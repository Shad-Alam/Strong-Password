package com.example.strongpassword;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    public TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // password copy to clipboard by using button click
    public void copy(ActionEvent actionEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(tf_password.getText());
        content.putHtml("<b>Some</b> text");
        clipboard.setContent(content);
    }
}
