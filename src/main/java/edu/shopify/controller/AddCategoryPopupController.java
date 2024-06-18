package edu.shopify.controller;

import edu.shopify.bo.BoFactory;
import edu.shopify.bo.custom.CategoryBo;
import edu.shopify.dto.Category;
import edu.shopify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCategoryPopupController implements Initializable {
    public TextField txtId;
    public TextField txtName;

    private CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);

    public AddCategoryPopupController() throws Exception {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/add-category-popup.fxml")))));
        stage.close();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            Category category = new Category(
                    txtId.getText(),
                    txtName.getText()
            );

            categoryBo.saveCategory(category);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
