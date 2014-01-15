/*
 * HOMEWORK ASSIGNMENT H4 (monitor part)
 * Julien DESPREZ
 * CS420, Operating Systems
 * 10/7/2011
 * myCondition class
 * This file handle the state of the condition
 * can change the state or return it
 *
 */
package os_chicken;

public class myCondition {

    private boolean myCondition = false;

    public synchronized void resetCondition() {
        myCondition = false;
    }

    public synchronized void signal() {
        myCondition = true;
    }

    public synchronized boolean leaveWithSignal() {
        return myCondition;
    }
}
