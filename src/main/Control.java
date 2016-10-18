package main;

import data.Parameters;
import data.ParametersImpl;
import data.Model;
import ui.UI;
import ui.UIError;
import ui.UIForm;
import ui.UIFormBuilder;
import ui.UIFormTest;
import ui.UIMenu;
import ui.UIMenuBuilder;

class Control {
	private static final int MENU_EXITED = 0;
	private static final int MENU_EXIT = 1;
	private static final int MENU_RUN = 2;
	private static final int MENU_SETTINGS = 3;

	private UIMenu[] menus;
	private int state;
	private UIForm setSingleDouble;
	private UIForm setDoubleDouble;
	private UIForm setDoubleInt;
	private UIForm setPattern;
	private UIFormTest doubleTest;
	private UIFormTest numberTest;
	private UI ui;

	Control(UI ui) {
		this.ui = ui;

		this.menus = new UIMenu[4];
		this.state = MENU_RUN;
		addMenu(MENU_EXIT);
		addMenu(MENU_RUN);
		addMenu(MENU_SETTINGS);

		this.numberTest = (input) -> {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};

		this.doubleTest = (input) -> {
			try {
				Double.parseDouble(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};

		UIFormBuilder singleDouble = new UIFormBuilder();
		singleDouble.add("value:", this.doubleTest);
		this.setSingleDouble = singleDouble.toUIForm("Enter value: ");

		UIFormBuilder singleDouble2 = new UIFormBuilder();
		singleDouble2.add("value (default value is " + Parameters.runTime.getRandomValue() + "):", this.doubleTest);
		this.setSingleDouble = singleDouble2.toUIForm("Enter value: ");

		UIFormBuilder doubleInt = new UIFormBuilder();
		doubleInt.add("dimension (default value is " + Parameters.grid.getGridValue() + "):", this.numberTest);

		this.setDoubleInt = doubleInt.toUIForm("Enter value: ");

		UIFormBuilder setPattern = new UIFormBuilder();
		setPattern.add("1 for simple pattern and 2 for alternating: ", this.numberTest);
		this.setPattern = setPattern.toUIForm("Enter value: ");

		UIFormBuilder setDoubleDouble = new UIFormBuilder();
		setDoubleDouble.add("a minimum: ", this.numberTest);
		setDoubleDouble.add("a maximum: ", this.numberTest);
		this.setDoubleDouble = setDoubleDouble.toUIForm(": ");
	}

	void run() {
		try {
			while (this.state != MENU_EXITED) {
				this.ui.processMenu(this.menus[this.state]);
			}
		} catch (UIError e) {
			this.ui.displayError("UI exited!");
		}
	}

	private void addMenu(int menuNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		switch (menuNum) {
		case MENU_RUN:
			m.add("Default", () -> {
			});
			m.add("Run simulation", () -> {
				this.state = MENU_RUN;
				Model model = new Model();
				model.run();
				model.dispose();
			});
			m.add("Change simulation parameters", () -> this.state = MENU_SETTINGS);
			m.add("Exit", () -> this.state = MENU_EXIT);
			this.menus[menuNum] = m.toUIMenu("Simulation City");
			break;
		case MENU_SETTINGS:
			m.add("Default", () -> {
				this.ui.displayError("doh!");
			});

			m.add("Show current values", () -> {
				StringBuilder b = ParametersImpl.showCurrentValues();
				this.ui.displayMessage(b.toString());
			});

			m.add("Simulation time step", () -> {
				String[] result = this.ui.processForm(this.setSingleDouble);
				Parameters.timeStep.setRTValue(Double.parseDouble(result[0]));

			});

			m.add("Simulation runtime", () -> {
				String[] result = this.ui.processForm(this.setSingleDouble);
				Parameters.runTime.setRTValue(Double.parseDouble(result[0]));

			});

			m.add("Grid size", () -> {
				String[] result = this.ui.processForm(this.setDoubleInt);
				int[] dim = { Integer.parseInt(result[0]), Integer.parseInt(result[1]) };
				Parameters.grid.setGridValue(dim);

			});

			m.add("Set traffic pattern", () -> {
				String[] result = this.ui.processForm(this.setPattern);

				if (Integer.parseInt(result[0]) == 1) {
					Parameters.isAlternative.setPatternValue(false);
				} else {
					Parameters.isAlternative.setPatternValue(true);
				}
			});

			m.add("Set car entry rate", () -> {

				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.carGenerationDelay.setRangeValue(range);

			});

			m.add("Set road lengths", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.roadSegmentLength.setRangeValue(range);

			});

			m.add("Set intersection lengths", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.intersectionLength.setRangeValue(range);

			});

			m.add("Set car length", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.carLength.setRangeValue(range);

			});

			m.add("Set max car velocity", () -> {
				String[] result = this.ui.processForm(this.setSingleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.carMaxVelocity.setRangeValue(range);

			});

			m.add("Set car stop distance", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.carStopDistance.setRangeValue(range);

			});

			m.add("Set car break distance", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.carBrakeDistance.setRangeValue(range);

			});

			m.add("Set traffic light green times", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.trafficLightGreenTime.setRangeValue(range);

			});

			m.add("Set traffic light yellow times", () -> {
				String[] result = this.ui.processForm(this.setDoubleDouble);
				double[] range = { Double.parseDouble(result[0]), Double.parseDouble(result[1]) };
				Parameters.trafficLightYellowTime.setRangeValue(range);

			});

			m.add("Reset simulation and return to main menu", () -> {
				ParametersImpl.reset();

				this.state = MENU_RUN;
			});

			m.add("Return to main menu", () -> this.state = MENU_RUN);
			this.menus[menuNum] = m.toUIMenu("Traffic Simultion");
			break;
		case MENU_EXIT:
			m.add("Default", () -> {
			});
			m.add("Yes", () -> this.state = MENU_EXITED);
			m.add("No", () -> this.state = MENU_RUN);
			this.menus[menuNum] = m.toUIMenu("Are you sure you want to exit? ");
			break;

		default:
			break;
		}
	}
}
