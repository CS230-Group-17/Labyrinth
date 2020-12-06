package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Menu {
    @FXML private ComboBox levelSelectCb;

    @FXML
    public void showLevelSelect() {
        levelSelectCb.setVisible(true);
    }
}
