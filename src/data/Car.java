package data;

import java.awt.Color;

import agent.Agent;
import data.Parameters;
/**
 * A car remembers its position from the beginning of its road. Cars have random
 * velocity and random movement pattern: when reaching the end of a road, the
 * dot either resets its position to the beginning of the road, or reverses its
 * direction.
 */
public class Car implements Agent {

	private double nextFrontPosition; // is the next position that a car will
	private double frontPosition;// is the position of front car
	private double maxVelocity;// The maximum velocity of the car (in// meters/second)
	private double brakeDistance;	// is the distance that car has to slow down (in meters)
	private double stopDistance;// is the distance that car has to stop due to an obstacle(in meters)
	private double length;// Length of the car (in meters)
	// color of the car, it takes a random number between 0-255 and with
		// math.ceil make it to bigger 0.2 to 1
	private final Color color = new Color((int) Math.ceil(Math.random() * 255.0),
			(int) Math.ceil(Math.random() * 255.0), (int) Math.ceil(Math.random() * 255.0));
	private Road currentRoad;// road that is the car
	/**
	 * Constructor that creates a new car object, sets length, max velocity,
	 * brake distance and stop distance.
	 * gives the car access to all the user selected parameters.
	 * 
	 */
	Car() {
		frontPosition = 0.0;
		maxVelocity = Parameters.carMaxVelocity.getRandomValue();
		brakeDistance = Parameters.carBrakeDistance.getRandomValue();
		stopDistance = Parameters.carStopDistance.getRandomValue();
		length = Parameters.carLength.getRandomValue();
	}

	/**
	 * static factory to generate a car
	 * 
	 * @return A new car Object
	 */

	static public Car generateCar() {
		return new Car();
	}
	/**
	 * set new <code>setFrontPositon</code> with the use of
	 * <code>nextFrontPosition</code>.
	 */

	public void run() {
		setFrontPosition(nextFrontPosition());
	}
	/**
	 * Sets a new front position to the car object.
	 * 
	 * @param frontPosition
	 *            is the new front position for the Car object.
	 * @throws IllegalArgumentException();
	 */
	public void setFrontPosition(double frontPosition) {
		if (frontPosition < 0)
			throw new IllegalArgumentException();
		// connects to road and get the road segment lenght
		double endPos = currentRoad.getLength();
		if (frontPosition > endPos) {
			// if we are in the end Position then we remove it and go to next
			// road from CarAcceptor
			currentRoad.remove(this);
			// in the accept take the car and the new front Position
			currentRoad.getNextRoad().accept(this, frontPosition - endPos);
		} else {
			this.frontPosition = frontPosition;
		}

	}
	
	/**
	 * calls the current {@link CarAcceptor} which holds Car object estimates
	 * new <code>velocity</code> based on the obstacles distance in front of the
	 * car object.
	 * 
	 * @return the next front position of the car.
	 */

	double nextFrontPosition() {
		double velocity = 0;
		// finds the closest obstacle to front Position
		double closestObstacle = currentRoad.distanceToObstacle(frontPosition);
		// it's possible for a slow car to be in front of a fast car, outside
				// the fast cars braking distance but within the fast cars maximum
				// velocity
			if (closestObstacle < maxVelocity && (closestObstacle > brakeDistance 
					|| closestObstacle > stopDistance)) { // compute velocity
				velocity = computeVelocity(closestObstacle/2);
				// If distance to nearest obstacle is <= brakeDistance,
				// then the car will start to slow down
				// If distance to nearest obstacle is == stopDistance,
				// then the car will stop			
		} else if (closestObstacle <= stopDistance) {
			velocity = computeVelocity(closestObstacle);
		} else if (closestObstacle > maxVelocity) {
			velocity = maxVelocity;
		}
		velocity = Math.max(0.0, velocity);// not negative
		velocity = Math.min(maxVelocity, velocity);// can not be more than max
													// velocity
		nextFrontPosition=	frontPosition += velocity * Parameters.carLength.getRandomValue();
		getCurrentRoad().accept(this, frontPosition);
		return nextFrontPosition;
	}

	private double computeVelocity(double closestObstacle) {
		// estimate velocity based on the closest obstacle
		return (maxVelocity / (brakeDistance - stopDistance)) * (closestObstacle - stopDistance);
	}

	/**
	 * @return the Road that Car object is located on.
	 */
	public Road getCurrentRoad() {
		return currentRoad;
	}
	/**
	 * sets the <code>currentRoad</code> to road.
	 * 
	 * @param road
	 *            the new Road to hold the Car object.
	 * @throws IllegalArgumentException()
	 */
	public void setCurrentRoad(Road road) {
		if (road == null) {
			throw new IllegalArgumentException();
		}
		currentRoad = road;
	}
	/**
	 * @return front position of the car object.
	 */
	public double getFrontPosition() {
		return frontPosition;
	}

	/**
	 * @return the car object brakeDistance.
	 */
	public double getBrakeDistance() {
		return brakeDistance;
	}

	/**
	 * @return the car object maxVelocity.
	 */
	public double getMaxVelocity() {
		return maxVelocity;
	}

	/**
	 * @return the car object stopDistance.
	 */
	public double getStopDistance() {
		return stopDistance;
	}
	/**
	 * @return back position of the car object.
	 */
	public double getBackPosition() {
		return frontPosition - length;
	}
	/**
	 * @return the car object color.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @return the car object length.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @return next front position of the Vehicle object that will be.
	 */
	public double getNextFrontPosition() {
		return nextFrontPosition;
	}
	
}
