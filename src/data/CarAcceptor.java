package data;
/**
 * interface for all Objects that can accept Car objects. 
 * Road , Sink , trafficLight
 */
public interface CarAcceptor {
	/**
	 * 	
	 * @param car The {@link Car} Object to be accepted into the CarAcceptor. 
	 * @param frontPosition The position of the {@link Car} Object in the CarAcceptor. 
	 * 
	 */
	public boolean accept(Car car, double frontPosition);
	/**
	 * Returns the distance to the closest obstacle from <code>frontPosition</code>.
	 * @param frontPosition The front position of the current {@link Car} object. 
	 * @return the distance to the closest obstacle from <code>frontPosition</code>.
	 */
	public double distanceToObstacle(double frontPosition);
}
