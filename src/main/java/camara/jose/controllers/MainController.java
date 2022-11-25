package camara.jose.controllers;


import camara.jose.utils.message.ConfirmMessage;
import camara.jose.utils.message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {

    /**
     * Atributos bindeados con javafx
     */
    @FXML
    private BorderPane bdrPane;
    @FXML
    private Button btnClose;

    /**
     * Atributos de clase
     */
    private Stage stage;
    private double xOffSet=0;
    private double yOffSet=0;

    /**
     * Minimiza la aplicación a la barra de tareas
     */
    @FXML
    private void minimizeWindow() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.setIconified(true);
    }

    /**
     * Finaliza la aplicación
     */
    @FXML
    private void closeApp() {
        Message ms = new ConfirmMessage("¿Seguro que desea salir?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }

    /**
     * Método que hace drageable un stage
     */
    @FXML
    private void makeStageDragable() {
        bdrPane.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        bdrPane.setOnMouseDragged((event) -> {
            App.stage.setX(event.getScreenX() - xOffSet);
            App.stage.setY(event.getScreenY() - yOffSet);
            App.stage.setOpacity(0.8f);
        });
        bdrPane.setOnDragDone((event) -> {
            App.stage.setOpacity(1.0f);
        });
        bdrPane.setOnMouseReleased((event) -> {
            App.stage.setOpacity(1.0f);
        });
    }
}