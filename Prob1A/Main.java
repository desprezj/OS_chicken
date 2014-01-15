/*
 * HOMEWORK ASSIGNMENT H4 (semaphore part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/5/2011
 * Main class
 * This file contains the main method which creates 4 semaphores and 4 threads
 * (Brain, Eyes, Feet and Mouth). These threads represent the function of
 * different chicken body parts. These 4 threads will be synchronized by
 * semaphores.
 *
 */

package os_chicken;

import java.util.concurrent.Semaphore;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Semaphore mySemE1 = new Semaphore(0);
        Semaphore mySemF1 = new Semaphore(0);
        Semaphore mySemF2 = new Semaphore(0);
        Semaphore mySemM1 = new Semaphore(0);
        
        eyes myEyes = new eyes(mySemE1);
        feet myFeet = new feet(mySemF1,mySemF2);
        mouth myMouth = new mouth(mySemM1);
        
        brain myBrain =new brain(mySemE1,mySemF1,mySemF2,mySemM1,myFeet,myMouth);
        
        
        myBrain.start();
        myFeet.start();
        myMouth.start();
        myEyes.start();
	}

}
