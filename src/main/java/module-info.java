module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml;
    exports AccesoArchivos.AccesoArchivos;
}
