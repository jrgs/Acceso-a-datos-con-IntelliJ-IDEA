package com.example.javafx.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label btnLogin;
    
    @FXML
    private TextField txtUser;
    
    @FXML 
    private TextField txtPassword;

    @FXML
    private Label errorLabel;

    @FXML
    protected void onBtnLoginClick() {

        if ( validarUsuario(txtUser.getText(), txtPassword.getText()) == false ) {
            errorLabel.setText("Usuario o contraseña incorrectos");
            errorLabel.setVisible( true );
        }
    }

    private boolean validarUsuario(String text, String text1) {
        // Aquí deberíamos implementar el código necesario para autenticar a un usuario
        return true;
    }
}