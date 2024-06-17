package edu.shopify.controller;

import edu.shopify.dto.Employee;
import edu.shopify.dto.tm.EmployeeTM;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeesFormController implements Initializable {

    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtEmpId;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public TableView tblEmployees;
    private EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public ManageEmployeesFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();

    }
    public void btnSearchOnAction(ActionEvent actionEvent) throws Exception {
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

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
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
            loadTable();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Employee Id Already Exists!");
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
            loadTable();
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
            loadTable();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error deleting employee!");
        alert.show();
    }

    private void loadTable(){
        ObservableList<EmployeeTM> employeeTableData = FXCollections.observableArrayList();

        employeeBo.getAllEmployees().forEach(employee -> {
            EmployeeTM employeeTm = new EmployeeTM(
                    employee.getEmployeeId(),
                    employee.getFirstName()+" "+employee.getLastName(),
                    employee.getAddress(),
                    employee.getMobile(),
                    employee.getEmail()
            );
            employeeTableData.add(employeeTm);
        });
        tblEmployees.setItems(employeeTableData);
    }






//Methods to navigate between pages
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
