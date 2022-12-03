package camara.jose.controllers;

import camara.jose.log.Log;
import camara.jose.model.DAO.ColourDAO;
import camara.jose.model.dataObject.Colour;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowColoursController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label lblColours;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Colour> colours = new ColourDAO().getAll();
        if(!colours.isEmpty()) {
            int columns = 0;
            int row = 1;
            GridPane gp = new GridPane();
            gp.setPrefSize(480, 385);
            AnchorPane pane;
            for (Colour c : colours) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(App.class.getResource("colour-item.fxml"));
                    pane = fxmlLoader.load();
                    ColourItemController cic = fxmlLoader.getController();
                    cic.setItem(c);
                    if (columns == 1) {
                        columns = 0;
                        ++row;
                    }
                    gp.add(pane, ++columns, row);
                } catch (IOException e) {
                    Log.warningLogging(e+"");
                }
            }
            scrollPane.setContent(gp);
        }else {
            lblColours.setText("No existen colores aún.");
        }
    }

    @FXML
    private void closeApp() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.close();
    }
}
