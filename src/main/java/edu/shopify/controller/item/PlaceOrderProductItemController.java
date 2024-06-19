package edu.shopify.controller.item;

import edu.shopify.dto.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;

public class PlaceOrderProductItemController {
    public Label lblProductId;
    public ImageView imgProduct;
    public Label lblName;
    public VBox productCard;

    private Product product;

    public void setData(Product product) throws Exception {
        this.product = product;
        lblName.setText(product.getName());
        lblProductId.setText(product.getId());
        imgProduct.setImage(retrievingImage(product.getImage()));
    }

    private Image retrievingImage(byte[] imageBytes) throws Exception {
        return new Image(new ByteArrayInputStream(imageBytes));
    }
}
