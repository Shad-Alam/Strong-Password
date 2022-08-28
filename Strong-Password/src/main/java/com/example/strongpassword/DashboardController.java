package com.example.strongpassword;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;


public class DashboardController implements Initializable {
    public TextField tf_digit;
    char[] i = new char[1111];
    char[] number = new char[10];
    char[] special = new char[1111];
    char[] lowerCase = new char[1111];
    char[] upperCase = new char[1111];
    int p4 = 0, p=0, p1=0, p2=0, p3=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int a=48; a<=57; a++){
            number[p4] = (char) a; p4++;
            i[p] = (char) a; p++;
        }

        for(int a=123; a<=126; a++){
            special[p1] = (char) a; p1++;
            i[p] = (char) a; p++;
        }

        for(int a=33; a<=47; a++){
            special[p1] = (char) a; p1++;
            i[p] = (char) a; p++;
        }

        for(int a=91; a<=94; a++){
            special[p1] = (char) a; p1++;
            i[p] = (char) a; p++;
        }

        for(int a=58; a<=64; a++){
            special[p1] = (char) a; p1++;
            i[p] = (char) a; p++;
        }

        for(int a=97; a<=122; a++){
            lowerCase[p2] = (char) a; p2++;
            i[p] = (char) a; p++;
        }

        for(int a=65; a<=90; a++) {
            upperCase[p3] = (char) a;
            p3++; i[p] = (char) a; p++;
        }
    }

    public boolean isNumeric(String i){
        int c=0;
        for (int a=0; a<i.length(); a++){
            if(i.charAt(a)>='0' && i.charAt(a)<='9'){
                c++;
            }else{
                return false;
            }
        }
        return c==i.length() ? true : false;
    }

    public void generate(ActionEvent actionEvent) {
        Random random = new Random();
        String digit = tf_digit.getText();

        if(digit.length()==0 || digit.isEmpty() || !isNumeric(digit)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input field invalid.");
            alert.setContentText("Type a valid number.");
            alert.showAndWait();
            return;
        }

        int n = Integer.parseInt(digit);
        if(n>5){
            String password = "";

            char[] password1= new char[10];

            password1[0] =  i[random.nextInt(p)];
            password1[1] =  upperCase[random.nextInt(p3)];
            password1[2] =  special[random.nextInt(p1)];
            password1[3] =  upperCase[random.nextInt(p2)];
            password1[4] =  number[random.nextInt(p4)];
            password1[5] =  i[random.nextInt(p)];

            int r1 = random.nextInt(6);
            char sw = password1[r1];
            password1[r1] = password1[4];
            password1[4] = sw;

            int r2 = random.nextInt(6);
            int r3 = random.nextInt(6);
            char sp = password1[r2];
            password1[r2] = password1[r3];
            password1[r3] = sp;

            for(int a=0; a<6; a++){
                password+=(char) password1[a];
            }

            n-=6;
            int sm = 0;
            for(int a=0; a<n; a++){
                if(sm==0){
                    password+= (char) i[random.nextInt(p)];
                    sm++;
                }else if(sm<=2){
                    password+= (char) special[random.nextInt(p1)];
                    sm++;
                }else if(sm==3){
                    password+= (char) i[random.nextInt(p)];
                    sm++;
                }else if(sm==4){
                    password+= (char) lowerCase[random.nextInt(p2)];
                    sm++;
                }else{
                    password+= (char) upperCase[random.nextInt(p3)];
                    sm++;
                }
                if(sm==6){
                    sm = 0;
                }
            }

            try {
                final Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fxm = new FXMLLoader(getClass().getResource("password.fxml"));
                Parent parent = (Parent) fxm.load();
                Scene scene = new Scene(parent, 1000, 100);
                stage.setScene(scene);
                stage.show();

                PasswordController passwordController = fxm.getController();
                passwordController.tf_password.setText(password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your password size is smaller than 6.");
            alert.setContentText("Type a number greater than 5.");
            alert.showAndWait();
        }
    }

    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Recommended password size is greater than 8\n");
        alert.setContentText("Good Luck");
        alert.showAndWait();
    }
}
