module AccesoArchivos.AccesoArchivos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires javafx.base;
	requires com.sun.xml.bind;

    opens AccesoArchivos.AccesoArchivos to javafx.fxml,java.xml.bind,com.sun.xml.bind;
    opens AccesoArchivos.AccesoArchivos.models.room_folder to java.xml.bind,com.sun.xml.binds;
    opens AccesoArchivos.AccesoArchivos.models.user_folder to java.xml.bind,com.sun.xml.binds;
    opens AccesoArchivos.AccesoArchivos.models.message_folder to java.xml.bind,com.sun.xml.binds;
    
    exports AccesoArchivos.AccesoArchivos.models.user_folder to com.sun.xml.bind;
    exports AccesoArchivos.AccesoArchivos.models.message_folder to com.sun.xml.bind;
    exports AccesoArchivos.AccesoArchivos.models.room_folder to com.sun.xml.bind;
    exports AccesoArchivos.AccesoArchivos;
}