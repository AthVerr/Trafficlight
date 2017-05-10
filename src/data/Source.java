package data;

import agent.Agent;
import agent.TimeServer;
import agent.TimeServerLinked;

/**
 * Creates {@link car} Objects and places them into the next {@link CarAcceptor} Object. 
 */
public final class Source implements Agent {

	private CarAcceptor nextRoad;
	private TimeServer timeserver;
	private double sourcetime;//time need to generate a car
	/**
	 * Creates a new source object
	 * @param ca is the next road.
	 * @param params is the defined parameters. 
	 */
	Source(CarAcceptor ca) {
		nextRoad = ca;
		sourcetime=Parameters.carGenerationDelay.getRandomValue();
		timeserver = TimeServerLinked.getInstance();	//to measure time that needs to generate new cars
		//add in current time the source time 
		timeserver.enqueue(timeserver.currentTime() + sourcetime, this);
	}

	 /**Creates a new source Object with <code>CarAcceptor</code>.
	 * static factory
	 * @param CarAcceptor next car accepted.
	 * @return Source Object with <code>Parameters</code> and <code>CarAcceptor</code>.
	 */
	public static Source generateSource(CarAcceptor ca) {
		return new  Source(ca);
		}
	 /**
     * creates a new {@link car} object whenever it is run 
     */
	public void run() {
		Car car = Car.generateCar();
		//our front position will be 0 in the start
			if (nextRoad.accept(car, 0.0)){
				timeserver.enqueue(timeserver.currentTime(), car);
			}
			timeserver.enqueue(timeserver.currentTime() + sourcetime,this);
		}
	}

