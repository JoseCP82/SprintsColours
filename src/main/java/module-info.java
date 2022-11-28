module camara.jose.sprintscolours {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;


    opens camara.jose.controllers to javafx.fxml;
    exports camara.jose.controllers;
}