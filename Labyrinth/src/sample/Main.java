package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) throws FileNotFoundException {

        String paths = "C:\\Users\\marij\\Downloads\\aa.png";
        Image tileimage = new Image(new FileInputStream(paths));
        //board
        FloorTile[][] boardTemp = {{new FloorTile(paths, tileimage, true, false, true, true, false, "L Shape"), new FloorTile(paths, tileimage, false, false, true, false, true, "- Shape"), new FloorTile(paths, tileimage, true, false, false, true, true, "re L Shape")},
                {new FloorTile(paths, tileimage,false, false, true, true, true, "T Shape"), new FloorTile(paths, tileimage,false, true, true, true, true, "+ Shape"), new FloorTile(paths, tileimage, false, true, false, true, true, "T  Shape")},
                {new FloorTile(paths, tileimage, true, true, true, false, false, "L Shape"), new FloorTile(paths, tileimage,false, true, false, true, false, "I Shape"), new FloorTile(paths, tileimage, true, true, false, false, true, "L Shape")},
                {new FloorTile(paths, tileimage, true, true, true, false, false, "L Shape"), new FloorTile(paths, tileimage,false, true, false, true, false, "I Shape"), new FloorTile(paths, tileimage, true, true, false, false, true, "L Shape")}

        };

        ArrayList<Tile> silkbag = new ArrayList<Tile>();

        Image silkImage = new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\qq.png"));
        silkbag.add(new FloorTile("C:\\Users\\marij\\Downloads\\qq.png", silkImage,false, true, true, true, true, "+"));
        silkbag.add(new FloorTile("C:\\Users\\marij\\Downloads\\qq.png", silkImage,false, true, true, true, true, "+"));
        silkbag.add(new FloorTile("C:\\Users\\marij\\Downloads\\qq.png", silkImage,false, true, true, true, true, "+"));
        silkbag.add(new Ice("C:\\Users\\marij\\Downloads\\ice.png", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\ice.png")), "Ice"));
        //silkbag.add(new DoubleMove("path"));
        PlayerProfile bob = new PlayerProfile(0, 1, "bob", 1);
        PlayerProfile sam = new PlayerProfile(1, 0, "sam", 1);


        Image playerImg = new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\stick.png"));
        Player player1 = new Player("C:\\Users\\marij\\Downloads\\stick.png", bob, boardTemp[1][2],playerImg);
        player1.addHand(new Fire("C:\\Users\\marij\\Downloads\\fire.png", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\fire.png")), "Fire"));

        player1.addHand(new Ice("C:\\Users\\marij\\Downloads\\ice.png", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\ice.png")), "ice"));
        player1.addHand(new DoubleMove("C:\\Users\\marij\\Downloads\\1.jpg", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\1.jpg")), "Double"));
        player1.addHand(new Backtrack("C:\\Users\\marij\\Downloads\\back.png", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\back.png")), "backTrack"));

        Player player2 = new Player("C:\\Users\\marij\\Downloads\\stick.png", sam, boardTemp[2][2], playerImg);
        player2.addHand(new DoubleMove("C:\\Users\\marij\\Downloads\\1.jpg", new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\1.jpg")), "Double"));

        //Image playerImg = new Image(new FileInputStream("C:\\Users\\marij\\Downloads\\stick.png"));
        //Player player3 = new Player("C:\\Users\\marij\\Downloads\\stick.png", bob, boardTemp[2][2],playerImg);

        Player[] playersArr = {player1, player2};
        Game itsAGame = new Game(playersArr, boardTemp, boardTemp[1][1], silkbag, 0, 2);

        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = (Pane) FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root, 700, 700);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
