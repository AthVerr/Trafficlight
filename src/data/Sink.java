package data;

/**
 * A sink is the end point of the last CarAcceptor. It catches all of the
 * {@link car} Objects and removes them.
 * 
 * @author athina
 *
 */
public final class Sink implements CarAcceptor {
	
	
	Sink() {

	}

	
	/**
	 * Static factory
	 * @return a new sink object
	 */
	public static Sink generateSink() {
		return new Sink();
	}
	/**
	 * always returns the maximum distance possible.
	 */
	public double distanceToObstacle(double fromPosition) {
		return Double.POSITIVE_INFINITY;
	}
	/**
	 * Accepts and destroys {@link car} objects.
	 * @param car Car to be destroyed.
	 * @param frontPosition
	 * @return True or False
	 */
	public boolean accept(Car car, double frontPosition) {
		 return (frontPosition == Double.POSITIVE_INFINITY) ? true : false;
			
	}
}
