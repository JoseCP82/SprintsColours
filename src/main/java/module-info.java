module camara.jose.sprintscolours {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires java.desktop;

    opens camara.jose.connection to java.xml.bind;
    exports camara.jose.connection;
    opens camara.jose.controllers to javafx.fxml;
    exports camara.jose.controllers;
}