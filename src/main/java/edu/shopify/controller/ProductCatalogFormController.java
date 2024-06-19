package edu.shopify.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.CategoryBo;
import edu.shopify.bo.custom.ProductBo;
import edu.shopify.bo.custom.SupplierBo;
import edu.shopify.db.StageManager;
import edu.shopify.dto.Category;
import edu.shopify.dto.Employee;
import edu.shopify.dto.Product;
import edu.shopify.dto.Supplier;
import edu.shopify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Blob;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductCatalogFormController implements Initializable {

    public TextField txtId;
    public TextField txtName;

    public TextField txtQty;
    public TextField txtRetailPrice;
    public TextField txtWholesalePrice;
    public ImageView imgImage;
    public JFXButton btnAddAgain;
    public ComboBox cmbCategory;
    public ComboBox cmbSupplier;
    public ComboBox cmbSize;
    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);
    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    private byte[] imageByteArray;

    public ProductCatalogFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSizesDropdown();
        try {
            loadSupplierDropdown();
            loadCategoryDropdown();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cmbCategory.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.equals("+ Add New Category")) {
                        try {
                            Stage stage = new Stage();
                            stage.setScene(new Scene
                                    (new FXMLLoader(getClass()
                                            .getResource
                                                    ("/view/add-category-popup.fxml"))
                                            .load()));
                            stage.showAndWait();

                            loadCategoryDropdown();

                            //cmbCategory.setValue(cmbCategory.getItems().get(cmbCategory.getItems().size()-1));

                            //System.out.println(cmbCategory.getItems().size()-1);

                        } catch (IOException e) {
                            System.err.println("Error loading add-category-popup.fxml: " + e.getMessage());
                        } catch (Exception e) {
                            System.err.println("Error loading Category Dropdown " + e.getMessage());
                        }
                    }
                }
        );


    }

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        String selectedCategoryId = cmbCategory.getValue().toString().split(" - ")[0];
        Category category = categoryBo.searchCategory(selectedCategoryId);

        String selectedSupplierId = cmbSupplier.getValue().toString().split(" - ")[0];
        Supplier supplier = supplierBo.searchSupplier(selectedSupplierId);

        Product product = new Product(
                txtId.getText(),
                txtName.getText(),
                cmbSize.getValue().toString(),
                category,
                supplier,
                //imageByteArray,
                Double.parseDouble(txtRetailPrice.getText()),
                Double.parseDouble(txtWholesalePrice.getText()),
                Integer.parseInt(txtQty.getText()),
                true
        );

        try {
            if (Boolean.TRUE.equals(productBo.saveProduct(product))) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Product Added Successfully!");
                alert.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Product Id Already Exists!");
            alert.show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        String selectedCategoryId = cmbCategory.getValue().toString().split(" - ")[0];
        Category category = categoryBo.searchCategory(selectedCategoryId);

        String selectedSupplierId = cmbSupplier.getValue().toString().split(" - ")[0];
        Supplier supplier = supplierBo.searchSupplier(selectedSupplierId);

        Product product = new Product(
                txtId.getText(),
                txtName.getText(),
                cmbSize.getValue().toString(),
                category,
                supplier,
                //imageByteArray,
                Double.parseDouble(txtRetailPrice.getText()),
                Double.parseDouble(txtWholesalePrice.getText()),
                Integer.parseInt(txtQty.getText()),
                true
        );
        if(Boolean.TRUE.equals(productBo.updateProduct(product))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product Updated Successfully!");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Error Updating Product!");
        alert.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        if(Boolean.TRUE.equals(productBo.deleteProduct(txtId.getText()))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product Deleted Successfully!");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error Deleting Product!");
        alert.show();
    }

    public void btnAddAgainOnAction(ActionEvent actionEvent){
        if(Boolean.TRUE.equals(productBo.readdProduct(txtId.getText()))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product Added Successfully!");
            alert.show();
            btnAddAgain.setDisable(true);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error adding Product!");
        alert.show();
        btnAddAgain.setDisable(true);
    }

    public void imgImageClickOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File file = fileChooser.showOpenDialog(StageManager.getCurrentStage());

        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file));
                imgImage.setImage(image);
                imageByteArray = convertImageToByteArray(file);
            } catch (IOException e) {
                //showError("Error loading image", e.getMessage());
            }
        }
    }

    private byte[] convertImageToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = bos.toByteArray();

        return imageBytes;
    }

    private void loadCategoryDropdown() throws Exception {
        ObservableList<String> categories = FXCollections.observableArrayList();

        for (Category category : categoryBo.getAllCategories()) {
            categories.add(category.getId() + " - " + category.getName());
        }

        categories.add("+ Add New Category");

        cmbCategory.setItems(categories);
    }

    private void loadSupplierDropdown() throws Exception {
        ObservableList<String> suppliers = FXCollections.observableArrayList();

        for (Supplier supplier : supplierBo.getAllSuppliers()) {
            suppliers.add(supplier.getSupplierId() + " - " + supplier.getName() + " - " + supplier.getCompany());
        }

        cmbSupplier.setItems(suppliers);
    }

    private void loadSizesDropdown() {
        ObservableList<String> sizes = FXCollections.observableArrayList();

        sizes.add("XXS");
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");

        cmbSize.setItems(sizes);
    }

    public void navDashbordOnClick(MouseEvent mouseEvent) {
    }

    public void navPlaceOrderOnClick(MouseEvent mouseEvent) {
    }

    public void navReturnItemsOnClick(MouseEvent mouseEvent) {
    }

    public void navSuppliersOnClick(MouseEvent mouseEvent) {
    }

    public void navEmployeesOnClick(MouseEvent mouseEvent) {
    }

    public void navReportsOnClick(MouseEvent mouseEvent) {
    }




}
