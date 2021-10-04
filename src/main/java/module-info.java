module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml;
    exports AccesoArchivos.AccesoArchivos;
}
