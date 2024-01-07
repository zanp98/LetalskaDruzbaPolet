module com.example.letalskadruzbapolet {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens com.example.letalskadruzbapolet to javafx.fxml;
    exports com.example.letalskadruzbapolet;
}