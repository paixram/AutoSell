module espol.edu.ec.autosell {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.edu.ec.autosell to javafx.fxml;
    exports espol.edu.ec.autosell;
}
