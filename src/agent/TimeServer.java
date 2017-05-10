package agent;

/**
 * The waketime must be greater than currenttime, or the enqueue method should
 * throw an exception. The timeserver orders agents by their waketime, so the
 * one to wake first is always at the front. So in order to run the next agent,
 * the first agent is dequeued. then the currenttime is set to its waketime,
 * then the agent is run.
 */

public interface TimeServer {
	public double currentTime();

	public void enqueue(double waketime, Agent thing);

	public void run(double duration);

	public void addObserver(java.util.Observer o);

	public void deleteObserver(java.util.Observer o);
}
