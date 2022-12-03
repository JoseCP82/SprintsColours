package camara.jose.controllers;

import camara.jose.model.dataObject.Colour;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ColourItemController {

    /**
     * Atributos bindeados con java fx
     */
    @FXML
    private Label lblName;
    @FXML
    private Pane paneColour;

    /**
     * Setea los atributos bindeados
     * @param colour Color a setear
     */
    public void setItem(Colour colour) {
        String colourName= colour.getName();;
        this.lblName.setText(colourName);
        this.paneColour.setStyle("-fx-background-color: "+colourName);
    }
}
