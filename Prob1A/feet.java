/*
 * HOMEWORK ASSIGNMENT H4 (semaphore part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/5/2011
 * feet class
 * This file contains the thread of the feet of the chicken
 * do between 5 and 10 steps
 * wait 400ms between each step
 *
 */
package os_chicken;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class feet extends Thread{
	
	private Semaphore sem_feet1;
	private Semaphore sem_feet2;
	private Random myRand;
	private final int MINIMUM_STEPS = 5;
    private final int MARGIN_STEPS = 6;
    private final int WAITING = 400;
	
	public feet(Semaphore sem1,Semaphore sem2){
		myRand =new Random();
		sem_feet1=sem1;
		sem_feet2=sem2;
	}
	
	public void run(){
		System.out.println("Start feet");
		int i,step;
		while(true){
			try {
				sem_feet1.acquire();
				step= MINIMUM_STEPS+ myRand.nextInt(MARGIN_STEPS);
				i=0;
				while(i<step){
					feet.sleep(WAITING);
					System.out.println("step "+ (i+1));
					i++;
				}
				if(i==step){
					sem_feet2.release();
				}
				
			} catch (InterruptedException e) {
			}
		}
	}
}
