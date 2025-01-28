package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {
    @FXML
    private TextArea txtAreaClient;

    @FXML
    private TextField txtClient;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String mg = "";

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                txtAreaClient.appendText("Client Started\n");

                while (!socket.isClosed()) {
                    mg = dataInputStream.readUTF();
                    txtAreaClient.appendText("Server: " + mg + "\n");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
    @FXML
    void SendMassageOnAction(ActionEvent event) throws IOException {
        String message = txtClient.getText();
        txtAreaClient.appendText("Client: " + message + "\n");
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        txtClient.clear();

    }

}

