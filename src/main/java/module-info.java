module com.example.matrixcalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.matrixcalc to javafx.fxml;
    exports com.example.matrixcalc;
}