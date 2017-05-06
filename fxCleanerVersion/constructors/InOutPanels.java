package constructors;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InOutPanels {

	public GridPane proceedRestricts(String minMaxComboBox, String restrictsComboBox, GridPane grid) {

		int width = 40;

		for (int i = 0; i < Integer.parseInt(restrictsComboBox); i++) {

			Label restrictionNrLabel = new Label();
			Label factorA1Label = new Label();
			Label factprA2Label = new Label();
			TextField factorA1 = new TextField();
			TextField factorA2 = new TextField();
			TextField border = new TextField();

			factorA1.setPrefWidth(width);
			factorA2.setPrefWidth(width);
			border.setPrefWidth(width);
			String operator;

			if (minMaxComboBox == "MAX")
				operator = "<=";
			else
				operator = ">=";
			restrictionNrLabel.setText("NB" + i + ": ");
			factorA1Label.setText("x + ");
			factprA2Label.setText("y " + operator);

			grid.add(restrictionNrLabel, 0, 2 + i);
			grid.add(factorA1, 1, 2 + i);
			grid.add(factorA1Label, 2, 2 + i);
			grid.add(factorA2, 3, 2 + i);
			grid.add(factprA2Label, 4, 2 + i);
			grid.add(border, 5, 2 + i);

		}

		Button fireButton = new Button();
		fireButton.setText("Calculate");
		fireButton.setPrefWidth(60);
		grid.add(fireButton, 5, Integer.parseInt(restrictsComboBox) + 2);
		return grid;
	}

	public GridPane proceesOutput(String c1, String c2, GridPane g) {

		// Noch zu implementieren
		return g;
	}
}
