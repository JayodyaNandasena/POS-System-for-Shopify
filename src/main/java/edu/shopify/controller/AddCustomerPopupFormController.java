package edu.shopify.controller;

import com.jfoenix.controls.JFXButton;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.CustomerBo;
import edu.shopify.dto.Category;
import edu.shopify.dto.Customer;
import edu.shopify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerPopupFormController implements Initializable {

    public TextField txtMobile;
    public TextField txtEmail;
    public JFXButton btnCancelCustomer;
    public TextField txtFirstName;
    public TextField txtLastName;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public AddCustomerPopupFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        closeWindow();
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        try {

            System.out.println("Next id = "+generateId());

            Customer customer = new Customer(
                    generateId(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtMobile.getText(),
                    txtEmail.getText()
            );

            customerBo.saveCustomer(customer);

            closeWindow();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private String generateId() {
        try{
            String lastId = customerBo.getLastId();

            if (lastId == null){
                return "C0001";
            }

            int numericPart = Integer.parseInt(lastId.substring(1));
            numericPart++;
            return String.format("C%04d", numericPart);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    private void closeWindow(){
        Stage stage = (Stage) btnCancelCustomer.getScene().getWindow();
        stage.close();
    }


}
