package constructors;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import panel.Panel;

public class InOutPanels {

	public GridPane proceedRestricts(String minMaxComboBox, String restrictsComboBox, GridPane grid) {

		int width = 40;
		OutColors oC = new OutColors();
		for (int i = 0; i < Integer.parseInt(restrictsComboBox); i++) {

			Label restrictionNrLabel = new Label();
			Label factorA1Label = new Label();
			Label factorA2Label = new Label();
			TextField factorA1 = new TextField();
			TextField factorA2 = new TextField();
			TextField border = new TextField();

			Panel.restrictLabels.add(restrictionNrLabel);
			Panel.restrictLabels.add(factorA1Label);
			Panel.restrictLabels.add(factorA2Label);
			
			restrictionNrLabel.setStyle(oC.colorsS.get(i));

		
			Panel.restrictValues.add(factorA1);
			Panel.restrictValues.add(factorA2);
			Panel.restrictValues.add(border);
			
			factorA1.setPrefWidth(width);
			factorA2.setPrefWidth(width);
			border.setPrefWidth(width);
			String operator;

			if (minMaxComboBox == "MAX")
				operator = "<=";
			else
				operator = ">=";
			restrictionNrLabel.setText("NB" + i+1 + ": ");
			factorA1Label.setText("x + ");
			factorA2Label.setText("y " + operator);

			grid.add(restrictionNrLabel, 0, 2 + i+Panel.fR);
			grid.add(factorA1, 1, 2 + i+Panel.fR);
			grid.add(factorA1Label, 2, 2 + i+Panel.fR);
			grid.add(factorA2, 3, 2 + i+Panel.fR);
			grid.add(factorA2Label, 4, 2 + i+Panel.fR);
			grid.add(border, 5, 2 + i+Panel.fR);

		}

		
		return grid;
	}

	public GridPane proceesOutput(String c1, String c2, GridPane g) {

		// Noch zu implementieren
		return g;
	}
}
