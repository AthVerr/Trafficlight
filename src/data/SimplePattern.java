package data;

import swing.AnimatorBuilder;

final class SimplePattern implements Pattern {
	private static TrafficLightController[] trafficlightcontroler;
	private static FaceDirection direction;
	private static int index;
	private static AnimatorBuilder Abuilder;

	SimplePattern(TrafficLightController[] lightcontroler, FaceDirection facedirection, int index2,
			AnimatorBuilder builder) {
		trafficlightcontroler = lightcontroler;
		direction = facedirection;
		index = index2;
		Abuilder = builder;
	}

	public Source generateSegment() {
		CarAcceptor next = new Road(new Sink());
		if (direction == FaceDirection.EAST_TO_WEST) {
			Abuilder.addHorizontalRoad((Road) next, index, trafficlightcontroler.length, false);
		} else {
			Abuilder.addVerticalRoad((Road) next, trafficlightcontroler.length, index, false);
		}
		for (int i = trafficlightcontroler.length - 1; i >= 0; i--) {
			TrafficLight light = new TrafficLight(next);
			trafficlightcontroler[i].setTrafficLight(light, direction);
			next = new Road(light);
			if (direction == FaceDirection.EAST_TO_WEST) {
				Abuilder.addHorizontalRoad((Road) next, index, i, false);
				Abuilder.addLight(light, index, i);
			} else {
				Abuilder.addVerticalRoad((Road) next, i, index, false);
				Abuilder.addLight(light, i, index);
			}

		}
		return new Source(next);
	}
}
