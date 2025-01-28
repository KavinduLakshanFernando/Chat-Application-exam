package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    @FXML
    private TextArea txtAreaServer;

    @FXML
    private TextField txtServer;

    ServerSocket serverSocket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;
    String mg = "";


    public void initialize(){
        new Thread(()->{
            try{
                serverSocket = new ServerSocket(3000);
                txtAreaServer.appendText("server Started" + "\n");
                socket = serverSocket.accept();
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!socket.isClosed()){
                    mg = dataInputStream.readUTF();
                    txtAreaServer.appendText("Server :" + mg + "\n");
                }
                System.out.println("server"+mg);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @FXML
    void sendMassageOnAction(ActionEvent event) throws IOException {
        String message = txtServer.getText();
        txtAreaServer.appendText("Server: " + message + "\n");
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        txtServer.clear();
    }

}

