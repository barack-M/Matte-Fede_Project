module org.example.giocodelloca {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.giocodelloca to javafx.fxml;
    exports org.example.giocodelloca;
}