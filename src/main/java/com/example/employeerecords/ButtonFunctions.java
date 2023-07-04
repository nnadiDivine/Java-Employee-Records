package com.example.employeerecords;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.*;
import java.util.*;

public class ButtonFunctions {
static TextField field1;
static TextField field2;
static TextField field3;
static TextField field4;
static TextField field5;
static TextField field6;
static TextField field7;
static TextField field8;
    //function for to display Employee information when view button is clicked
    public static void viewBtnFunction(EmployeeItems ek){
        //creating the Stage to display the modal
        Stage window = new Stage();

        // Configuring the stage to be a modal like stage
        window.initModality(Modality.APPLICATION_MODAL);

        // Adding a title for the modal
        window.setTitle(ek.festName+" "+ek.lasName+" Information");
        // setting the maximum width of the modal
        window.setMaxWidth(350);

        // Label to display the Employee first name
        Label label0 = new Label();
        label0.setText("Employee Number: "+ ek.employeeNum);
        label0.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee first name
        Label label1 = new Label();
        label1.setText("First Name: "+ ek.festName);
        label1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Last name
        Label label2 = new Label();
        label2.setText("Last Name: "+ ek.lasName);
        label2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Last name
        Label label3 = new Label();
        label3.setText("Age: "+ ek.age);
        label3.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Last name
        Label label4 = new Label();
        label4.setText("Salary: "+ ek.salary);
        label4.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Department
        Label label5 = new Label();
        label5.setText("Department: "+ ek.depart);
        label5.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Department
        Label label6 = new Label();
        label6.setText("Date: "+ ek.date);
        label6.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Department
        Label label7 = new Label();
        label7.setText("Address: "+ ek.address);
        label7.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        // Label to display the Employee Department
        Label label8 = new Label();
        label8.setText("Phone: "+ ek.phone);
        label8.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));

        //Creating a button to close the modal
        Button closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-background-color: red;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
        closeBtn.setPadding(new Insets(5,15,5,15));
        closeBtn.setOnAction(e-> window.close());

        // Creating the VBox that display all the information vertically
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setSpacing(20);
        layout.getChildren().addAll(label0,label1,label2,label3,label4,label5,label6,label7,label8,closeBtn);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    //function to edit a record
    public static void editBtnFunction(EmployeeItems ek, HBox root){
        //creating the Stage to display the modal
        Stage window = new Stage();

        // Configuring the stage to be a modal like stage
        window.initModality(Modality.APPLICATION_MODAL);

        // Adding a title for the modal
        window.setTitle(ek.festName+" "+ek.lasName+" Information");
        // setting the maximum width of the modal
        window.setMaxWidth(350);

        // Label to display the Employee first name
        Label label1 = new Label();
        label1.setText("First Name: ");
        label1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field1 = new TextField();
        field1.setText(ek.festName);

        // Label to display the Employee Last name
        Label label2 = new Label();
        label2.setText("Last Name: ");
        label2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field2 = new TextField();
        field2.setText(ek.lasName);

        // Label to display the Employee Department
        Label label3 = new Label();
        label3.setText("Age: ");
        label3.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field3 = new TextField();
        field3.setText(ek.age);

        // Label to display the Employee Department
        Label label4 = new Label();
        label4.setText("Salary: ");
        label4.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field4 = new TextField();
        field4.setText(ek.salary);

        // Label to display the Employee Department
        Label label5 = new Label();
        label5.setText("Department: ");
        label5.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field5 = new TextField();
        field5.setText(ek.depart);

        // Label to display the Employee Department
        Label label6 = new Label();
        label6.setText("Date: ");
        label6.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field6 = new TextField();
        field6.setText(ek.date);

        // Label to display the Employee Department
        Label label7 = new Label();
        label7.setText("Address: ");
        label7.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field7 = new TextField();
        field7.setText(ek.address);

        // Label to display the Employee Department
        Label label8 = new Label();
        label8.setText("Phone: ");
        label8.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR,15));
        field8 = new TextField();
        field8.setText(ek.phone);

        //Creating a button to close the modal
        Button closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-background-color: red;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
        closeBtn.setPadding(new Insets(5,15,5,15));

        //writing an event listener to perform the function when the button is clicked
        // the function is created below this function
        closeBtn.setOnMouseClicked(e-> {
            try {
                closeViewModalBtn(window,root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Creating a button to edit the modal
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: orange;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.3),10,0.5,0.0,0.0);");
        saveBtn.setPadding(new Insets(5,15,5,15));
        //writing an event listener to perform the function when the button is clicked
        // the function is created below this function
        saveBtn.setOnAction(e-> {
            try {
                saveBtn(ek);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

//        Creating a region this is like a space between
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

//        ADDING THE LABELS AND INPUT FIELDS IN HBOX TO DISPLAY IN FLEX
        HBox flexDiv1 = new HBox(label1,region1,field1);
        flexDiv1.setPadding(new Insets(5));
        flexDiv1.setSpacing(10);

        HBox flexDiv2 = new HBox(label2,region1,field2);
        flexDiv2.setPadding(new Insets(5));
        flexDiv2.setSpacing(10);

        HBox flexDiv3 = new HBox(label3,region1,field3);
        flexDiv3.setPadding(new Insets(5));
        flexDiv3.setSpacing(10);

        HBox flexDiv4 = new HBox(label4,region1,field4);
        flexDiv4.setPadding(new Insets(5));
        flexDiv4.setSpacing(10);

        HBox flexDiv5 = new HBox(label5,region1,field5);
        flexDiv5.setPadding(new Insets(5));
        flexDiv5.setSpacing(10);

        HBox flexDiv6 = new HBox(label6,region1,field6);
        flexDiv6.setPadding(new Insets(5));
        flexDiv6.setSpacing(10);

        HBox flexDiv7 = new HBox(label7,region1,field7);
        flexDiv7.setPadding(new Insets(5));
        flexDiv7.setSpacing(10);

        HBox flexDiv8 = new HBox(label8,region1,field8);
        flexDiv8.setPadding(new Insets(5));
        flexDiv8.setSpacing(10);

//        ADDING THE BUTTONS IN HBOX TO DISPLAY IN FLEX
        HBox flexButtons = new HBox(saveBtn,region1,closeBtn);

        // adding margins to the buttons
        flexButtons.setMargin(saveBtn, new Insets(0,5,0,5));
        flexButtons.setMargin(closeBtn, new Insets(0,5,0,5));

        // Creating the VBox that display all the information vertically
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setSpacing(20);
        layout.getChildren().addAll(flexDiv1,flexDiv2,flexDiv3,flexDiv4,flexDiv5,flexDiv6,flexDiv7,flexDiv8,flexButtons);
        layout.setAlignment(Pos.TOP_CENTER);

//        creating a scene to show the modal
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

//    Function to close the modal that displays when you click the edit button at the display records home page
    public static void closeViewModalBtn(Stage window, HBox root) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
//        reloading the app so that you can notice the changes
        HBox hBox = FXMLLoader.load(ButtonFunctions.class.getResource("hello-view.fxml"));
        root.getChildren().setAll(hBox);
        //setting the width and height of main page to that of the screen of the computer
        hBox.setPrefHeight(bounds.getHeight());
        hBox.setPrefWidth(bounds.getWidth());
        window.close();
    }

//  Function to edit the employee details the function is called by a button in the modal
//  that pops up when you click edit in the display records area
    public static void saveBtn(EmployeeItems ek) throws IOException, ClassNotFoundException {
//        Specifying to the files i will need
        File file = new File("employee.txt");
        File randFile = new File("random.txt");
//        creating the array list to store my new updated employee details
        ArrayList<EmployeeItems> al = new ArrayList<EmployeeItems>();
//        creating my arraylist to get the employee number
        ArrayList<RandomNumber> randList = new ArrayList<RandomNumber>();
//        creating my ObjectOutputStream to write into a file
        ObjectOutputStream oos = null;
//        creating my ObjectInputStream to read from a file
        ObjectInputStream ois = null;
//        List iterator to loop  to EmployeeItems array list
        ListIterator li = null;
//        List iterator to loop  to RandomNumber array list
        ListIterator randLi = null;

//        adding everything from my employee.txt file into my al ArrayList
        ois = new ObjectInputStream(new FileInputStream(file));
        al = (ArrayList<EmployeeItems>)ois.readObject();
        ois.close();

//        adding everything from my random.txt file into my randList ArrayList
        ois = new ObjectInputStream(new FileInputStream(randFile));
        randList = (ArrayList<RandomNumber>)ois.readObject();

//        Sorting the employee number stored in my al ArrayList
        Collections.sort(al, new Comparator<EmployeeItems>() {
            @Override
            public int compare(EmployeeItems o1, EmployeeItems o2) {
                Integer m = Integer.valueOf(o1.employeeNum);
                Integer m2 = Integer.valueOf(o2.employeeNum);
                return m - m2;
            }
        });
//        Sorting the random number stored in my randList ArrayList
        Collections.sort(randList, new Comparator<RandomNumber>() {
            @Override
            public int compare(RandomNumber o1, RandomNumber o2) {
                Integer m = Integer.valueOf(o1.randNum);
                Integer m2 = Integer.valueOf(o2.randNum);
                return m - m2;
            }
        });

//        assigning li to be al list iterator
        li = al.listIterator();
//        assigning randLi to be randList list iterator
        randLi = randList.listIterator();
//        creating a boolean to know if someone has been edited
        boolean done = false;

//        looping through every element is al List until the employee number in al and random number in randLi
//        matches
        RandomNumber e;
        while (li.hasNext()) {
           e = (RandomNumber) randLi.next();
            if (Objects.equals(e.randNum, ek.employeeNum)) {
                System.out.println(li.next());
//                setting what is inside that iterator of al List to this new information
                li.set(new EmployeeItems(ek.employeeNum, field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText()));
//                setting boolean done to true for more processing
                done = true;
                break;
            }
        }
//        writing what will happen when a record is found and update this code should display a notification
//        and update our employee.txt file with the new details
        if (done) {
//            updating our employee.txt file with what is currently inside al
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(al);
            oos.close();

//            notification to say update was sucessful
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Updated Successful");
            tray.setMessage(ek.festName+" "+ek.lasName+" was updated successfully");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

//    Function to delete a record this is almost the same as the saveBtn function above, only that we did not set the
//    li of al List instead we removed it
    public static void deleteBtnFunction(EmployeeItems ek,HBox root) throws IOException, ClassNotFoundException {
        File file = new File("employee.txt");
        File randFile = new File("random.txt");
        ArrayList<EmployeeItems> al = new ArrayList<EmployeeItems>();
        ArrayList<RandomNumber> randList = new ArrayList<RandomNumber>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator li = null;
        ListIterator randLi = null;

        ois = new ObjectInputStream(new FileInputStream(file));
        al = (ArrayList<EmployeeItems>)ois.readObject();
        ois.close();

        ois = new ObjectInputStream(new FileInputStream(randFile));
        randList = (ArrayList<RandomNumber>)ois.readObject();

        Collections.sort(al, new Comparator<EmployeeItems>() {
            @Override
            public int compare(EmployeeItems o1, EmployeeItems o2) {
                Integer m = Integer.valueOf(o1.employeeNum);
                Integer m2 = Integer.valueOf(o2.employeeNum);
                return m - m2;
            }
        });
        Collections.sort(randList, new Comparator<RandomNumber>() {
            @Override
            public int compare(RandomNumber o1, RandomNumber o2) {
                Integer m = Integer.valueOf(o1.randNum);
                Integer m2 = Integer.valueOf(o2.randNum);
                return m - m2;
            }
        });

        li = al.listIterator();
        randLi = randList.listIterator();
        boolean done = false;

        System.out.println("al"+al);
        System.out.println("randList"+randList);
        RandomNumber e;
        while (li.hasNext()) {
            System.out.println("yes");
            e = (RandomNumber) randLi.next();
            System.out.println("enum"+e.randNum);
            System.out.println("ek"+ek.employeeNum);
            System.out.println("li "+li.next());
            if (Objects.equals(e.randNum, ek.employeeNum)) {
                System.out.println("finally");
                li.remove();
                randLi.remove();
                done = true;
                break;
            }
        }
        System.out.println("finally2");
        if (!done) {
            System.out.println("Could not find record");
        }else {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(al);
            oos.close();

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            HBox hBox = FXMLLoader.load(ButtonFunctions.class.getResource("hello-view.fxml"));
            root.getChildren().setAll(hBox);
            //setting the width and height of main page to that of the screen of the computer
            hBox.setPrefHeight(bounds.getHeight());
            hBox.setPrefWidth(bounds.getWidth());

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Deleted Successful");
            tray.setMessage(ek.festName+" "+ek.lasName+" was Deleted successfully");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
        System.out.println(ek);
    }
}
