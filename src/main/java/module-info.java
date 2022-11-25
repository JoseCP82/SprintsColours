module camara.jose.sprintscolours {
    requires javafx.controls;
    requires javafx.fxml;


    opens camara.jose.sprintscolours to javafx.fxml;
    exports camara.jose.sprintscolours;
    exports camara.jose.sprintscolours.controllers;
    opens camara.jose.sprintscolours.controllers to javafx.fxml;
}