package com.jmc.bitcart.Controllers;

import com.jmc.bitcart.DatabaseManger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    public Label login_email_lbl;
    public TextField login_email_fld;
    public Label login_password_lbl;
    public TextField login_password_fld;
    public Button login_btn;
    public Label register_name_lbl;
    public TextField register_name_fld;
    public Label register_email_lbl;
    public TextField register_email_fld;
    public Label register_phone_lbl;
    public TextField register_phone_fld;
    public Label register_password_lbl;
    public TextField register_password_fld;
    public Button register_btn;

    public void handleLoginClick() throws Exception {
        // Retrieve data from the text fields
        String email = login_email_fld.getText();
        String password = login_password_fld.getText();

        try {
            Connection con= DatabaseManger.getConnection();
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM  users");

            boolean isValid=false;
            while (resultSet.next()) {

                String CheckEmail=  resultSet.getString("email");
                String CheckPassword= resultSet.getString("password");
                if(CheckEmail.equals(email) && CheckPassword.equals(password)) {

                    System.out.println("You are logged in");
                    isValid=true;
                    break;

                }

            }
            if (isValid) {
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Success");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully logged in!");
                alert.showAndWait();
            } else {
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email or password.");
                alert.showAndWait();
            }


            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Add further logic (e.g., authentication)
    }
    public void handleRegisterClick() {
        // Retrieve data from the text fields
        String email = register_email_fld.getText();
        String password = register_password_fld.getText();
        String phone= register_phone_fld.getText();
        String name= register_name_fld.getText();
        System.out.println("email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Phone: " + phone);
        System.out.println("Name: " + name);
        // Add further logic (e.g., authentication)
    }
}

