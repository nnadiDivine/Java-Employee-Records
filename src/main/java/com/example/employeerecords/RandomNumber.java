package com.example.employeerecords;

import java.io.Serializable;

//This is the object to store and process random generated numbers stored in random.txt and always read into an array List
//This object has a constructor and a getter
public class RandomNumber implements Serializable {
    String randNum;

//    Constructor
    public RandomNumber(String randNum) {
        this.randNum = randNum;
    }
//    Getter
    public String toString() {
        return randNum;
    }
}
