package ru.ibs.planeta.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static  final Model INSTANCE = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance(){
        return INSTANCE;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("Name_1", "Surname_1", 15.00));
        model.put(2, new User("Name_2", "Surname_2", 5.00));
        model.put(3, new User("Name_3", "Surname_3", 150.00));
    }

    public void addUser(User user, int id){
        model.put(id, user);
    }

    public User getUser(int id){
        return model.get(id);
    }

    public Map<Integer, User> getUserList(){
        return model;
    }
}
