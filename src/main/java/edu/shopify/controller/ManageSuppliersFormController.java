package edu.shopify.controller;

import com.jfoenix.controls.JFXButton;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.SupplierBo;
import edu.shopify.db.StageManager;
import edu.shopify.dto.Employee;
import edu.shopify.dto.Supplier;
import edu.shopify.dto.tm.EmployeeTM;
import edu.shopify.dto.tm.SupplierTM;
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
import java.util.ResourceBundle;

public class ManageSuppliersFormController implements Initializable {
    public TableView tblSuppliers;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colNIC;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public TextField txtName;
    public TextField txtCompany;
    public TextField txtNIC;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField txtSupplierId;
    public JFXButton btnAddAgain;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    public ManageSuppliersFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtNIC.getText(),
                txtMobile.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                true
        );

        try{
            if(Boolean.TRUE.equals(supplierBo.saveSupplier(supplier))){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Supplier Added Successfully!");
                alert.show();
                loadTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtNIC.getText(),
                txtMobile.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                true
        );
        if(Boolean.TRUE.equals(supplierBo.updateSupplier(supplier))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Supplier Updated Successfully!");
            loadTable();
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Error Updating Supplier!");
        alert.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if(Boolean.TRUE.equals(supplierBo.deleteSupplier(txtSupplierId.getText()))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Supplier Deleted Successfully!");
            alert.show();
            loadTable();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error deleting supplier!");
        alert.show();
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        try{
            Supplier supplier = supplierBo.searchSupplier(txtSupplierId.getText());
            if(supplier == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Id!");
                alert.show();
                return;
            }

            txtSupplierId.setText(supplier.getSupplierId());
            txtName.setText(supplier.getName());
            txtCompany.setText(supplier.getCompany());
            txtNIC.setText(supplier.getNic());
            txtMobile.setText(supplier.getMobile());
            txtEmail.setText(supplier.getEmail());

            if (supplier.getIsActive() == false){
                btnAddAgain.setDisable(false);
            }else{
                btnAddAgain.setDisable(true);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Id!");
            alert.show();
        }

    }
    public void btnAddAgainOnAction(ActionEvent actionEvent) {
        if(supplierBo.readdSupplier(txtSupplierId.getText())){
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
        ObservableList<SupplierTM> supplierTableData = FXCollections.observableArrayList();

        try{
            supplierBo.getAllSuppliers().forEach(supplier -> {
                SupplierTM supplierTm = new SupplierTM(
                        supplier.getSupplierId(),
                        supplier.getName(),
                        supplier.getCompany(),
                        supplier.getNic(),
                        supplier.getMobile(),
                        supplier.getEmail()
                );
                supplierTableData.add(supplierTm);
            });
            tblSuppliers.setItems(supplierTableData);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void navDashbordOnClick(MouseEvent mouseEvent) {
    }

    public void navPlaceOrderOnClick(MouseEvent mouseEvent) {
    }

    public void navReturnItemsOnClick(MouseEvent mouseEvent) {
    }

    public void navProductsOnClick(MouseEvent mouseEvent) {
    }

    public void navEmployeesOnClick(MouseEvent mouseEvent) {
        Stage currentStage= StageManager.getCurrentStage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/manage-employees-form.fxml"));
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
