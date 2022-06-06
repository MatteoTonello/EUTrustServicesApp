/**
 * Modulo EUTrustServicesApplication
 */
module code.eutrustservicesapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens code.eutrustservicesapplication to javafx.fxml;
    exports code.eutrustservicesapplication;
}