package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import data.Car;
import data.LightColor;
import data.TrafficLight;

import data.Road;
import data.Parameters;
import util.Animator;
import util.SwingAnimator;
import util.SwingAnimatorPainter;




/**
 * A class for building Animators.
 */
public class SwingAnimatorBuilder implements AnimatorBuilder {
	MyPainter _painter;

	public SwingAnimatorBuilder() {
		_painter = new MyPainter();
	}

	public Animator getAnimator() {
		if (_painter == null) {
			throw new IllegalStateException();
		}
		Animator returnValue = new SwingAnimator(_painter, "Traffic Simulator",
				VP.displayWidth, VP.displayHeight, VP.displayDelay);
		_painter = null;
		return returnValue;
	}

	private static double skipInit = VP.gap;
	private static double skipRoad = VP.gap + Parameters.roadSegmentLength_grafic.getRandomValue();
	
	private static double skipCar = VP.gap + VP.elementWidth;
	private static double skipRoadCar = skipRoad + skipCar;

	public void addLight(TrafficLight d, int i, int j) {
		double x = skipInit + skipRoad + j * skipRoadCar;
		double y = skipInit + skipRoad + i * skipRoadCar;
		Translator t = new TranslatorWE(x, y, Parameters.carLength_grafic.getRandomValue(),
				VP.elementWidth, VP.scaleFactor);
		_painter.addLight(d, t);
	}

	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		double x = skipInit + j * skipRoadCar;
		double y = skipInit + skipRoad + i * skipRoadCar;
		Translator t = eastToWest ? new TranslatorEW(x, y,
				Parameters.roadSegmentLength_grafic.getRandomValue(), VP.elementWidth, VP.scaleFactor)
				: new TranslatorWE(x, y, Parameters.roadSegmentLength_grafic.getRandomValue(),
						VP.elementWidth, VP.scaleFactor);
		_painter.addRoad(l, t);
	}

	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		double x = skipInit + skipRoad + j * skipRoadCar;
		double y = skipInit + i * skipRoadCar;
		Translator t = southToNorth ? new TranslatorSN(x, y,
				Parameters.roadSegmentLength_grafic.getRandomValue(), VP.elementWidth, VP.scaleFactor)
				: new TranslatorNS(x, y, Parameters.roadSegmentLength_grafic.getRandomValue(),
						VP.elementWidth, VP.scaleFactor);
		_painter.addRoad(l, t);
	}

	/** Class for drawing the Model. */
	private static class MyPainter implements SwingAnimatorPainter {

		/**
		 * Pair of a model element <code>x</code> and a translator
		 * <code>t</code>.
		 */
		private static class Element<T> {
			T x;
			Translator t;

			Element(T x, Translator t) {
				this.x = x;
				this.t = t;
			}
		}

		private List<Element<Road>> _roadElements;
		private List<Element<TrafficLight>> _lightElements;

		MyPainter() {
			_roadElements = new ArrayList<Element<Road>>();
			_lightElements = new ArrayList<Element<TrafficLight>>();
		}

		void addLight(TrafficLight x, Translator t) {
			_lightElements.add(new Element<TrafficLight>(x, t));
		}

		void addRoad(Road x, Translator t) {
			_roadElements.add(new Element<Road>(x, t));
		}

		public void paint(Graphics g) {
			// This method is called by the swing thread, so may be called
			// at any time during execution...

			// First draw the background elements
			for (Element<TrafficLight> e : _lightElements) {
				if (e.x.getColor() == LightColor.GREEN) {
					g.setColor(Color.GREEN);
				} else if (e.x.getColor() == LightColor.YELLOW) {
					g.setColor(Color.YELLOW);
				} else if (e.x.getColor() == LightColor.RED) {
					g.setColor(Color.RED);
				}
				XGraphics.fillOval(g, e.t, 0, 0, Parameters.carLength_grafic.getRandomValue(),
						VP.elementWidth);
			}
			g.setColor(Color.BLACK);
			for (Element<Road> e : _roadElements) {
				XGraphics.fillRect(g, e.t, 0, 0, Parameters.roadSegmentLength_grafic.getRandomValue(),
						VP.elementWidth);
			}

			// Then draw the foreground elements
			for (Element<Road> e : _roadElements) {
				// iterate through a copy because e.x.getCars() may change
				// during iteration...
				double roadLength = e.x.getLength();
				Iterator<Car> it = e.x.getCars().iterator();
				while (it.hasNext()) {
					Car d = it.next();
					g.setColor(d.getColor());
					double normalizedPosition = d.getFrontPosition()
							/ roadLength;
					XGraphics.fillOval(g, e.t, normalizedPosition
							* Parameters.roadSegmentLength_grafic.getRandomValue(), 0, d.getLength(),
							VP.elementWidth);
				}
			}
		}
	}
}
