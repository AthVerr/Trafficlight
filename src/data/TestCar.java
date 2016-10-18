package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCar {
	private static final double EPSILON = 0.0;

	private Road road;
	private Car vehicle;

	@Before
	public void setUp() {
		vehicle = new Car();
		road = new Road(Sink.generateSink());
		vehicle.setCurrentRoad(road);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setFrontPositionnegative() {
		vehicle.setFrontPosition(-5.0);
	}

	@Test
	public void setFrontPositionbigger() {
		vehicle.setFrontPosition(600);

	}

	@Test
	public void setFrontPosition() {
		vehicle.setFrontPosition(5.0);
		assertEquals(5.0, vehicle.getFrontPosition(), EPSILON);
	}

	@Test
	public void CurrentRoad_nonNullRoad() {
		vehicle.setCurrentRoad(road);
		assertEquals(vehicle.getCurrentRoad(), road);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CurrentRoad_nullRoad() {
		vehicle.setCurrentRoad(null);
	}

}
