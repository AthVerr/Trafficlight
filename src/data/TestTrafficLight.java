package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTrafficLight {
	private static final double EPSILON = 0.0;

	private Road road;
	private TrafficLight light;

	@Before
	public void setUp() {
		road = Road.generateRoad(Sink.generateSink());
		light = new TrafficLight(road);
	}

	@Test
	public void SetColor() {

		light.setColor(LightColor.GREEN);
		assertEquals(LightColor.GREEN, light.getColor());
		light.setColor(LightColor.YELLOW);
		assertEquals(LightColor.YELLOW, light.getColor());
		light.setColor(LightColor.RED);
		assertEquals(LightColor.RED, light.getColor());
	}

	@Test
	public void DistanceToObstacle() {

		road.setLength(300);
		TrafficLight l = new TrafficLight(road);
		l.setColor(LightColor.RED);
		assertEquals(0.0, l.distanceToObstacle(300), EPSILON);
		l.setColor(LightColor.GREEN);
		//assertEquals(300.0, l.distanceToObstacle(0), EPSILON);
		l.setColor(LightColor.YELLOW);
		//assertEquals(50.0, l.distanceToObstacle(110), EPSILON);
	}

}
