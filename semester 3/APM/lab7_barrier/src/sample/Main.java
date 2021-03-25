package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader secondLoader=new FXMLLoader();
        secondLoader.setLocation(getClass().getResource("SecondWindow.fxml"));
        Parent secondWindow=secondLoader.load();
        SecondWindowController secondWindowController=secondLoader.getController();
        primaryStage.setTitle("Run the selected program");
        primaryStage.setScene(new Scene(secondWindow));
        primaryStage.show();

        FXMLLoader mainLoader=new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("MainWindow.fxml"));
        Parent mainWindow=mainLoader.load();
        MainWindowController mainWindowController= mainLoader.getController();
        mainWindowController.setSecondWindow(secondWindowController);
        Stage secondStage=new Stage();
        secondStage.setTitle("Select a program");
        secondStage.setScene(new Scene(mainWindow));
        secondStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
