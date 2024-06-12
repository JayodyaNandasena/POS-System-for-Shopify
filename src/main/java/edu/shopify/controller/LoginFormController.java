package edu.shopify.controller;

import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.db.StageManager;
import edu.shopify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtEmail;
    public TextField txtPassword;

    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void btnLoginOnAction(ActionEvent actionEvent) {

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        try {
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter Login Credentials!");
                alert.show();
                return;
            }

            Boolean isValid = employeeBo.validateLogin(email, password);

            if (Boolean.FALSE.equals(isValid)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Login Credentials!");
                alert.show();
                return;
            }

            Stage currentStage= StageManager.getCurrentStage();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/manage-employees-form.fxml"));
            Parent root;
            try {
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                if (currentStage != null) {
                    currentStage.close();
                }
                StageManager.setCurrentStage(stage);

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter Login Credentials! null");
            alert.show();
        }


    }

    public void txtForgotPasswordOnClick(MouseEvent mouseEvent) {
        Stage currentStage= StageManager.getCurrentStage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/reset-password-form.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            if (currentStage != null) {
                currentStage.close();
            }

            StageManager.setCurrentStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}
