package edu.shopify.controller;

import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.CustomerBo;
import edu.shopify.bo.custom.OrderBo;
import edu.shopify.bo.custom.ProductBo;
import edu.shopify.dto.Category;
import edu.shopify.dto.Customer;
import edu.shopify.dto.Product;
import edu.shopify.dto.tm.CartTm;
import edu.shopify.dto.tm.EmployeeTM;
import edu.shopify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class AddOrderFormController implements Initializable {
    public Label lblOrderId;
    public ComboBox cmbItemIds;
    public ImageView imgImage;
    public Label lblName;
    public Label lblSize;
    public Label lblCategory;
    public Label lblUnitPrice;
    public Label lblQtyInStock;
    public TextField txtQty;
    public Label lblTotal;
    public TextField txtName;
    public ComboBox cmbMobile;
    public TextField txtEmail;
    public Label lblDate;
    public Label lblTime;
    public TableView tblCart;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colQty;
    public TableColumn colAmount;
    public TableColumn colUnitPrice;
    //private List<Product> products = new ArrayList<>();

    //private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    ObservableList<CartTm> cartTableData = FXCollections.observableArrayList();
    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public AddOrderFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //products = productBo.getAllProducts();

        txtEmail.setEditable(false);
        txtName.setEditable(false);
        //loadProductCatalog();

        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadItemIdsDropdown();
        loadMobileDropdown();

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    setProductDetails((String) newValue);
                });

        cmbMobile.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.equals("+ Add New Customer")) {
                        try {
                            Stage stage = new Stage();
                            stage.setScene(new Scene
                                    (new FXMLLoader(getClass()
                                            .getResource
                                                    ("/view/add-customer-popup.fxml"))
                                            .load()));
                            stage.showAndWait();

                            loadMobileDropdown();

                            cmbMobile.setValue(cmbMobile.getItems().get(cmbMobile.getItems().size()-2));

                        } catch (IOException e) {
                            System.err.println("Error loading add-customer-popup.fxml: " + e.getMessage());
                        } catch (Exception e) {
                            System.err.println("Error loading Customer Mobiles" + e.getMessage());
                        }
                    }else{
                        setCustomerDetails((String) newValue);
                    }


                }
        );
    }

    private void setProductDetails(String newValue) {
        try{
            Product product = productBo.searchProduct(newValue);

            lblName.setText(product.getName());
            lblSize.setText(product.getSize());
            lblUnitPrice.setText(product.getRetailPrice().toString());
            lblCategory.setText(product.getCategory().getId() + " - "+product.getCategory().getName());
            lblQtyInStock.setText(product.getQtyInStock().toString());

            imgImage.setImage(new Image
                    (new ByteArrayInputStream(product.getImage())));

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Retrieving Product Data").show();
        }


    }

    private void setCustomerDetails(String mobile) {
        try{
            Customer customer = customerBo.searchCustomerByMobile(mobile);

            txtName.setText(customer.getFirstName()+" "+customer.getLastName());
            txtEmail.setText(customer.getEmail());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Retrieving Customer Data").show();
        }

    }

    private void loadMobileDropdown() {
        ObservableList<String> mobileNumbers = FXCollections.observableArrayList();

        Collections.addAll(mobileNumbers, customerBo.getAllMobiles());

        mobileNumbers.add("+ Add New Customer");

        cmbMobile.setItems(mobileNumbers);
    }

    private void loadItemIdsDropdown() {
        ObservableList<String> itemIds = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = productBo.getAllProducts();

        for(Product product: allProducts){
            itemIds.add(product.getId());
        }

        cmbItemIds.setItems(itemIds);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        if (cmbMobile.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select a Customer!").show();
            return;
        }
        if (cmbItemIds.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select an Item!").show();
            return;
        }
        if (txtQty.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter a Quantity!").show();
            return;
        }

        try{
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            Double amount = qty * unitPrice;

            CartTm cartItem = new CartTm(
                    (String) cmbItemIds.getValue(),
                    lblName.getText(),
                    qty,
                    unitPrice,
                    amount
            );

            cartTableData.add(cartItem);

            tblCart.setItems(cartTableData);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR,"Please Enter a valid Quantity!").show();
        }
    }

    public void btnClearAllOnAction(ActionEvent actionEvent) {
        cartTableData.clear();

        cmbMobile.setValue(null);
        cmbMobile.setPromptText("-- select Customer Mobile--");

        cmbItemIds.setValue(null);
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
    }

//Methods to navigate between windows
    public void navReportsOnClick(MouseEvent mouseEvent) {
    }

    public void navEmployeesOnClick(MouseEvent mouseEvent) {
    }

    public void navSuppliersOnClick(MouseEvent mouseEvent) {
    }

    public void navPlaceOrderOnClick(MouseEvent mouseEvent) {
    }

    public void navReturnItemsOnClick(MouseEvent mouseEvent) {
    }

    public void navDashbordOnClick(MouseEvent mouseEvent) {
    }


}
