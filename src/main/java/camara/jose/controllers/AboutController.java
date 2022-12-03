package camara.jose.controllers;

import camara.jose.log.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController {

    /**
     * Atributo bindeados con javafx
     */
    @FXML
    private Button btnClose;

    /**
     * Atributos de clase
     */
    private Stage stage;

    /**
     * Cierra la aplicación
     */
    @FXML
    private void closeApp() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.close();
    }

    /**
     * Abre el explorador por defecto y carga la url (GitHub)
     */
    @FXML
    private void goToGitHub() {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/JoseCP82/SprintsColours"));
        } catch (URISyntaxException ex) {
            Log.warningLogging(ex+"");
        }catch(IOException e){
            Log.warningLogging(e+"");
        }
    }
}
