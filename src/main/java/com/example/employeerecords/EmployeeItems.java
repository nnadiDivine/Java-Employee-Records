package com.example.employeerecords;

import java.io.Serializable;

//This is the Object to store and process all the information for our employee.txt which is normally read into an array list
//This object has a constructor and a getter

public class EmployeeItems implements Serializable {
    private static long counter = 0;
    String employeeNum;
    String festName;
    String lasName;
    String age;
    String salary;
    String date;
    String address;
    String phone;
    String depart;

//    Constructor
    public EmployeeItems(String employeeNum, String festName, String lasName, String age, String salary , String depart, String date, String address, String phone) {
        this.employeeNum = employeeNum;
        this.festName = festName;
        this.lasName = lasName;
        this.age = age;
        this.salary = salary;
        this.depart = depart;
        this.date = date;
        this.address = address;
        this.phone = phone;
    }
//  Getter
    public String toString(){
        return employeeNum+" "+festName+" "+lasName+" "+age+" "+salary+" "+depart+" "+date+" "+address+" "+phone;
    }
}
