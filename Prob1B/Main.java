/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * Main class
 * This file contains the main method which creates and runs 4 threads (Brain,
 * Eyes, Feet and Mouth). These threads represent the function of different
 * chicken body parts. These 4 threads will be synchronized using Monitor
 * 
 *
 */
package os_chicken;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        myMonitor Mon1 =new myMonitor();
        eyes myEyes = new eyes(Mon1);
        feet myFeet = new feet(Mon1);
        mouth myMouth = new mouth(Mon1);
        brain myBrain = new brain(Mon1);
        myBrain.start();
        myMouth.start();
        myFeet.start();
        myEyes.start();
        
        
	}

}
