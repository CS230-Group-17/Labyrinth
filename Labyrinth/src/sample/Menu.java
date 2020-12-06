package sample;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Menu {
    @FXML private ComboBox levelSelectCb;
    @FXML private ComboBox playerdd1;
    @FXML private ComboBox playerdd2;
    @FXML private ComboBox playerdd3;
    @FXML private ComboBox playerdd4;
    @FXML private ComboBox deleteProfiledd;
    @FXML private TextField newPlayerTxt;
    @FXML private Label playerCount;
    @FXML private Label messageOfTheDay;
    @FXML private AnchorPane anchPane;
    private String[] playerProfiles;
    public static Game theGame;


    @FXML
    public void showLevelSelect() {
        FileSystem.getAllPlayerProfiles();
        levelSelectCb.getItems().clear();
        if (levelSelectCb.isVisible() == false)
        {
            levelSelectCb.setVisible(true);
        }
        File curDir = new File("src\\sample\\Maps\\NewMaps");
        String[] pathnames = curDir.list();

        for (String pathname : pathnames) {
            levelSelectCb.getItems().add(pathname.substring(0, pathname.length()-4));
        }
    }

    @FXML
    private void showProfiles(){
        File file = new File("PlayerProfiles");
        ObservableList<String> files = FXCollections.observableArrayList(file.list());
        playerdd1.setItems(files);
        playerdd2.setItems(files);
        playerdd3.setItems(files);
        playerdd4.setItems(files);
        deleteProfiledd.setItems(files);
    }

    @FXML
    private void createProfile() {
        //FileSystem.createPlayerProfile(newPlayerTxt.getText());
    }

    @FXML
    private void showGameSelect() {
        levelSelectCb.getItems().clear();
        if (levelSelectCb.isVisible() == false)
        {
            levelSelectCb.setVisible(true);
        }
        File curDir = new File("src\\sample\\Maps\\SavedMaps");
        String[] pathnames = curDir.list();

        for (String pathname : pathnames) {
            levelSelectCb.getItems().add(pathname.substring(0, pathname.length()-4));
        }
    }
    @FXML
    private void showProfileTest() {

    }

    @FXML
    private void updatePlayerCount()
    {
        int count = 0;
        if (playerdd1.getValue() != null )
        {
            count++;
        }
        if (playerdd2.getValue() != null )
        {
            count++;
        }
        if (playerdd3.getValue() != null )
        {
            count++;
        }
        if (playerdd4.getValue() != null )
        {
            count++;
        }
        playerCount.setText(count+" Players");
    }

    @FXML
    private void clearPlayerdd1()
    {
        playerdd1.setValue(null);
    }
    @FXML
    private void clearPlayerdd2()
    {
        playerdd2.setValue(null);
    }
    @FXML
    private void clearPlayerdd3()
    {
        playerdd3.setValue(null);
    }
    @FXML
    private void clearPlayerdd4()
    {
        playerdd4.setValue(null);
    }

    @FXML
    private void deletePlayerProfile()
    {
        File file = new File("PlayerProfiles\\" +deleteProfiledd.getValue());
        file.delete();
        deleteProfiledd.setValue(null);
    }

    @FXML
    private void chosenLevel() throws IOException {
        String file = levelSelectCb.getValue() + ".txt";
        levelSelectCb.setVisible(false);
        //Game theGame = null;//FileSystem.readNewGame(file, playerProfiles);
        AnchorPane temp = FXMLLoader.load(getClass().getResource("sample.fxml"));
        anchPane.getChildren().setAll(temp);
    }

    @FXML
    private void showLeader() throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        anchPane.getChildren().setAll(temp);
    }

    @FXML
    public void initialize() throws IOException{
        String s= getMessageOfTheDay();
        messageOfTheDay.setText(s) ;
    }

    private static String getMessageOfTheDay() throws IOException
    {
        //Getting the raw message data
        URL url = new URL ("http://cswebcat.swansea.ac.uk/puzzle");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String input;
        StringBuffer response = new StringBuffer();
        while ((input = in.readLine())!=null)
        {
            response.append(input);
        }
        in.close();
        String puzzleCode = response.toString();

        //Caesar Cipher
        String s = shift(puzzleCode);//do the weird cipher

        //Formating
        int len = puzzleCode.length();
        puzzleCode = "CS-230" + s + (len+6); // s is the shifted puzzleCode

        //Get Solved Code
        URL url2 = new URL ("http://cswebcat.swansea.ac.uk/message?solution=" + puzzleCode);
        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
        con2.setRequestMethod("GET");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(
                con2.getInputStream()));
        String input2;
        StringBuffer response2 = new StringBuffer();
        while ((input2 = in2.readLine())!=null)
        {
            response2.append(input2);
        }
        in2.close();
        String Message = response2.toString();
        return Message;
    }

    public static String shift(String text)
    {
        StringBuffer result= new StringBuffer();
        for (int i=1; i<=text.length(); i++)
        {
            int shift = 0;
            // setting shift values
            if (i % 2 ==0)
            {
                shift = i; // if placement = even
            }else
            {
                shift = -i; // else odd
            }

            //shift the char value
            int charAscii = (int)text.charAt(i-1);
            if(charAscii + shift > 90)
            {
                charAscii = ((charAscii+shift)-90) + 64;
            }
            else if (charAscii + shift < 65)
            {
                charAscii =  91- (65-(charAscii + shift));
            }
            else charAscii += shift;
            result.append((char)charAscii);
        }
        return result.toString();
    }
}