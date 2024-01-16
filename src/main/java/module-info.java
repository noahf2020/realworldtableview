module com.example.realworldtableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.realworldtableview to javafx.fxml;
    exports com.example.realworldtableview;
}