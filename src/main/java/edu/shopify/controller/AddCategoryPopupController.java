package edu.shopify.controller;

import com.jfoenix.controls.JFXButton;
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
    public JFXButton btnCancel;

    private CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);

    public AddCategoryPopupController() throws Exception {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            Category category = new Category(
                    txtId.getText(),
                    txtName.getText()
            );

            categoryBo.saveCategory(category);

            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
