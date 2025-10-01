module com.enosistudio.fxstyleintellij {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.enosistudio.fxstyleintellij to javafx.fxml;
    exports com.enosistudio.fxstyleintellij;
}