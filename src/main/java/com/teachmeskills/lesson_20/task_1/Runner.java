package com.teachmeskills.lesson_20.task_1;

import com.teachmeskills.lesson_20.task_1.helpers.LocationInterface;
import com.teachmeskills.lesson_20.task_1.helpers.LocationMethods;
import com.teachmeskills.lesson_20.task_1.helpers.StudentsMethods;
import com.teachmeskills.lesson_20.task_1.model.Location;


public class Runner {

    public static void main(String[] args) {
        StudentsMethods studentsInterface = new StudentsMethods();
        studentsInterface.getStudentList();
//        Location location = new Location(11, "Kiev");
//        Student student = new Student(11, "Vasya", "Petrov", location);

        Location location1 = new Location(15, "Lvov");
        LocationInterface locationInterface = new LocationMethods();
        locationInterface.getLocationList();
        locationInterface.getLocationById(4);
        locationInterface.deleteLocationById(15);
        locationInterface.getLocationList();
    }
}
