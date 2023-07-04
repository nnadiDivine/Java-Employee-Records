package com.example.employeerecords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class AddRecords implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private VBox box1;

    @FXML
    private VBox box2;

    @FXML
    private Button closeRecordBtn;

    @FXML
    private DatePicker date;

    @FXML
    private TextField department;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phone;

    @FXML
    private HBox recordRoot;

    @FXML
    private TextField salary;

    @FXML
    private Button submitBtn;


    ArrayList<EmployeeItems> list = new ArrayList<EmployeeItems>();//Creating an ArrayList of objects to store the employee's information
    Random random = new Random();//Creating the random class to generate random numbers to be assigned as employee number


//  Creating the initialize method to run when the class is called
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Screen screen = Screen.getPrimary();//Getting the computer Screen
        Rectangle2D bounds = screen.getVisualBounds();//Assigning the screen to Rectangle2D
        box1.setPrefWidth((70*bounds.getWidth())/100);//Setting box1 to 70% of the computer Screen
        box2.setPrefWidth((30*bounds.getWidth())/100);//Setting box2 to 30% of the computer Screen (box2 is the box containing all Text Fields to get employee data)



    }
//    Creating a function that will submit the Employee Info and add it to the text file
    public void submit(ActionEvent event) throws IOException, ClassNotFoundException {


        //Specifying a file
        File file = new File("employee.txt");//the employee file to store employee info
        File randFile = new File("random.txt");//the random file to store the employee number that is randomly generated
        ArrayList<RandomNumber> randomList = new ArrayList<RandomNumber>();//Creating the random list array object to store the employee number that is randomly generated
        ObjectOutputStream oos = null;//the ObjectOutputStream to write into my file from my object array
        ObjectInputStream ois = null;//the ObjectInputStream to read from a file from my object array
        ListIterator li = null;//the list Iterator to iterate through every item in any Array list I want


        //checking if file exist
        if (file.isFile()) {
            try {
                //reading what is the file and appending it to the array
        ois = new ObjectInputStream(new FileInputStream(file));
                list = (ArrayList<EmployeeItems>) ois.readObject();
                ois.close();//closing the file
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String isDateString = String.valueOf(date.getValue());

            //checking if all input fields are empty
            if (Objects.equals(firstName.getText(), "") || Objects.equals(lastName.getText(), "") || Objects.equals(age.getText(), "")|| Objects.equals(salary.getText(), "") || Objects.equals(department.getText(), "") || Objects.equals(isDateString, "")|| Objects.equals(address.getText(), "")|| Objects.equals(phone.getText(), "")) {
                //creating a notification to warn that all field are required
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("ALl Fields Required");
                tray.setMessage("No record must be blank");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(3000));
                System.out.println("kjb");
                //writing an else that will happen when all fields were inputted
            } else {
                if (randFile.isFile()){//checking if random file exist
                    ois = new ObjectInputStream(new FileInputStream(randFile));//reading all the contents of random file
                    randomList = (ArrayList<RandomNumber>)ois.readObject();//add those contents to randomList Array
                    ois.close();//closing the file
                    li = randomList.listIterator();//assigning li to be my randomList Array iterator

                    while (true) {
                        int randNum = random.nextInt(500) + 1;//Creating a random number
                        String strRandNum = Integer.toString(randNum);//converting the random number to string
                        if (!randomList.contains(strRandNum)) {//checking if my random list already has that number
                            li.add(new RandomNumber(strRandNum));//adding the number if my randomList does not have it
                            break;//stopping the loop
                        }
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(randFile));//opening my random file to write to it
                    oos.writeObject(randomList);//writing into my random file what is in randomList
                    oos.close();//closing the file
                }

                ois = new ObjectInputStream(new FileInputStream(randFile));//opening my random file to from it
                randomList = (ArrayList<RandomNumber>)ois.readObject();//filling my random array list with the content random file
                ois.close();//closing the file

                //getting the text of all input fields
                li = randomList.listIterator();
                String fName = firstName.getText();
                String lName = lastName.getText();
                String theAge = age.getText();
                String getSalary = salary.getText();
                String dp = department.getText();
                LocalDate getDate = date.getValue();
                String getAddress = address.getText();
                String getPhone = phone.getText();

//                making the pattern of my datetime to be dd/MM/yyyy
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String newDate = getDate.format(formatter);


                //add them to my array list using my EmployeeItems objects
                list.add(new EmployeeItems(li.next().toString(),fName, lName, theAge, getSalary,dp,newDate,getAddress,getPhone));

                //update the file and write that array list into it
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                oos.close();

                //setting all the input fields back to blank
                firstName.setText("");
                lastName.setText("");
                age.setText("");
                salary.setText("");
                department.setText("");
                address.setText("");
                phone.setText("");
                date.setValue(null);

                //creating the notification to tell that the person has been added
                System.out.println(list);
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("Record Added");
                tray.setMessage(fName + " " + lName + " Was Successfully Added");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }else {
            String isDateString = String.valueOf(date.getValue());

            //repeating the same process that will occur if the file exists
            if (Objects.equals(firstName.getText(), "") || Objects.equals(lastName.getText(), "") || Objects.equals(age.getText(), "")|| Objects.equals(salary.getText(), "") || Objects.equals(department.getText(), "") || Objects.equals(isDateString, "")|| Objects.equals(address.getText(), "")|| Objects.equals(phone.getText(), "")) {
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("ALl Fields Required");
                tray.setMessage("No record must be blank");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(3000));
                System.out.println("kjb");
            } else {
                //creating a new file if the specified file does not exist
                file.createNewFile();

                //this code is basically just the same as the if file.isFile() block as explained above only
                // that this time we created new file
                if (!randFile.isFile()){
                    randFile.createNewFile();
                    li = randomList.listIterator();

                    while (true) {
                        int randNum = random.nextInt(500) + 1;
                        String strRandNum = Integer.toString(randNum);
                        if (!randomList.contains(strRandNum)) {
                            li.add(new RandomNumber(strRandNum));
                            break;
                        }
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(randFile));
                    oos.writeObject(randomList);
                    oos.close();
                }


                System.out.println("randomList "+randomList);

                String fName = firstName.getText();
                String lName = lastName.getText();
                String theAge = age.getText();
                String getSalary = salary.getText();
                String dp = department.getText();
                LocalDate getDate = date.getValue();
                String getAddress = address.getText();
                String getPhone = phone.getText();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String newDate = getDate.format(formatter);

                list.add(new EmployeeItems(randomList.get(0).toString(),fName, lName, theAge, getSalary,dp,newDate,getAddress,getPhone));


                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                oos.close();

                firstName.setText("");
                lastName.setText("");
                age.setText("");
                salary.setText("");
                department.setText("");
                address.setText("");
                phone.setText("");
                date.setValue(null);

                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("Record Added");
                tray.setMessage(fName + " " + lName + " Was Successfully Added");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }
        }
    public void back() throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        HBox hBox = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        recordRoot.getChildren().setAll(hBox);
        //setting the width and height of main page to that of the screen of the computer
        hBox.setPrefHeight(bounds.getHeight());
        hBox.setPrefWidth(bounds.getWidth());
    }
    }
//}
