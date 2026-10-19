module org.example.speedrun {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.speedrun to javafx.fxml;
    exports org.example.speedrun;
}