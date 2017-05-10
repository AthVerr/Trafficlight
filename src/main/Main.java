package main;

import ui.UI;
import ui.UIFactory;

public class Main {

	public static void main(String[] args) {

		UI ui = UIFactory.ui();
		Control controller = new Control(ui);
		controller.run();

	}

}
