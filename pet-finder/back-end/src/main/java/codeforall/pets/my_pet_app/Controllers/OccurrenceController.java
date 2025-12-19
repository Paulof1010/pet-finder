package codeforall.pets.my_pet_app.Controllers;

import codeforall.pets.my_pet_app.Converter;
import codeforall.pets.my_pet_app.Dto.OccurrenceDTO;
import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.Occurence.MissingOccurrence;
import codeforall.pets.my_pet_app.Model.Occurence.Occurrence;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import codeforall.pets.my_pet_app.Service.AnimalService;
import codeforall.pets.my_pet_app.Service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@RestController
public class OccurrenceController {

    // dependencies
    private OccurrenceService occurrenceService;
    private AnimalService animalService;
    private Converter converter;


    // get requests
    @GetMapping("/occurrence/{occId}")
    public ResponseEntity<OccurrenceDTO> get(@PathVariable int occId) {

        try {
            return new ResponseEntity<>(converter.occurrenceToOccurrenceDto(occurrenceService.findById(occId)), HttpStatus.OK);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/occurrence")
    public ResponseEntity<List<OccurrenceDTO>> list() {


        try{

            List<OccurrenceDTO> occurrenceDtoList = new ArrayList<>();
            occurrenceService.listAll().forEach(o -> occurrenceDtoList.add(converter.occurrenceToOccurrenceDto(o)));

            return new ResponseEntity<>(occurrenceDtoList, HttpStatus.OK);
        } catch (Exception e ) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    // post requests
    @PostMapping(path = "/occurrence")
    public ResponseEntity<?> add(@RequestBody OccurrenceDTO occurrenceDTO) {

        try {

            System.out.println("test before method");

            Occurrence occTest = converter.occurrenceDtoToOccurrence(occurrenceDTO);

            System.out.println("test between methods");

            System.out.println(occTest.getComment());

            Animal animal;
            if (occTest instanceof SightingOccurrence oc) {
                animal = oc.getAnimal();
            } else {

                animal = ((MissingOccurrence) occTest).getAnimal();
            }

            animalService.add(animal);
            Animal maxIdAnimal = animalService.listAll().stream()
                    .max(Comparator.comparingInt(Animal::getId))
                    .orElse(null);

            if (occTest instanceof SightingOccurrence oc) {
                oc.setAnimal(maxIdAnimal);
            } else {
                ((MissingOccurrence) occTest).setAnimal(maxIdAnimal);
            }

            occurrenceService.add(occTest);


            System.out.println("testing name " + ((SightingOccurrence) occTest).getAnimal().getName());
            System.out.println("test after methods");

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //put requests
    @PutMapping(path = "/occurrence")
    public ResponseEntity<?> edit(@RequestBody OccurrenceDTO occurrenceDTO) {

        try{

            occurrenceService.edit(converter.occurrenceDtoToOccurrence(occurrenceDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // delete requests
    @DeleteMapping(path = "/occurrence/{occId}")
    public ResponseEntity<?> delete(@PathVariable int occId){

        try{
            occurrenceService.delete(occId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Autowired
    public void setOccurrenceService(OccurrenceService occurrenceService) {
        this.occurrenceService = occurrenceService;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }
}






