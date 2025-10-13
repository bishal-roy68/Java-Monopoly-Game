package com.example.monopoly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartController {

    @FXML
    protected void onNewGameClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("monopoly-view.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Monopoly Game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLoadClicked(ActionEvent event) {
        onNewGameClicked(event);
    }
}

