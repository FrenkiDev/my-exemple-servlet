package ru.ibs.planeta.springs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ibs.planeta.springs.compas.Side;
import ru.ibs.planeta.springs.compas.SideModel;

import java.util.Map;

@RestController
public class ControllerCompas {
    private static final SideModel sideModel = SideModel.getInstance();

    @PostMapping(value = "/initRange", consumes = "application/json", produces = "application/json;charset=utf-8")
    public void initRange(@RequestBody Side sideOfTheWorld){
        sideModel.setSide(sideOfTheWorld);
    }

    @GetMapping(value = "/compas", consumes = "application/json", produces = "application/json;charset=utf-8")
    public Side.Degree getSide(@RequestBody Map<String, Integer> range){
        return sideModel.getSide().getDegree(range.get("Degree"));
    }
}
