module com.example.javafx.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.javafx.javafx to javafx.fxml;
    exports com.example.javafx.javafx;
}

