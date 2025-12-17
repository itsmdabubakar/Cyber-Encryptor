package com.example.cyberencryptor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. SETUP THE COMPONENTS
        Label lblInput = new Label("1. Secret Message:");
        TextArea txtInput = new TextArea();
        txtInput.setPromptText("Type what you want to hide...");
        txtInput.setPrefHeight(100);

        Label lblKey = new Label("2. Secret Password:");
        TextField txtKey = new TextField();
        txtKey.setPromptText("e.g. MyPassword123");

        Button btnEncrypt = new Button("🔒 Encrypt");
        Button btnDecrypt = new Button("🔓 Decrypt");

        // Make buttons full width for better look
        btnEncrypt.setMaxWidth(Double.MAX_VALUE);
        btnDecrypt.setMaxWidth(Double.MAX_VALUE);

        Label lblOutput = new Label("3. Result:");
        TextArea txtOutput = new TextArea();
        txtOutput.setEditable(false); // User shouldn't type here
        txtOutput.setPrefHeight(100);

        // 2. CONNECT BUTTONS TO LOGIC
        btnEncrypt.setOnAction(e -> {
            String msg = txtInput.getText();
            String key = txtKey.getText();

            if(!key.isEmpty()) {
                String result = AESUtils.encrypt(msg, key);
                txtOutput.setText(result);
            } else {
                txtOutput.setText("Please enter a password first!");
            }
        });

        btnDecrypt.setOnAction(e -> {
            String msg = txtInput.getText();
            String key = txtKey.getText();

            if(!key.isEmpty()) {
                String result = AESUtils.decrypt(msg, key);
                txtOutput.setText(result);
            } else {
                txtOutput.setText("Please enter a password first!");
            }
        });

        // 3. LAYOUT (Stacking items nicely)
        VBox layout = new VBox(10); // 10px spacing between items
        layout.setPadding(new Insets(20)); // 20px padding around the window
        layout.getChildren().addAll(
                lblInput, txtInput,
                lblKey, txtKey,
                btnEncrypt, btnDecrypt,
                lblOutput, txtOutput
        );

        // 4. LAUNCH WINDOW
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setTitle("JavaFX Cyber Encryptor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}