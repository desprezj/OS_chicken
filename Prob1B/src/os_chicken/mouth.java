/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * mouth class
 * This class is a subclass of java.lang.Thread class.
 * The run method will wait for a signal from the brain to start eating.
 * Eating lasts until it is informed to stop; eating each mouthful takes 400ms
 * and it prints “munching!”.
 * This method will use monitor to synchronize the others tasks.
 *
 */
package os_chicken;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mouth extends Thread{
    
	private myMonitor Monitor1;
	private final int WAITING = 400;
	
	public mouth(myMonitor Mon1){
		Monitor1 = Mon1;
	}
	
	public void run(){
		System.out.println("Start mouth");
		while(true){
			try {
				while(true){
				  Monitor1.waitforeat(this);
				  mouth.sleep(WAITING);
				  
				  if(Monitor1.nextEat()){
				      System.out.println("Munching!");
				  }
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(mouth.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
