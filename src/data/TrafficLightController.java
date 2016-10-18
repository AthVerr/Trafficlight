package data;

import java.util.Observable;

import agent.Agent;
import agent.TimeServer;
import agent.TimeServerLinked;


/**
 * A TrafficControler has a {@link LightColor} that determines which cars can
 * pass through an {@link TrafficLight} Object.
 */
public class TrafficLightController extends Observable implements Agent {

	

	private TrafficLight eastToWestLight;
	private TrafficLight  northToSouthLight;
	private TimeServer timeServer;
	private double getGreenLightTime,getYellowTimeLight;

	TrafficLightController() {
		timeServer = TimeServerLinked.getInstance();
		timeServer.enqueue(timeServer.currentTime(), this);
		getGreenLightTime=Parameters.trafficLightGreenTime.getRandomValue();
		getYellowTimeLight=Parameters.trafficLightYellowTime.getRandomValue();
		
	}

	public void run() {
		controlTraffic();
	}
	

	public TrafficLight getEastWestLight(){
		return eastToWestLight;
	}

	public TrafficLight getNorthSouthLight(){
		return northToSouthLight;
	}


	   public void setTrafficLight(TrafficLight light,FaceDirection direction) {
	        if (direction == FaceDirection.EAST_TO_WEST) {
	            eastToWestLight = light;
	            eastToWestLight.setColor(LightColor.RED);
	        } else {
	            northToSouthLight = light;
	            northToSouthLight.setColor(LightColor.GREEN);
	        }
	    }

	   /**
		 * Depending on the current state of the TrafficLight Object, the
		 * {@link LightColor} is changed, and the TrafficLight is reEnqueued.
		 */

	private void controlTraffic() {
		 if (eastToWestLight.getColor() == LightColor.GREEN) {
	            northToSouthLight.setColor(LightColor.RED);
	            timeServer.enqueue(timeServer.currentTime() + getGreenLightTime, this);
	            eastToWestLight.setColor(LightColor.YELLOW);
	        } else if (eastToWestLight.getColor() == LightColor.YELLOW) {
	            timeServer.enqueue(timeServer.currentTime() + getYellowTimeLight, this);
	            eastToWestLight.setColor(LightColor.RED);
	            northToSouthLight.setColor(LightColor.GREEN);
	        } else if (northToSouthLight.getColor() == LightColor.GREEN) {
	            eastToWestLight.setColor(LightColor.RED);
	            timeServer.enqueue(timeServer.currentTime() + getGreenLightTime, this);
	            northToSouthLight.setColor(LightColor.YELLOW);
	        } else if (northToSouthLight.getColor() == LightColor.YELLOW) {
	            timeServer.enqueue(timeServer.currentTime() + getYellowTimeLight, this);
	            northToSouthLight.setColor(LightColor.RED);
	            eastToWestLight.setColor(LightColor.GREEN);
	        }
	}
}
