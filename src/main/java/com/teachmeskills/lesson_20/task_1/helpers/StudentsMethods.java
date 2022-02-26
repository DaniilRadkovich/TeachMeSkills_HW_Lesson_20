package com.teachmeskills.lesson_20.task_1.helpers;

import com.teachmeskills.lesson_20.task_1.model.Location;
import com.teachmeskills.lesson_20.task_1.model.Student;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.List;

public class StudentsMethods implements StudentsInterface {

    @Language("SQL")
    private static final String INSERT_STUDENT =
            "INSERT INTO groupmates " +
                    "(firstName, lastName, location_id) " +
                    "VALUES (?, ?, ?) ";
    @Language("SQL")
    private static final String FIND_ALL_STUDENTS =
            "SELECT s.id AS student_id, firstName, lastName, location_id, hometown " +
                    "FROM groupmates s " + "JOIN hometowns l " +
                    "ON (s.location_id = l.id) ";
    @Language("SQL")
    private static final String FIND_STUDENT_BY_ID =
            FIND_ALL_STUDENTS +
                    "WHERE s.id = ? ";
    @Language("SQL")
    private static final String UPDATE_STUDENT =
            "UPDATE groupmates " +
                    "SET firstName = ?, lastName = ?, location_id = ? " +
                    "WHERE id = ? ";
    @Language("SQL")
    private static final String DELETE_STUDENT =
            "DELETE from groupmates " +
                    "WHERE firstName LIKE ? and lastName LIKE ? and location_id = ? ";
    @Language("SQL")
    private static final String DELETE_STUDENT_BY_ID =
            "DELETE from groupmates " +
                    "WHERE id = ? ";

    @Override
    public boolean addStudent(Student item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(INSERT_STUDENT);
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setInt(3, item.getLocation().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getStudentList() {
        DBWorker dbWorker = new DBWorker();
        PreparedStatement statement = null;
        try {
            statement = dbWorker.getConnection().prepareStatement(FIND_ALL_STUDENTS);
            ResultSet resultSet = (statement).executeQuery("SELECT * FROM students_schema.groupmates");
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                System.out.printf("%d. %s %s \n", id, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student getStudentOccurrence(ResultSet resultSet) throws SQLException {
        int locationId = resultSet.getInt("location_id");
        String hometown = resultSet.getString("hometown");
        Location studentLocation = new Location(locationId, hometown);
        int studentsId = resultSet.getInt("student_id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        return new Student(studentsId, firstName, lastName, studentLocation);
    }

    public Student getStudentById(Integer id) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(FIND_STUDENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return getStudentOccurrence(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student updateStudent(Student item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(UPDATE_STUDENT);
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setInt(3, item.getLocation().getId());
            statement.setInt(4, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteStudent(Student item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(DELETE_STUDENT);
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setInt(3, item.getLocation().getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudentById(Integer id) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(DELETE_STUDENT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
