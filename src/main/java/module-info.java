module org.zerock.chatterup {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.zerock.chatterup to javafx.fxml;
    exports org.zerock.chatterup;
}