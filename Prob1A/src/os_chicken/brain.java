/*
 * HOMEWORK ASSIGNMENT H4 (semaphore part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/5/2011
 * brain class
 * This file contains the thread of the brain of the chicken
 * When the brain receives a signal from the eyes: saw food
 * stops the feet and moves to the new food
 * 
 * When arrives to the food, it activates the mouth to start eating
 * when it receives a new food from the eyes it interrupts the eating and start the feet.
 */
package os_chicken;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class brain extends Thread{
	private Semaphore sem_eyes;
	private Semaphore sem_feet1;
	private Semaphore sem_feet2;
	private Semaphore sem_mouth;
	private feet myFeet;
	private mouth myMouth;
	
	public brain(Semaphore sem1,Semaphore sem2,Semaphore sem3,Semaphore sem4,feet FEET,mouth MOUTH){
		sem_eyes = sem1;
		sem_feet1 = sem2;
		sem_feet2 = sem3;
		sem_mouth = sem4;
		this.myFeet =FEET;
		this.myMouth = MOUTH;
	}
	
	public void run(){
		System.out.println("Start Brain");
		boolean restart=false;
		boolean mouth_active=false;
		while(true){
			try {
				restart=false;
				sem_eyes.acquire();
				
				if(mouth_active==true){
					myMouth.interrupt();
					mouth_active=false;
				}
				System.out.println("Brain: Saw food!");
				sem_feet1.release();
				
				
				 while (sem_feet2.availablePermits() == 0  && restart == false) {
	                    if (sem_eyes.availablePermits() > 0) {
	                        myFeet.interrupt();
	                        //we are going to restart :-interrupt feet and interrupt mouth
	                        restart = true;
	                        myMouth.interrupt();
	                    }
				 }
				 
				if(restart==false){
				   sem_feet2.acquire();
				   System.out.println("Arrived food!");
				   sem_mouth.release();
				   mouth_active=true;
				}
				
			} catch (InterruptedException ex) {
				 Logger.getLogger(brain.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
}
