/*
 * HOMEWORK ASSIGNMENT H4 (semaphore part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/5/2011
 * eyes class
 * This file contains the thread of the eyes of the chicken
 * wait between 1000ms to 2000ms
 * 50% to find food when looking
 */
package os_chicken;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eyes extends Thread{
   
	private Semaphore sem_eyes;
	private Random myRand;
	private int MINIMUM_WAITING = 1000;
    private int MARGIN_WAITING = 1001;
	
	public eyes(Semaphore sem){
		sem_eyes = sem;
		myRand = new Random();

	}
	
	public void run(){
		System.out.println("Start Eyes");
		
		while(true){
		  try {
			sleep(MINIMUM_WAITING + myRand.nextInt(MARGIN_WAITING));
			System.out.println("Looking!");
		    if(myRand.nextBoolean()){
				sem_eyes.release();
			}
		  } catch (InterruptedException ex) {
			  Logger.getLogger(eyes.class.getName()).log(Level.SEVERE, null, ex);
		  }
		}
	}
}
