package data;

import agent.TimeServer;
import agent.TimeServerLinked;

import swing.AnimatorBuilder;
import swing.SwingAnimatorBuilder;
import util.Animator;

public final class Model {

	private AnimatorBuilder animatorbuilder;
	private Animator animator;
	private TimeServer timeserver;
	private boolean isAlternative;
	private boolean disposed;

	public Model() {
		animatorbuilder = new SwingAnimatorBuilder();
		timeserver = TimeServerLinked.getInstance();
		isAlternative = Parameters.isAlternative.getPatternValue();
		generateGrid(Parameters.grid.getGridValue());
		animator = animatorbuilder.getAnimator();
		timeserver.addObserver(animator);
	}
	
	
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	public void run() {
		if (disposed)
			throw new IllegalStateException();
		timeserver.run(Parameters.runTime.getRandomValue());
	}

	private void generateGrid(int[] grdDimension) {
		int row = grdDimension[0];
		int col = grdDimension[1];
		TrafficLightController[][] eastWest = new TrafficLightController[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				eastWest[i][j] = new TrafficLightController();
			}
		}
		TrafficLightController[][] northSouth = rotate(eastWest);
		generateSegments(row, col, eastWest, northSouth);
	}

	private void generateSegments(int row, int col, TrafficLightController[][] eastWest,
			TrafficLightController[][] northSouth) {
		if (isAlternative) {
			isAlternative = false;
			for (int i = 0; i < row; i++) {
				generateAlternative(eastWest, i, FaceDirection.EAST_TO_WEST);
				isAlternative = !isAlternative;
			}
			isAlternative = false;
			for (int i = 0; i < col; i++) {
				generateAlternative(northSouth, i, FaceDirection.NORTH_TO_SOUTH);
				isAlternative = !isAlternative;
			}

		}
	}

	private void generateAlternative(TrafficLightController[][] lc, int i, FaceDirection d) {
		if (isAlternative) {
			AlternativePattern revSeg = new AlternativePattern(lc[i], d, i, animatorbuilder);
			revSeg.generateSegment();
		} else {
			SimplePattern regSeg = new SimplePattern(lc[i], d, i, animatorbuilder);
			regSeg.generateSegment();
		}
	}

	private TrafficLightController[][] rotate(TrafficLightController[][] orig) {
		TrafficLightController[][] value = new TrafficLightController[orig[0].length][orig.length];
		for (int i = 0; i < orig[0].length; i++) {
			for (int j = 0; j < orig.length; j++) {
				value[i][j] = orig[j][orig[0].length - i - 1];
			}
		}
		return value;
	}

	
}
