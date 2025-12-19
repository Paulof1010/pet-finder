package codeforall.pets.my_pet_app.Controllers;

import codeforall.pets.my_pet_app.Converter;
import codeforall.pets.my_pet_app.Dto.AnimalDTO;
import codeforall.pets.my_pet_app.Dto.OccurrenceDTO;
import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import codeforall.pets.my_pet_app.Service.AnimalService;
import codeforall.pets.my_pet_app.Service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;


@RestController
public class AnimalController {

    //dependencies
    private AnimalService animalService;
    private OccurrenceService occurrenceService;
    private Converter converter;

    // get requests
    @GetMapping("/pets/{petId}")
    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable int petId) {

        try {
            return new ResponseEntity<>(converter.animalToAnimalDto(animalService.findById(petId)), HttpStatus.OK);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pets")
    public ResponseEntity<List<AnimalDTO>> listAnimal() {


        try{

            List<AnimalDTO> animalDtoList = new ArrayList<>();
            animalService.listAll().forEach(a -> animalDtoList.add(converter.animalToAnimalDto(a)));

            return new ResponseEntity<>(animalDtoList, HttpStatus.OK);
        } catch (Exception e ) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/pets/{petId}/occurrences")
    public ResponseEntity<List<OccurrenceDTO>> listAnimalOccurrences(@PathVariable int petId) {

        try{

            List<OccurrenceDTO> animalOccurrences = new ArrayList<>();
            animalService.findById(petId).getSightingOccurrencesList().forEach(occ -> animalOccurrences.add(converter.occurrenceToOccurrenceDto(occ)));

            return new ResponseEntity<>(animalOccurrences, HttpStatus.OK);
        } catch (Exception e ) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }



    // post requests
    @PostMapping(path = "/pets")
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDTO animalDto) {

        try {

            animalService.add(converter.animalDtoToAnimal(animalDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/pets/{petId}/occurrence")
    public ResponseEntity<?> addAnimalSighting(@RequestBody OccurrenceDTO occurrenceDto, @PathVariable int petId) {

        try {

            SightingOccurrence occToAdd = (SightingOccurrence) converter.occurrenceDtoToOccurrence(occurrenceDto);
            occToAdd.setAnimal(animalService.findById(petId));
            occurrenceService.add(occToAdd);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




    // put requests
    @PutMapping(path = "/pets/")
    public ResponseEntity<?> editAnimal(@RequestBody AnimalDTO animalDto) {

        try{

            animalService.edit(converter.animalDtoToAnimal(animalDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // delete requests
    @DeleteMapping(path = "/pets/{petId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable int petId){

        try{
            animalService.delete(petId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



//    @GetMapping("/pets/{userId}")
//    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable int userId) {
//
//        try{
//            return new ResponseEntity<>(Converter.animalToAnimalDto(userService.findBy(userId).getAnimal()), HttpStatus.OK);
//        } catch (Exception e) {
//            e.fillInStackTrace();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


//    @GetMapping("/pets")
//    public ResponseEntity<List<Animal>> listAnimals(
//            @RequestParam String type,
//            @RequestParam String color,
//            @RequestParam String breed) {
//
//        return new ResponseEntity<>(animalService.listAll(type, color, breed), HttpStatus.OK);
//    }

//    @GetMapping("/pet/occurrences/")
//    public ResponseEntity<List<AnimalDTO>> listAnimals(
//            @RequestParam String date,
//            @RequestParam int latitude,
//            @RequestParam int longitude) {
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    //setters
    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setOccurrenceService(OccurrenceService occurrenceService) {
        this.occurrenceService = occurrenceService;
    }
}
