package org.example.giocodelloca;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RulesController {
    @FXML
    private GridPane rulesGrid;

    @FXML
    private void initialize() {
        for (int i = 0; i < 63; i++) {
            TextArea ruleArea = new TextArea("Effetto casella " + (i + 1));
            rulesGrid.add(new Label("Casella " + (i + 1)), 0, i);
            rulesGrid.add(ruleArea, 1, i);
        }
    }

    @FXML
    private void saveRules() {
        System.out.println("Regole salvate!");
    }
}
