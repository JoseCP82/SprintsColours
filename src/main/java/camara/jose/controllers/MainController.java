package camara.jose.controllers;

import camara.jose.model.DAO.ColourDAO;
import camara.jose.model.dataObject.Colour;
import camara.jose.utils.message.ConfirmMessage;
import camara.jose.utils.message.ErrorMessage;
import camara.jose.utils.message.InfoMessage;
import camara.jose.utils.message.Message;
import camara.jose.utils.utils.Chronometer;
import camara.jose.utils.utils.ConvertColourToString;
import camara.jose.utils.utils.GenerateRgbValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import static java.lang.System.exit;

public class MainController {

    /**
     * Atributos bindeados con javafx
     */
    @FXML
    private BorderPane bdrPane;
    @FXML
    private Button btnClose;
    @FXML
    private VBox vboxRed;
    @FXML
    private VBox vboxGreen;
    @FXML
    private VBox vboxBlue;
    @FXML
    private Button btnStartStop;
    @FXML
    private Button btnRestart;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblWin;

    /**
     * Atributos de clase
     */
    private Stage stage;
    private double xOffSet=0;
    private double yOffSet=0;
    private int btnStatus=0;
    private Thread threadRed=null;
    private Thread threadGreen=null;
    private Thread threadBlue=null;
    private GenerateRgbValue redValue = null;
    private GenerateRgbValue greenValue = null;
    private GenerateRgbValue blueValue = null;
    private Chronometer chronometer;

    /**
     * Inicia o para la carrera
     * @throws InterruptedException
     */
    @FXML
    private void startStop() throws InterruptedException {
        this.btnRestart.setDisable(false);
        if(this.btnStatus==0){
            if(redValue==null && greenValue==null && blueValue==null) {

                chronometer = new Chronometer(lblTime);
                chronometer.start();

                GenerateRgbValue[] rgb = new GenerateRgbValue[3];

                redValue = new GenerateRgbValue(vboxRed,btnStartStop, chronometer,"Red",lblWin);
                greenValue = new GenerateRgbValue(vboxGreen,btnStartStop,chronometer,"Green",lblWin);
                blueValue = new GenerateRgbValue(vboxBlue,btnStartStop,chronometer,"Blue",lblWin);

                rgb[0]=redValue;
                rgb[1]=greenValue;
                rgb[2]=blueValue;

                redValue.setGenerateRgbValue(rgb);
                blueValue.setGenerateRgbValue(rgb);
                greenValue.setGenerateRgbValue(rgb);

                threadRed = new Thread(redValue);
                threadGreen = new Thread(greenValue);
                threadBlue = new Thread(blueValue);

                threadRed.start();
                threadGreen.start();
                threadBlue.start();
            }
            this.btnStatus=1;
            this.btnStartStop.setStyle("-fx-background-color: #5499C7");
            //this.btnStartStop.setStyle("-fx-cursor: hand");
            redValue.getThreadStatus().setSuspended(false);
            greenValue.getThreadStatus().setSuspended(false);
            blueValue.getThreadStatus().setSuspended(false);
            chronometer.getThreadStatus().setSuspended(false);

        }
        else if (this.btnStatus==1){
            this.btnStatus=0;
            this.btnStartStop.setStyle("-fx-background-color: #F1C40F");
            //this.btnStartStop.setStyle("-fx-cursor: hand");
            redValue.getThreadStatus().setSuspended(true);
            greenValue.getThreadStatus().setSuspended(true);
            blueValue.getThreadStatus().setSuspended(true);
            chronometer.getThreadStatus().setSuspended(true);
        }
    }

    /**
     * Para la carrera y restablece los parametros de la app
     */
    @FXML
    private void restart(){
        chronometer.getThreadStatus().setSuspended(true);
        redValue.getThreadStatus().setSuspended(true);
        greenValue.getThreadStatus().setSuspended(true);
        blueValue.getThreadStatus().setSuspended(true);
        this.btnRestart.setDisable(true);
        ConfirmMessage cm = new ConfirmMessage("??Desea guardar la mezcla?");
        cm.showMessage();
        if(cm.getBt()==ButtonType.OK){
            saveColour();
        }
        this.btnStatus=0;
        this.btnStartStop.setDisable(false);
        this.btnStartStop.setStyle("-fx-background-color:  #27AE60");
        //this.btnStartStop.setStyle("-fx-cursor: hand");
        chronometer.interrupt();
        lblTime.setText("00:00:00");
        lblWin.setText("");
        threadRed.interrupt();
        threadGreen.interrupt();
        threadBlue.interrupt();
        for (int i=0; i<25; i++) {
            this.vboxRed.getChildren().get(i).setVisible(false);
            this.vboxGreen.getChildren().get(i).setVisible(false);
            this.vboxBlue.getChildren().get(i).setVisible(false);
        }
        redValue=null;
        greenValue=null;
        blueValue=null;
    }

    /**
     * Obtiene los datos de la interfaz grafica y llama al metodo que guarda la informacion
     */
    private void saveColour(){
        ColourDAO cdao = new ColourDAO();
        String newColour = ConvertColourToString.convert(redValue.getValue(), greenValue.getValue(), blueValue.getValue());
        Colour c = new Colour(newColour, redValue.getValue(), greenValue.getValue(), blueValue.getValue());
        if(cdao.get(newColour)==null){
            if(new ColourDAO().insert(c)){
                new InfoMessage("Color almacenado.").showMessage();
            } else {
                new ErrorMessage("No se pudo guardar el color").showMessage();
            }
        }
        else {
            new InfoMessage("Color existente en la base de datos.").showMessage();
        }
    }

    /**
     * Carga la vista grafica donde se muestran los colores almacenados
     * @throws IOException
     */
    @FXML
    private void loadColoursView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("show-colours.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 640);
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(scene);
        s.show();
    }

    /**
     * Carga la vista grafica About
     * @throws IOException
     */
    @FXML
    private void loadAboutView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("show-about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 640);
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(scene);
        s.show();
    }

    /**
     * Minimiza la aplicaci??n a la barra de tareas
     */
    @FXML
    private void minimizeWindow() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.setIconified(true);
    }

    /**
     * Finaliza la aplicaci??n
     */
    @FXML
    private void closeApp() {
        Message ms = new ConfirmMessage("??Seguro que desea salir?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            if(threadRed!=null) {
                threadRed.interrupt();
                threadGreen.interrupt();
                threadBlue.interrupt();
                chronometer.interrupt();
            }
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
            exit(0);
        }
    }

    /**
     * M??todo que hace drageable un stage
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
