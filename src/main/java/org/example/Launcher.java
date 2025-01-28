package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ServerForm.fxml"))));
        stage.centerOnScreen();
        stage.show();

        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ClientForm.fxml"))));
        secondStage.centerOnScreen();

        secondStage.show();

//        Stage thirdStage = new Stage();
//        thirdStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Client2.fxml"))));
//        thirdStage.centerOnScreen();
//
//        thirdStage.show();
    }
}
