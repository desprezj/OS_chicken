/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * eyes class
 * This class is a subclass of java.lang.Thread class.
 * The run method will look for food every 1000–2000ms, finding food 50% of the
 * time. Each time they look for food print out a string “looking!”
 * Each time it finds food it prints out a string "Eyes: Saw food!"
 * This method will use monitor to synchronize the others tasks.
 */
package os_chicken;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eyes extends Thread{
   
	private myMonitor monitor1;
	private Random myRand;
	private int MINIMUM_WAITING = 1000;
    private int MARGIN_WAITING = 1001;
	
	public eyes(myMonitor mon1){
		monitor1=mon1;
		myRand = new Random();

	}
	
	public void run(){
		System.out.println("Start Eyes");
		
		while(true){
		  try {
			sleep(MINIMUM_WAITING + myRand.nextInt(MARGIN_WAITING));
			System.out.println("Looking!");
		    if(myRand.nextBoolean()){
		    	  System.out.println("Eyes: Saw food!");
                  monitor1.SeeFood();
			}
		  } catch (InterruptedException ex) {
			  Logger.getLogger(eyes.class.getName()).log(Level.SEVERE, null, ex);
		  }
		}
	}
}
