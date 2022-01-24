package ru.ibs.planeta.springs.compas;

import lombok.Getter;

@Getter
public enum SideOfTheWorld {
    NORTH("320-40"),
    NORTHEAST("40-85"),
    EAST("85-115"),
    SOUTHEAST("120-130"),
    SOUTH("130-185"),
    SOUTHWEST("185-225"),
    WEST("225-275"),
    NORTHWEST("280-320");

    private String range;

    SideOfTheWorld(String range) {
        this.range = range;
    }
}
