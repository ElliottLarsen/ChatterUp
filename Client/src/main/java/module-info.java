module org.zerock.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.zerock.client to javafx.fxml;
    exports org.zerock.client;
}