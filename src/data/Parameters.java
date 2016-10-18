package data;

import util.Util;

/**
 * 
 * enumeration for the standard parameters
 *
 */
public enum Parameters {
	
	 timeStep("Simulation time step (seconds)", 0.1D), 
	 runTime("Simulation run time (seconds)", 1000.0), 
	 grid("Grid size (number of roads)", 2, 3), 
	 isAlternative("Traffic pattern", true), 
	 carGenerationDelay("Car entry rate (seconds/car)", 2.0, 25.0), 
	 roadSegmentLength("Road segment length (meters)", 200.0, 500.0), 
	 intersectionLength("Intersection length (meters)", 10.0, 15.0), 
	 carLength("Car length (meters)", 5.0, 10.0), 
	 carMaxVelocity("Car maximum velocity (meters/second)", 10.0, 30.0), 
	 carStopDistance("Car stop distance (meters)", 0.5, 5.0), 
	 carBrakeDistance("Car brake distance (meters)", 9.0, 10.0), 
	 trafficLightGreenTime("Traffic light green time (seconds)", 10.0, 11.0), 
	 trafficLightYellowTime("Traffic light yellow time (seconds)", 4.0, 5.0),

	 roadSegmentLength_grafic("Road segment lenght UI", 300.0), 
	 carLength_grafic("Car lenght UI", 15.0);
	
	private String use;
	private double value;
	private int[] gridDimension = { 2, 3 };
	private boolean Alternative;
	private double[] dimension = { 100, 200 };
	
	
	/**
	 * for time step and run
	 * @param usage
	 * @param initialize
	 */

	Parameters(String usage, double initialize) {
		use = usage;
		value = initialize;
		gridDimension = null;
		Alternative = false;
		dimension = null;
	}

/**
 * for grid
 * @param usage
 * @param row
 * @param col
 */
	Parameters(String usage, int row, int col) {
		use = usage;
		gridDimension[0] = row;
		gridDimension[1] = col;
		value = 0.0;
		Alternative = false;
		dimension = null;
	}

	/**
	 * for traffic pattern
	 * @param usage
	 * @param isAlternative
	 */
	Parameters(String usage, boolean isAlternative) {
		use = usage;
		Alternative = isAlternative;
		value = 0.0;
		gridDimension = null;
		dimension = null;
	}

	/**
	 * for random values
	 * @param usage
	 * @param min
	 * @param max
	 */
	Parameters(String usage, double min, double max) {
		use = usage;
		dimension[0] = min;
		dimension[1] = max;
		value = 0.0;
		gridDimension = null;
		Alternative = false;
	}
/**
 * for the random values like velocity inside the program,enum above
 * @return value
 */
	public double getRandomValue() {
		if (value == 0.0)
			return Util.nextRandom(dimension[0], dimension[1]);
		else
			return value;
	}

/**
 * for the pattern
 * @return  Alternative
 */
	public boolean getPatternValue() {
		return Alternative;
	}
	/**
	 * for the pattern
	 * @return  gridDimension
	 */
	public int[] getGridValue() {
		return gridDimension;
	}
	/**
	 * for the run time and time step
	 * @param initialize
	 */
	public void setRTValue(double initialize) {
		value = initialize;
	}
/**
 * for the random values like velocity inside the program,enum above
 * @param range
 */
	public void setRangeValue(double[] range) {
		dimension = range;
	}
/**
 * for the pattern change
 * @param pattern
 */
	public void setPatternValue(boolean pattern) {
		Alternative = pattern;
	}
	/**
	 * for grid dimension
	 * @param dimension
	 */

	public void setGridValue(int[] dimension) {
		gridDimension = dimension;
	}
	
	public String getTitle() {
		return use;
	}
	/**
	 * print value numbers when select 2 and 1 as option
	 */
	public String toString() {
		if (gridDimension != null)
			return "[" + gridDimension[0] + ", " + gridDimension[1] + "]";
		else if (dimension != null)
			return "[" + dimension[0] + ", " + dimension[1] + "]";
		else if (value != 0.0)
			return "[" + value + "]";
		else {
			if (Alternative)
				return "[alternative]";
			else
				return "[simple]";
		}
	}
}
