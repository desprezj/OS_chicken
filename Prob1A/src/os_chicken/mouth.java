/*
 * HOMEWORK ASSIGNMENT H4 (semaphore part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/5/2011
 * mouth class
 * This file contains the thread of the mouth of the chicken
 * each mouthful takes 400ms
 * eating lasts until it is informed to stop
 *
 */
package os_chicken;

import java.util.concurrent.Semaphore;

public class mouth extends Thread{
    
	private Semaphore sem_mouth;
	private final int WAITING = 400;
	
	public mouth(Semaphore sem1){
		sem_mouth = sem1;
	}
	
	public void run(){
		System.out.println("Start mouth");
		while(true){
			try {
				sem_mouth.acquire();
				while(true){
				   mouth.sleep(WAITING);
				   System.out.println("Munching!");
				}
			} catch (InterruptedException ex) {
			}
		}
	}
}
