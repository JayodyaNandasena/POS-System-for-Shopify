package edu.shopify.controller;

import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.CustomerBo;
import edu.shopify.bo.custom.OrderBo;
import edu.shopify.bo.custom.ProductBo;
import edu.shopify.dto.Category;
import edu.shopify.dto.Customer;
import edu.shopify.dto.Product;
import edu.shopify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class AddOrderFormController implements Initializable {
    public Label lblOrderId;
    public ScrollPane scrlProducts;
    public GridPane gridProducts;
    public ComboBox cmbItemIds;
    public ImageView imgImage;
    public Label lblName;
    public Label lblSize;
    public Label lblCategory;
    public Label lblUnitPrice;
    public Label lblQtyInStock;
    public TextField txtQty;
    public ScrollPane scrlOrderProducts;
    public GridPane gridCart;
    public Label lblTotal;
    public TextField txtName;
    public ComboBox cmbMobile;
    public TextField txtEmail;
    private List<Product> products = new ArrayList<>();

    //private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public AddOrderFormController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        products = productBo.getAllProducts();
        //loadProductCatalog();

        loadMobileDropdown();


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

    private void setCustomerDetails(String mobile) {
        try{
            Customer customer = customerBo.searchCustomerByMobile(mobile);

            txtName.setText(customer.getFirstName()+" "+customer.getLastName());
            txtEmail.setText(customer.getEmail());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Retrieving Customer Date").show();
        }

    }

    private void loadMobileDropdown() {
        ObservableList<String> mobileNumbers = FXCollections.observableArrayList();

        Collections.addAll(mobileNumbers, customerBo.getAllMobiles());

        mobileNumbers.add("+ Add New Customer");

        cmbMobile.setItems(mobileNumbers);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnClearAllOnAction(ActionEvent actionEvent) {
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
    }

    public void imgImageClickOnAction(MouseEvent mouseEvent) {
    }

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
