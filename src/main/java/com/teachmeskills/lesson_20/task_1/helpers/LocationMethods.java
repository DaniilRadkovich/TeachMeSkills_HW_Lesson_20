package com.teachmeskills.lesson_20.task_1.helpers;

import com.teachmeskills.lesson_20.task_1.model.Location;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationMethods implements LocationInterface {

    @Language("SQL")
    private static final String INSERT_LOCATION =
            "INSERT INTO hometowns " +
                    "(id, hometown) " +
                    "VALUES (?, ?) ";
    @Language("SQL")
    private static final String FIND_ALL_LOCATIONS =
            "SELECT id, hometown " +
                    "FROM hometowns ";
    @Language("SQL")
    private static final String FIND_LOCATION_BY_ID =
            FIND_ALL_LOCATIONS +
                    "WHERE id = ? ";
    @Language("SQL")
    private static final String UPDATE_LOCATION =
            "UPDATE hometowns " +
                    "SET hometown = ? " +
                    "WHERE id = ? ";
    @Language("SQL")
    private static final String DELETE_LOCATION =
            "DELETE from hometowns " +
                    "WHERE id = ? and hometown LIKE ? ";
    @Language("SQL")
    private static final String DELETE_LOCATION_BY_ID =
            "DELETE from hometowns " +
                    "WHERE id = ? ";

    @Override
    public boolean addLocation(Location item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(INSERT_LOCATION);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getHometown());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Location> getLocationList() {
        DBWorker dbWorker = new DBWorker();
        PreparedStatement statement = null;
        try {
            statement = dbWorker.getConnection().prepareStatement(FIND_ALL_LOCATIONS);
            ResultSet resultSet = (statement).executeQuery("SELECT * FROM students_schema.hometowns");
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String hometown = resultSet.getString(2);
                System.out.printf("%d. %s \n", id, hometown);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Location getLocationOccurrence(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String hometown = resultSet.getString("hometown");
        return new Location(id, hometown);
    }

    @Override
    public Location getLocationById(Integer id) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(FIND_LOCATION_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return getLocationOccurrence(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Location updateLocation(Location item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(UPDATE_LOCATION);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getHometown());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteLocation(Location item) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(DELETE_LOCATION);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getHometown());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLocationById(Integer id) {
        DBWorker dbWorker = new DBWorker();
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(DELETE_LOCATION_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
