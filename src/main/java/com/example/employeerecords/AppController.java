package com.example.employeerecords;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class AppController implements Initializable {


    @FXML
    private Button displayBtn;
    @FXML
    private HBox root;
    @FXML
    private TextField searchBar;
    @FXML
    private ScrollPane view1;

    @FXML
    private AnchorPane view2;
    @FXML
    private VBox viewRecordsBox;

    boolean isViewClicked = false;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Over here I am checking if HelloApplication is loaded because, in the HelloApplication
        //There is a boolean isLoaded which is set to false, reason is because I want a welcome page to
        //display anytime I open the application, now the isLoaded will be set to true after a fadeOut
        //animation is finished as seen in this code.
        if (!HelloApplication.isLoaded) {

            //Try and catch for the Welcome Screen because FXMLLoader.load must be enclosed in a try and catch
            try {
                //setting isLoaded to true so that the animation won't repeat itself
                HelloApplication.isLoaded = true;
                //Loading the welcome page
                AnchorPane pane = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                root.getChildren().setAll(pane);
                //getting the screen of the computer
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                //setting the width and height of root to that of the screen of the computer
                root.setPrefWidth(bounds.getWidth());
                root.setPrefHeight(bounds.getHeight());
                //setting the width and height of pane to that of the screen of the computer
                pane.setPrefWidth(bounds.getWidth());
                pane.setPrefHeight(bounds.getHeight());


                //FadeIn Animation
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.setCycleCount(1);

                //FadeOut Animation
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setCycleCount(1);

                //playing the fadeIn animation
                fadeIn.play();

                //writing a function to act when fadeIn animation is finished
                fadeIn.setOnFinished((e) -> {
                    fadeOut.play();
                });
                //writing a function to act when fadeOut animation is finished
                fadeOut.setOnFinished((e) -> {
                    //Try and catch for the main screen
                    try {
                        //loading the main page
                        HBox hBox = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                        root.getChildren().setAll(hBox);
                        //setting the width and height of main page to that of the screen of the computer
                        hBox.setPrefHeight(bounds.getHeight());
                        hBox.setPrefWidth(bounds.getWidth());

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                });


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }

    //Writing a function that will move user to the scene where he can add records
    public void addRecord(ActionEvent event) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("add-records.fxml"));
            Scene scene = new Scene(root);
            Stage window  = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//        writing code to view Records
        public void view() throws IOException, ClassNotFoundException {
            if (!isViewClicked) {
            isViewClicked = true;
            viewRecordsBox.getChildren().setAll();
            ArrayList<EmployeeItems> list = new ArrayList<EmployeeItems>();
            File file = new File("employee.txt");
            ObjectInputStream ois = null;
            ListIterator li = null;
            if (file.isFile()) {
            viewRecordsBox.setStyle("-fx-background-image:none;");
                ois = new ObjectInputStream(new FileInputStream(file));
                list = (ArrayList<EmployeeItems>) ois.readObject();
                ois.close();
                li = list.listIterator();

                EmployeeItems e;
                Region region1;
                HBox box;
                Text text;
                Button deleteBtn;
                Button editBtn;
                Button viewBtn;
                String hBoxStyle = "-fx-background-color: #ff9900;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);";
                int intNumber = 0;
                final AtomicReference<TimeZone> reference;
                while (li.hasNext()) {
                    intNumber++;
                    String stringNumber = Integer.toString(intNumber);
                    e = (EmployeeItems) li.next();

                    // Creating a text node to store the name of the records
                    text = new Text(stringNumber+": "+e.festName + " " + e.lasName);
                    text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,13));

                    // creating buttons for crud operation of records
                    deleteBtn = new Button("Delete");
                    viewBtn = new Button("View");
                    editBtn = new Button("Edit");

                    // styling the buttons
                    deleteBtn.setStyle("-fx-background-color: red;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                    deleteBtn.setPadding(new Insets(5,15,5,15));
                    editBtn.setStyle("-fx-background-color: orange;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                    editBtn.setPadding(new Insets(5,15,5,15));
                    viewBtn.setStyle("-fx-background-color: #fff;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                    viewBtn.setPadding(new Insets(5,15,5,15));

                    // creating functions for the buttons
                    //viewBtn function
                    EmployeeItems finalE = e;
                    viewBtn.setOnAction(a->ButtonFunctions.viewBtnFunction(finalE) );

                    //editBtn function
                    editBtn.setOnAction(a->ButtonFunctions.editBtnFunction(finalE,root) );

                    //deleteBtn function
                    deleteBtn.setOnAction(a-> {
                        try {
                            ButtonFunctions.deleteBtnFunction(finalE,root);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    // creating a region that will help align my buttons to the right by filling the center
                    region1 = new Region();
                    HBox.setHgrow(region1,Priority.ALWAYS);
                    box = new HBox(text,region1, viewBtn,editBtn,deleteBtn);

                    box.setStyle(hBoxStyle);
                    box.setPadding(new Insets(20,10,20,10));
                    box.setSpacing(10);


                    viewRecordsBox.setPrefWidth(view2.getWidth());
                    viewRecordsBox.setMargin(box,new Insets(10,20,10,20));
                    viewRecordsBox.getChildren().add(box);
                }
                System.out.println("hello");

            } else System.out.println("file non existent");
        }
        }


    public void search(ActionEvent event) throws IOException, ClassNotFoundException {
        isViewClicked = false;
        ArrayList<EmployeeItems> list = new ArrayList<EmployeeItems>();
        File file = new File("employee.txt");
        ObjectInputStream ois = null;
        ListIterator li = null;
        if (file.isFile()) {
            viewRecordsBox.getChildren().setAll();
            viewRecordsBox.setStyle("-fx-background-image:none;");
            ois = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList<EmployeeItems>) ois.readObject();
            ois.close();
            li = list.listIterator();
            EmployeeItems e;
            Region region1;
            HBox box;
            Text text;
            Button deleteBtn;
            Button editBtn;
            Button viewBtn;
            String hBoxStyle = "-fx-background-color: #ff9900;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);";
            int intNumber = 0;
            while (li.hasNext()) {
                intNumber++;
                String stringNumber = Integer.toString(intNumber);
                e = (EmployeeItems) li.next();
                text = new Text(stringNumber+": "+e.festName + " " + e.lasName);
                text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,13));

                // creating buttons for crud operation of records
                deleteBtn = new Button("Delete");
                viewBtn = new Button("View");
                editBtn = new Button("Edit");

                // styling the buttons
                deleteBtn.setStyle("-fx-background-color: red;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                deleteBtn.setPadding(new Insets(5,15,5,15));
                editBtn.setStyle("-fx-background-color: orange;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                editBtn.setPadding(new Insets(5,15,5,15));
                viewBtn.setStyle("-fx-background-color: #fff;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
                viewBtn.setPadding(new Insets(5,15,5,15));

                ListIterator finalLi = li;
                EmployeeItems finalE = e;
                viewBtn.setOnAction(a->ButtonFunctions.viewBtnFunction(finalE) );

                //editBtn function
                ListIterator finalLi2 = li;
                EmployeeItems finalE2 = e;
                editBtn.setOnAction(a->ButtonFunctions.editBtnFunction(finalE2,root) );

                // creating a region that will help align my buttons to the right by filling the center
                region1 = new Region();
                HBox.setHgrow(region1,Priority.ALWAYS);

                box = new HBox(text,region1, viewBtn,editBtn,deleteBtn);
                box.setStyle(hBoxStyle);
                box.setPadding(new Insets(20,10,20,10));
                box.setSpacing(10);
                viewRecordsBox.setPrefWidth(view2.getWidth());
                viewRecordsBox.setMargin(box,new Insets(10,20,10,20));
                String firstAndLastName = e.festName.concat(" ").concat(e.lasName);
                String firstAndDepartment = e.festName.concat(" ").concat(e.depart);
                String lastAndDepartment = e.lasName.concat(" ").concat(e.depart);
                String departmentAndFirst = e.depart.concat(" ").concat(e.festName);
                String departmentAndLast = e.depart.concat(" ").concat(e.lasName);
                if (Objects.equals(searchBar.getText(), e.festName) || Objects.equals(searchBar.getText().toLowerCase(), e.festName.toLowerCase())) {
                viewRecordsBox.getChildren().add(box);
                }else if (Objects.equals(searchBar.getText(), e.lasName) || Objects.equals(searchBar.getText().toLowerCase(), e.lasName.toLowerCase())){
                    viewRecordsBox.getChildren().add(box);
                }else if (Objects.equals(searchBar.getText(), e.depart) || Objects.equals(searchBar.getText().toLowerCase(), e.depart.toLowerCase())){
                    viewRecordsBox.getChildren().add(box);
                } else if (Objects.equals(searchBar.getText(), firstAndLastName) || Objects.equals(searchBar.getText().toLowerCase(), firstAndLastName.toLowerCase())) {
                    viewRecordsBox.getChildren().add(box);
                } else if (Objects.equals(searchBar.getText(), firstAndDepartment) || Objects.equals(searchBar.getText().toLowerCase(), firstAndDepartment.toLowerCase())) {
                    viewRecordsBox.getChildren().add(box);
                } else if (Objects.equals(searchBar.getText(), lastAndDepartment) || Objects.equals(searchBar.getText().toLowerCase(), lastAndDepartment.toLowerCase())) {
                    viewRecordsBox.getChildren().add(box);
                } else if (Objects.equals(searchBar.getText(), departmentAndFirst) || Objects.equals(searchBar.getText().toLowerCase(), departmentAndFirst.toLowerCase())) {
                    viewRecordsBox.getChildren().add(box);
                } else if (Objects.equals(searchBar.getText(), departmentAndLast) || Objects.equals(searchBar.getText().toLowerCase(), departmentAndLast.toLowerCase())) {
                    viewRecordsBox.getChildren().add(box);
                }
            }
        }

    }

}
