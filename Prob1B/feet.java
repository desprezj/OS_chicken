/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * feet class
 * This class is a subclass of java.lang.Thread class.
 * The run method will receive input from the brain indicating new food. Once
 * so told, it stops the current movement if any, and moves random 5–10 steps
 * to the new food, 400ms per step. It will print for each step is taken print
 * out a string “step x” where x is the number of this step toward the current
 * food location. Once the steps have all been taken it tells the brain it has
 * walked to the destination.
 * This method will use monitor to synchronize the others tasks.
 *
 */
package os_chicken;

import java.util.Random;

public class feet extends Thread{
	
    private myMonitor Monitor1;
	private Random myRand;
	private final int MINIMUM_STEPS = 5;
    private final int MARGIN_STEPS = 6;
    private final int WAITING = 400;
	
	public feet(myMonitor Mon1){
		myRand =new Random();
		Monitor1 =Mon1;
	}
	
	public void run(){
		System.out.println("Start feet");
		int i,step;
		while(true){
			try {
                Monitor1.waitforfoodfeet(this);
                
				step= MINIMUM_STEPS+ myRand.nextInt(MARGIN_STEPS);
				i=1;
				while(i<=step && Monitor1.reset_step()==true){
				  feet.sleep(WAITING);
				  if(Monitor1.nextStep() && Monitor1.reset_step()==true){
					System.out.println("step "+ i);
					i++;
				  }
				  else{
					  i = step +1;
				  }
				}
				if(Monitor1.nextStep() && Monitor1.reset_step()==true){
                     Monitor1.arrived();
				}
				
			} catch (InterruptedException e) {
			}
		}
	}
}
