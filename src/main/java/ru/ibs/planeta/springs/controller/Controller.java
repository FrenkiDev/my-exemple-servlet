package ru.ibs.planeta.springs.controller;

import org.springframework.web.bind.annotation.*;
import ru.ibs.planeta.springs.Pet;
import ru.ibs.planeta.springs.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);
    private static final String msg = "Поздравляем вы создари своего %sго питомца";

    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json;charset=utf-8")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());

        String tmpMsg;
        if (petModel.getAll().size() == 1) {
            tmpMsg = format(msg, "перво");
        } else {
            tmpMsg = format(msg, petModel.getAll().size());
        }
        return tmpMsg;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    /**
     * @param id {"id":int}
     * @return Pet {"name":"string", "type":"string", "age":int}
     */
    @GetMapping(value = "/getPetById", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromLost(id.get("id"));
    }

    @PutMapping(value = "/updatePetById/{id}", consumes = "application/json", produces = "application/json;charset=utf-8")
    public Pet updatePet(@PathVariable(value = "id") int petId, @RequestBody Pet pet) {
        petModel.getAll().replace(petId, pet);
        return petModel.getFromLost(petId);
    }

    @DeleteMapping(value = "/deletePetById", consumes = "application/json", produces = "application/json;charset=utf-8")
    public String deletePet(@RequestBody Map<String, Integer> id){
        petModel.getAll().remove(id.get("id"));
        return "Питомец удален по id: " + id.get("id");
    }
}
