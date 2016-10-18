package swing;

import util.Animator;

/**
 * Null object for {@link Animator}.
 */
class NullAnimator implements Animator {
  public void update(java.util.Observable o, Object arg) { }
  public void dispose() { }
}
