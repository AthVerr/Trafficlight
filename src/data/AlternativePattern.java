package data;



import swing.AnimatorBuilder;

public final class AlternativePattern implements Pattern {

	private static TrafficLightController[] trafficlightcontroler;
	private static FaceDirection direction;
	private static int index;
	private static AnimatorBuilder Abuilder;

	AlternativePattern(TrafficLightController[] lightcontroler, FaceDirection facedirection, int index2,
			AnimatorBuilder builder) {
		trafficlightcontroler = lightcontroler;
		direction = facedirection;
		index = index2;
		Abuilder = builder;
	}

	public Source generateSegment() {
		CarAcceptor next = new Road(new Sink());
		if (direction == FaceDirection.EAST_TO_WEST) {
			Abuilder.addHorizontalRoad((Road) next, index, trafficlightcontroler.length, true);
		} else {
			Abuilder.addVerticalRoad((Road) next, trafficlightcontroler.length, index, true);
		}
		for (int i = 0; i < trafficlightcontroler.length; i++) {
			TrafficLight light = new TrafficLight(next);
			trafficlightcontroler[i].setTrafficLight(light, direction);
			if (direction == FaceDirection.EAST_TO_WEST) {
				Abuilder.addLight(light, index, i);
				Abuilder.addHorizontalRoad((Road) next, index, i, true);
			} else {
				Abuilder.addLight(light, i, index);
				Abuilder.addVerticalRoad((Road) next,i, index, true);
				
			}
			next = new Road(light);
		}
		
		return new Source(next);
	}
}
