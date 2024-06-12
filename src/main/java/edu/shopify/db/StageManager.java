package edu.shopify.db;

import javafx.stage.Stage;

public class StageManager {
    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        StageManager.currentStage = currentStage;
    }
}
