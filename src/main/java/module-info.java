module Conroller {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.xml;

    opens Controller to javafx.fxml;
    exports Controller;
}