import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class showGame extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:/data/hello-world.fxml"));
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

*/
        /*
        // Create a new pane to hold our GUI
        FlowPane root = new FlowPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        // Create a new label
        Label l = new Label("Hello, I'm alive!");
        root.getChildren().add(l);
        //Create a scene based on the pane.
        Scene scene = new Scene(root, 400, 400);
        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();

         */
        //Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
/*
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        for(int i = 0; i < boardTemp.length; i++){
            for (int j = 0; j < boardTemp[i].length; j++) {
                InputStream stream = new FileInputStream(boardTemp[i][j].imgPath);
                Image image = new Image(stream);
                //Creating the image view
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(50);
                imageView.setPreserveRatio(true);
                gridPane.add(imageView, i, j, 1, 1);
            }
        }


        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setTitle("Displaying Image");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
}