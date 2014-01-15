/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * myMonitor class
 * This class is used to create monitor object, this monitor object will be
 * used to synchronize different other objects, these objects will wait until
 * some conditions occur.
 * We use the myCondition Class to represent condition.
 *
 */
package os_chicken;

public class myMonitor {
    
	myCondition Condition1;
	myCondition Condition2;
	myCondition Condition3;
	myCondition Condition4;
	myCondition Condition5;
	myCondition Condition6;
	
	public myMonitor(){
		Condition1 =new myCondition(); // saw food or not
		Condition2 =new myCondition(); // is arrived or not
		Condition3 =new myCondition(); //is walking or not
	    Condition4 =new myCondition(); // feet wait for food
	    Condition5 =new myCondition(); //brain print its message or not
	    Condition6 =new myCondition(); //stop the step or not
	}
	
	public synchronized void SeeFood(){
		Condition6.resetCondition();
        Condition4.resetCondition();
        Condition5.resetCondition();
		Condition3.resetCondition();
		Condition1.signal();
		Condition2.resetCondition();

	}
	
	public synchronized void arrived() {
        Condition2.signal();
        Condition5.signal();
        Condition1.resetCondition();
        Condition3.resetCondition();
        Condition4.resetCondition();
        notifyAll();
    }
	
	
	public synchronized boolean waitfood(){
		return Condition1.leaveWithSignal();
	}
	
	
	public synchronized void waitforfoodfeet(Thread myThread) throws InterruptedException{
		while(Condition4.leaveWithSignal()!= true){
			try{
			       wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}	
		Condition3.signal();
		Condition6.signal();
	}
	
	public synchronized void startwalking(){
		Condition1.resetCondition();
		Condition3.signal();
		Condition4.signal();
        notifyAll();
	}
	
	public synchronized boolean nextStep(){
		return Condition3.leaveWithSignal();
	}
	
	public synchronized void waitforeat(Thread myThread) throws InterruptedException{
		while(Condition2.leaveWithSignal()!= true){
			try{
			       wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public synchronized boolean printBrain(){
		return Condition5.leaveWithSignal();
	}

	public synchronized void finishprint(){
		Condition5.resetCondition();
	}
	
	public synchronized boolean nextEat(){
		return Condition2.leaveWithSignal();
	}
	
	public synchronized boolean reset_step(){
		return Condition6.leaveWithSignal();
	}
}
