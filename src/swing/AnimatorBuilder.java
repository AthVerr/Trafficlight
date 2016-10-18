package swing;

import data.TrafficLight;
import data.Road;
import util.Animator;

/** 
 * An interface for building a {@link Animator} for this {@link MP}.
 */
public interface AnimatorBuilder {
  /**
   *  Returns the {@link Animator}.
   *  This method may be called only once; subsequent calls throw an
   *  {@link IllegalStateException}.
   */
  public Animator getAnimator();
  /**
   *  Add the {@link TrafficLight} to the display at position <code>i,j</code>.
   */
  public void addLight(TrafficLight d, int i, int j);
  /**
   *  Add the horizontal {@link Road} to the display, west of position <code>i,j</code>.
   *  If <code>eastToWest</code> is true, then road position 0 is the eastmost position.
   *  If <code>eastToWest</code> is false, then road position 0 is the westmost position.
   */
  public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest);
  /**
   *  Add the vertical {@link Road} to the display, north of position <code>i,j</code>.
   *  If <code>southToNorth</code> is true, then road position 0 is the southmost position.
   *  If <code>southToNorth</code> is false, then road position 0 is the northmost position.
   */
  public void addVerticalRoad(Road l, int i, int j, boolean southToNorth);
}




