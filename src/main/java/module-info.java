module com.alesandro.ejercicio15q {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alesandro.ejercicio15q to javafx.fxml;
    exports com.alesandro.ejercicio15q;
}