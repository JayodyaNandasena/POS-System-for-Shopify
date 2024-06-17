package edu.shopify.controller;

import com.jfoenix.controls.JFXButton;
import edu.shopify.db.StageManager;
import edu.shopify.dto.Employee;
import edu.shopify.dto.tm.EmployeeTM;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    public JFXButton btnAddAgain;
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

        if (employee.getIsActive() == false){
            btnAddAgain.setDisable(false);
        }else{
            btnAddAgain.setDisable(true);
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent){
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

        try{
           if(Boolean.TRUE.equals(employeeBo.saveEmployee(employee))){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Employee Added Successfully!");
                alert.show();
                loadTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Employee Id Already Exists!");
            alert.show();
        }
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
        if(Boolean.TRUE.equals(employeeBo.deleteEmployee(txtEmpId.getText()))){
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
    public void btnAddAgainOnAction(ActionEvent actionEvent) {
        if(employeeBo.readdEmployee(txtEmpId.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Employee Added Successfully!");
            alert.show();
            loadTable();
            btnAddAgain.setDisable(true);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error adding employee!");
        alert.show();
        btnAddAgain.setDisable(true);
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
        Stage currentStage= StageManager.getCurrentStage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/manage-suppliers-form.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            if (currentStage != null) {
                currentStage.close(); // Close the current stage if it exists
            }

            StageManager.setCurrentStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            System.out.println(e.getMessage());
        }
    }

    public void navReportsOnClick(MouseEvent mouseEvent) {
    }



}
