package camara.jose.controllers;

import camara.jose.model.DAO.ColourDAO;
import camara.jose.model.dataObject.Colour;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
            for (Colour c : colours) {
                AnchorPane pane = new AnchorPane();
                pane.setPrefSize(480,386);
                pane.setStyle("-fx-background-color: "+c.getName());
                pane.setPadding(new Insets(40, 50, 50, 50));
                if (columns == 1) {
                    columns = 0;
                    ++row;
                }
                gp.add(pane, ++columns, row);
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
