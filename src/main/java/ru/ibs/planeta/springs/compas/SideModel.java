package ru.ibs.planeta.springs.compas;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SideModel implements Serializable {
    private static final SideModel INSTANCE = new SideModel();
    private Side side;

    public static SideModel getInstance(){
        return INSTANCE;
    }
}
