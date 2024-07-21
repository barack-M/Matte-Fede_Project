module org.example.giocodelloca {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.giocodelloca to javafx.fxml;
    exports org.giocodelloca;
    exports org.giocodelloca.effects;
    opens org.giocodelloca.effects to javafx.fxml;
}