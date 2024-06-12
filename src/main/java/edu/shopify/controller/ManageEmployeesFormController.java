package edu.shopify.controller;

import dto.Employee;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.util.BoType;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ManageEmployeesFormController {

    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtEmpId;
    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void btnSerchOnAction(ActionEvent actionEvent) {
        Employee employee = employeeBo.searchEmployee(txtEmpId.getText());
        if(employee == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Employee Id!");
            alert.show();
            return;
        }

        txtEmpId.setText(employee.getEmployeeId());
        txtFirstName.setText(employee.getFirstName());
        txtLastName.setText(employee.getLastName());
        txtAddress.setText(employee.getAddress());
        txtMobile.setText(employee.getMobile());
        txtEmail.setText(employee.getEmail());
        txtPassword.setText(employee.getPassword());

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee(
                txtEmpId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtAddress.getText(),
                txtMobile.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                true
                );

        if(Boolean.TRUE.equals(employeeBo.saveEmployee(employee))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employee Added Successfully!");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Error Adding new Employee!");
        alert.show();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee(
                txtEmpId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtAddress.getText(),
                txtMobile.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                true
        );

        if(Boolean.TRUE.equals(employeeBo.updateEmployee(employee))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employee Updated Successfully!");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Error Updating Employee!");
        alert.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if(employeeBo.deleteEmployee(txtEmpId.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employee Deleted Successfully!");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error deleting employee!");
        alert.show();
    }

    public void navDashbordOnClick(MouseEvent mouseEvent) {
    }

    public void navPlaceOrderOnClick(MouseEvent mouseEvent) {
    }

    public void navReturnItemsOnClick(MouseEvent mouseEvent) {
    }

    public void navProductsOnClick(MouseEvent mouseEvent) {
    }

    public void navSuppliersOnClick(MouseEvent mouseEvent) {
    }

    public void navReportsOnClick(MouseEvent mouseEvent) {
    }
}
