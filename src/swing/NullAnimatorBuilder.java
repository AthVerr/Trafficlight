package swing;

import data.Road;
import data.TrafficLight;
import util.Animator;

/**
 * Null object for {@link AnimatorBuilder}.
 */
class NullAnimatorBuilder implements AnimatorBuilder {
  public Animator getAnimator() { return new NullAnimator(); }
  public void addLight(TrafficLight d, int i, int j) { }
  public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) { }
  public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) { }
}