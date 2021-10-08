/*
  module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires javafx.base;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml;
    opens AccesoArchivos to java.xml.bind;
    
    
    exports AccesoArchivos.AccesoArchivos;
} * */


 module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires javafx.base;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml,java.xml.bind;
    opens AccesoArchivos.AccesoArchivos.models.room_folder to java.xml.bind;
    opens AccesoArchivos.AccesoArchivos.models.user_folder to java.xml.bind;
    exports AccesoArchivos.AccesoArchivos;
}
 

