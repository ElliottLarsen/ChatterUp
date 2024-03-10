module org.zerock.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.zerock.server to javafx.fxml;
    exports org.zerock.server;
}