package edu.shopify.controller.item;

import edu.shopify.controller.ProductCatalogFormController;
import edu.shopify.dto.Product;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductCatalogItemController implements Initializable {
    public Label lblProductId;
    public ImageView imgProduct;
    public Label lblName;
    public VBox productCard;
    public Button btnEdit;
    public Label lblRetailPrice;
    public Label lblSize;
    public Label lblQtyInStock;

    private Product product;

    public void setData(Product product) throws Exception {
        this.product = product;

        lblName.setText(product.getName());
        lblProductId.setText(product.getId());
        lblRetailPrice.setText("Rs. "+product.getRetailPrice().toString());
        lblSize.setText(product.getSize());
        lblQtyInStock.setText(product.getQtyInStock().toString());
        imgProduct.setImage(retrievingImage(product.getImage()));
    }

    private Image retrievingImage(byte[] imageBytes) throws Exception {
        return new Image(new ByteArrayInputStream(imageBytes));
    }

    private void loadButtons(){
        final double BUTTON_DIMENSION = 25;

        //Create imageview with background image
        ImageView view1 = new ImageView(new Image("/img/edit.png"));
        view1.setFitHeight(BUTTON_DIMENSION);
        view1.setFitWidth(BUTTON_DIMENSION);
        view1.setPreserveRatio(false);

        //Attach image to the button
        btnEdit.setGraphic(view1);
        //Set the image to the top
        btnEdit.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadButtons();
    }

    public void itemPaneOnClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/product-catalog-form.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        ProductCatalogFormController productCatalogFormController = fxmlLoader.getController();
        productCatalogFormController.setChosenProduct(product);
//        ProductCatalogFormController formController = new ProductCatalogFormController();
//
//        //System.out.println(lblProductId.getText());
//        //formController.setChosenProduct(lblProductId.getText());
//        formController.setChosenProduct(product);

//        myListener.onClickListener(product);
    }
}
