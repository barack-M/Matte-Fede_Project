module org.example.giocodelloca {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.giocodelloca to javafx.fxml;
    exports org.giocodelloca;
}