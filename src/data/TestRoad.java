package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestRoad {

	private static final double EPSILON = 0.0;
	private Car vehicleB;
	private Car vehicleA;
	private Road road;
	

	@Before
	public void setUp() {
		vehicleA = new Car();
		vehicleB = new Car();
		road = new Road(Sink.generateSink());
		vehicleB.setCurrentRoad(road);

	}

	@Test
	public void remove_emptyCars_noChange() {
		assertEquals(road.getCars().size(), 0);
		road.remove(vehicleB);
		assertEquals(road.getCars().size(), 0);
	}

	@Test
	public void remove_hasA_noChange() {
		road.testaddCar(vehicleA);
		assertEquals(road.getCars().size(), 1);
		road.remove(vehicleB);
		assertEquals(road.getCars().size(), 1);
	}

	@Test
	public void remove_hasB_noB() {
		road.testaddCar(vehicleB);
		assertEquals(road.getCars().size(), 1);
		road.remove(vehicleB);
		assertEquals(road.getCars().size(), 0);
	}

	@Test
	public void RoadEndPosition() {

		assertTrue(road.getLength() > 200.0 && road.getLength() < 500.0);
	}

	@Test
	public void AcceptTrue() {
		road.setEndPosition(100.0);
		assertTrue(road.accept(vehicleB, vehicleB.getFrontPosition()));
	}

	// @Test
	// public void AcceptFronPosBiggerEnd() {
	// road.setEndPosition(100.0);
	// assertTrue(road.accept(vehicleB, vehicleB.getFrontPosition()));
	// vehicleB.setFrontPosition(150.0);
	// assertFalse(road.accept(vehicleB, vehicleB.getFrontPosition()));
	// }

	@Test
	public void distanceToObstacle_forFalse() {
		road.testaddCar(vehicleA);
		road.testaddCar(vehicleB);
		assertEquals(Double.POSITIVE_INFINITY, road.distanceToObstacle(10), EPSILON);
	}

	// @Test
	// public void DistanceToObstacleFor0() {
	//
	// road.setEndPosition(300.0);
	// assertEquals(0.0, road.distanceToObstacle(300.0), EPSILON);
	// }
	//
	// @Test
	// public void DistanceToObstacle() {
	// road.testaddCar(vehicleA);
	// road.testaddCar(vehicleB);
	// road.setEndPosition(300.0);
	// assertEquals(20.0, road.distanceToObstacle(180), EPSILON);
	//
	// }

}
