package data;

/**
 * A singleton class with all of the parameters for the Traffic Simulator.
 * sets all of the parameters with default values.
 */

public class ParametersImpl {
	
////make the constructor private so that this class cannot be
	// instantiated
	private ParametersImpl() {
		// sets all of the parameters with default values.
	}
	// create an object of SingleObject
	private static ParametersImpl p = null; // new Parametres();
	/**
	 * Creates a new Parametres class or returns the current class if one
	 * already exists. 
	 * @return p.
	 */
	// Get the only object available
	public static ParametersImpl makeparametres() {
		if (ParametersImpl.p == null) {
			ParametersImpl.p = new ParametersImpl();
		}
		return ParametersImpl.p;
	}
	/**
	 * sets time step variable to <code>timeStep</code>
	 * @param ts.
	 */
	static public void setTimeStep(double ts) {
	
		Parameters.timeStep.setRTValue(ts);
	}
	/**
	 * sets run time step variable to <code>rt</code>
	 * @param rt.
	 * * @throws IllegalArgumentException();
	 */
	static public void setRuntime(double rt) {

		Parameters.runTime.setRTValue(rt);
	}
/**
 * 
 * @param dim
 */
	static public void setDimension(int[] dim) {
		Parameters.grid.setGridValue(dim);
	}
/**
 * 
 * @param pattern
 */
	static public void setPattern(String pattern) {
		if (pattern.equals("alternating"))
			Parameters.isAlternative.setPatternValue(true);
		else
			Parameters.isAlternative.setPatternValue(false);
	}
/**
 * 
 * @param delay
 */
	static public void setCarGenDelay(double[] delay) {
		Parameters.carGenerationDelay.setRangeValue(delay);
	}
/**
 * 
 * @param roadlenght
 */
	static public void setRoadLength(double[] roadlenght) {
		Parameters.roadSegmentLength.setRangeValue(roadlenght);
	}
/**
 * 
 * @param inlenght
 */
	static public void setIntersectionLength(double[] inlenght) {
		Parameters.intersectionLength.setRangeValue(inlenght);
	}
/**
 * 
 * @param carlenght
 */
	static public void setCarLength(double[] carlenght) {
		Parameters.carLength.setRangeValue(carlenght);
	}
/**
 * 
 * @param carVel
 */
	static public void setCarMaxVelocity(double[] carVel) {
		Parameters.carMaxVelocity.setRangeValue(carVel);
	}
/**
 * 
 * @param carStop
 */
	static public void setCarStopDistance(double[] carStop) {
		Parameters.carStopDistance.setRangeValue(carStop);
	}
/**
 * 
 * @param carBrake
 */
	static public void setCarBrakeDistance(double[] carBrake) {
		Parameters.carBrakeDistance.setRangeValue(carBrake);
	}
/**
 * 
 * @param greenTime
 */
	static public void setTrafficGreenTime(double[] greenTime) {
		Parameters.trafficLightGreenTime.setRangeValue(greenTime);
	}
/**
 * 
 * @param yellowTime
 */
	static public void setTrafficYellowTime(double[] yellowTime) {
		Parameters.trafficLightYellowTime.setRangeValue(yellowTime);
	}

	/**
	 * appends the specified builder to a sequence. we increase the length of
	 * this sequence by the length of the argument.
	 * @return a String with all the parameters values and their minimum/maximum ranges.
	 */
	 static public StringBuilder showCurrentValues() {
		StringBuilder builder = new StringBuilder();
		for (Parameters p : Parameters.values()) {
			if (p != Parameters.roadSegmentLength_grafic || p != Parameters.carLength_grafic)
				builder.append(p.getTitle() + ": ");
			builder.append(p.toString());
			builder.append("\n");
		}
		return builder;
	}
	 /**
	  * reset the values
	  */
	 	static public void reset() {
	 		Parameters. timeStep.setRTValue(0.1);
	 		Parameters.runTime.setRTValue(1000.0);
	 		Parameters.grid.setGridValue(new int[] { 2, 3 });
	 		Parameters.isAlternative.setPatternValue(true);
	 		Parameters.carGenerationDelay.setRangeValue(new double[] { 5.0, 25.0 });
	 		Parameters.roadSegmentLength.setRangeValue(new double[] { 200.0, 500.0 });
	 		Parameters.intersectionLength.setRangeValue(new double[] { 10.0, 15.0 });
	 		Parameters.carLength.setRangeValue(new double[] { 5.0, 10.0 });
	 		Parameters.carMaxVelocity.setRangeValue(new double[] { 10.0, 30.0 });
	 		Parameters.carStopDistance.setRangeValue(new double[] { 0.5, 5.0 });
	 		Parameters.carBrakeDistance.setRangeValue(new double[] { 9.0, 10.0 });
	 		Parameters.trafficLightGreenTime.setRangeValue(new double[] { 8.0, 10.0 });
	 		Parameters.trafficLightYellowTime.setRangeValue(new double[] { 4.0, 5.0 });
	 	}
}
