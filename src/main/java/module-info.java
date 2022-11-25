module camara.jose.sprintscolours {
    requires javafx.controls;
    requires javafx.fxml;


    opens camara.jose.controllers to javafx.fxml;
    exports camara.jose.controllers;
}