module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires javafx.base;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml;
    opens utils to java.xml.bind;
    opens models.room_folder to java.xml.bind;
    
    exports AccesoArchivos.AccesoArchivos;
    exports models.room_folder to java.xml.bind;
}

/*
  
 module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires javafx.base;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml;
    exports AccesoArchivos.AccesoArchivos;
}
 
 * */
