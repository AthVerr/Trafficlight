package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import agent.TimeServer;
import agent.TimeServerLinked;

/**
 * A road holds cars and when runs, runs all the {@link Car} 
 * Objects it is holding and then re-enqueues itself.
 */

public class Road implements CarAcceptor {

	private Set<Car> hascars; //store cars
	private TimeServer timeserver;
	private double endPos;//road segment lenght 
	private CarAcceptor nextRoad;//is the next road that accepts cars
	//enable access to parametres , make object

	Road(CarAcceptor ca) {
		nextRoad = ca;
		hascars = new HashSet<Car>();// a road has cars , stopped , running
		endPos = Parameters.roadSegmentLength.getRandomValue();//is the road segment lenght
		timeserver = TimeServerLinked.getInstance();
	}
	/**
	 * Static factory that Creates a new Road Object with <code>ca</code>.
	 * @param ca
	* @return Road Object.
	 */
	public static Road generateRoad(CarAcceptor ca) {
		return new Road(ca);
	}
	
	/**
	 * Accepts a new {@link Car} object and places it at <code>frontPositon</code>
	 * @param vehicle  is the new Vehicle object.
	 * @param frontPositon is the front position of vehicle <code>car</code>
	 * @return True if accept succeed
	 */
	public boolean accept(Car car, double frontPosition) {
		hascars.remove(car);
		if (frontPosition > endPos) {//its bigger that road segment 
			//change front position to minus the road segment 
				return nextRoad.accept(car, frontPosition - endPos);
			
		} else {
			car.setCurrentRoad(this); //hold vehicle object in new Road 
			car.setFrontPosition(frontPosition);//set its front position from vehicle object
			hascars.add(car);
			//add in current time the time we need to move the vehicle
			timeserver.enqueue(timeserver.currentTime() + Parameters.timeStep.getRandomValue(), car);
			return true;
		}
	}
	

	/**
	 * Removes a selected Car object from the road if it exist.
	  * @return True if succeed
	 * @return False if not succeed
	 */
	public boolean remove(Car c) {
		if (hascars.contains(c)) {
			hascars.remove(c);
			return true;
		} else {
			return false;
		}
	}

	
/**
 * Estimates the closest car object to <code>fromPosition</code>
 * @param fromPosition  the point the measurement starts.
 * @return carBackPosition the closest car object to <code>fromPosition</code>
 */
//it estimates depending getBackPosition()
	private double distanceToCarBack(double fromPosition) {
		double carBackPosition = Double.POSITIVE_INFINITY;//very far
		for (Car c : hascars) {
			//if back pos of the car in the list is bigger from front pos and smaller 
			if (c.getBackPosition() >= fromPosition	&& c.getBackPosition() < carBackPosition)
				//this car back position is equal to our car back pos
				carBackPosition = c.getBackPosition();//when car are in front of it
		}
		return carBackPosition;
	}
	/**
	 * Finds the closest obstacle in front of <code>fromPosition</code>
	  * @param fromPositon the beginning point being measured
	* @return The distance to the closest obstacle from  <code>fromPosition</code>
	 */

	public double distanceToObstacle(double fromPosition) {
		//the obstacle is a car so it takes the closest car
		double obstaclePosition = this.distanceToCarBack(fromPosition);//where is the obstacle
		double distanceToEnd = 0;//distance to the obstacle
		if (obstaclePosition == Double.POSITIVE_INFINITY) {//very far
			//distance to the obstacle is front pos minus road segment 
			 distanceToEnd = fromPosition-endPos;
				obstaclePosition = (nextRoad).distanceToObstacle(fromPosition-endPos);
		}
	 distanceToEnd= obstaclePosition - fromPosition;
		return distanceToEnd;
	}
	
	/**
	 * @return The next road the car object is on.
	 */
	public CarAcceptor getNextRoad() {
		return nextRoad;
	}

      public void setLength(double endPosition) {
	        endPos = endPosition;
        }

      /**
  	 * @return the list of car objects in the Road.
  	 */
	public List<Car> getCars() {
		return new ArrayList<Car>(hascars);
	}
	/**
	 * @return The total length of the Road, road segment
	 */
	public double getLength() {
		return endPos;
	}
	
	// test
		void testaddCar(Car c) {
			hascars.add(c);
		}

		// test
		public void setEndPosition(double endPosition) {
			endPos = endPosition;
		}
}

