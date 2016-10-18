package data;


/**
 * Depending in the color of the light cars passes or stop 
 */
public class TrafficLight implements CarAcceptor {
	
	private LightColor state;
	private CarAcceptor nextRoad;

	TrafficLight(CarAcceptor ca) {
		nextRoad = ca;
	}
	/**
	 * Static factory generate traffic light
	 * 
	 * @param ca
	 * @return A new trafficLight Object
	 */


	public static TrafficLight generateintercextion(CarAcceptor ca) {
		return new TrafficLight(ca);
	}
	
	public LightColor getColor() {
		return state;
	}

	public void setColor(LightColor f) {
		state = f;
	}

	@Override
	public boolean accept(Car c, double frontPosition) {
	    return carCanGo() ? nextRoad.accept(c, frontPosition) : false;
	}

	@Override
	public double distanceToObstacle(double fromDistance) {
	    return (carCanGo()) ? nextRoad.distanceToObstacle(fromDistance) : 0.0;
	}

	private boolean carCanGo() {
	    return state == LightColor.GREEN || state == LightColor.YELLOW;
	}
}




