package com.teachmeskills.lesson_20.task_1.helpers;

import com.teachmeskills.lesson_20.task_1.model.Location;

import java.util.List;

public interface LocationInterface {
    boolean addLocation(Location location);

    List<Location> getLocationList();

    Location getLocationById(Integer id);

    Location updateLocation(Location location);

    boolean deleteLocation(Location location);

    boolean deleteLocationById(Integer id);
}
