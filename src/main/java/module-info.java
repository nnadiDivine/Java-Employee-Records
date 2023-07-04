module com.example.employeerecords {
    requires javafx.controls;
    requires javafx.fxml;
    requires TrayTester;


    opens com.example.employeerecords to javafx.fxml;
    exports com.example.employeerecords;
}