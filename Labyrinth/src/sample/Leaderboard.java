package sample;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;


public class Leaderboard {
    @FXML private TableView playerTable;
    @FXML private AnchorPane leaderPane;
    @FXML private ComboBox mapsBox;

    //style="-fx-background-color: PINK;"
    @FXML
    public void initialize(){
        mapsBox.getItems().add("map1");
        mapsBox.getItems().add("map2");
        mapsBox.getItems().add("map2");


        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn surnameColumn = new TableColumn("Wins");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Wins"));

        TableColumn lossColumn = new TableColumn("Losses");
        lossColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));

        playerTable.getColumns().addAll(nameColumn, surnameColumn, lossColumn);
    }


    @FXML
    private void backBtn() throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        leaderPane.getChildren().setAll(temp);
    }

    @FXML
    private void loadMaps() {
/*
        ArrayList<PlayerProfile> playerProfiles = FileSystem.getPlayerProfilesFromLeaderboard(mapsBox.getValue() + ".txt");
        PlayerProfile[] playerArr = new PlayerProfile[playerProfiles.size()];

        for(int i = 0; i < playerProfiles.size(); i++) {
            playerArr[i] = playerProfiles.get(i);
        }

        int n = playerArr.length;
        //bubble sort...
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (playerArr[j].getWins() < playerArr[j + 1].getWins()) {
                    // swap arr[j+1] and arr[j]
                    PlayerProfile temp2 = playerArr[j];
                    playerArr[j] = playerArr[j + 1];
                    playerArr[j + 1] = temp2;
                }


        for(int i = 0; i < playerArr.length; i++) {
            playerTable.getItems().add(playerArr[i]);
        }
*/

    }





}
