package camara.jose.controllers;

import camara.jose.utils.message.ConfirmMessage;
import camara.jose.utils.message.Message;
import camara.jose.utils.utils.GenerateRgbValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    /**
     * Atributos bindeados con javafx
     */
    @FXML
    private BorderPane bdrPane;
    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane paneRed1;
    @FXML
    private AnchorPane paneRed2;
    @FXML
    private AnchorPane paneRed3;
    @FXML
    private AnchorPane paneRed4;
    @FXML
    private AnchorPane paneRed5;
    @FXML
    private AnchorPane paneRed6;
    @FXML
    private AnchorPane paneRed7;
    @FXML
    private AnchorPane paneRed8;
    @FXML
    private AnchorPane paneRed9;
    @FXML
    private AnchorPane paneRed10;
    @FXML
    private VBox vboxRed;
    @FXML
    private Button btnStartStop;

    /**
     * Atributos de clase
     */
    private Stage stage;
    private double xOffSet=0;
    private double yOffSet=0;
    private int btnStatus=0;

    @FXML
    private void startStop() throws InterruptedException {
        GenerateRgbValue redValue = null;
        GenerateRgbValue greenValue = null;
        GenerateRgbValue blueValue = null;
        if(this.btnStatus==0){
            if(redValue==null && greenValue==null && blueValue==null) {
                redValue = new GenerateRgbValue();
                greenValue = new GenerateRgbValue();
                blueValue = new GenerateRgbValue();
                new Thread(redValue).start();
                new Thread(greenValue).start();
                new Thread(blueValue).start();
            }
            this.btnStatus=1;
            this.btnStartStop.setStyle("-fx-background-color:  #5499C7");
           //this.btnStartStop.setStyle("-fx-cursor: hand");
        }
        else if (this.btnStatus==1){
            this.btnStatus=0;
            this.btnStartStop.setStyle("-fx-background-color:   #F1C40F");
            //this.btnStartStop.setStyle("-fx-cursor: hand");
        }
    }

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