module espol.edu.ec.autosell {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.edu.ec.autosell to javafx.fxml;
    exports espol.edu.ec.autosell;
    exports espol.edu.ec.autosell.utils;
    exports espol.edu.ec.autosell.model;
    exports dumpfmm;
}
