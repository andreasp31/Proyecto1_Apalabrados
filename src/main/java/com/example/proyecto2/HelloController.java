package com.example.proyecto2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void iniciar(ActionEvent event) throws IOException {
        Parent nuevaVista = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("juego.fxml")));
        Scene nuevaEscena = new Scene(nuevaVista);

        // Obtener el escenario actual
        Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
        escenario.setScene(nuevaEscena);
        escenario.show();
    }
}