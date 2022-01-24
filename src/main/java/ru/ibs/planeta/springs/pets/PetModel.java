package ru.ibs.planeta.springs.pets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel INSTANCE = new PetModel();

    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<>();
    }

    public static PetModel getInstance(){
        return INSTANCE;
    }

    public void add(Pet pet, int id){
        model.put(id, pet);
    }

    public Pet getFromLost(int id){
        return model.get(id);
    }

    public Map<Integer, Pet> getAll(){
        return model;
    }
}
