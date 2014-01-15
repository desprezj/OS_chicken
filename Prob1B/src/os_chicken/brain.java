/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * brain class
 * This class is a subclass of java.lang.Thread class.
 * The run method will receive input from the eyes indicating food has been
 * found. Once so informed emit a string “Brain :saw food!” and then tells the mouth
 * to stop eating, the feet to stop any current movement and to move toward the
 * newly identified food. Once the feet indicate the destination is reached a
 * string is printed “arrived food!” and the mouth is told to start eating.
 * This method will use monitor to synchronize the others tasks.
 *
 */
package os_chicken;


public class brain extends Thread{

	private myMonitor Monitor1;
	
	public brain(myMonitor Mon1){
        Monitor1=Mon1;
	}
	
	public void run(){
		System.out.println("Start Brain");

		while(true){
	        if(Monitor1.waitfood()){
			   System.out.println("Brain: saw food!");
			   Monitor1.startwalking();
			}
			else
				yield();
			if(Monitor1.printBrain()){
			    System.out.println("Arrived food!");
			    Monitor1.finishprint();
			}
			else{
				yield();
			}

		}
	}
	
}
