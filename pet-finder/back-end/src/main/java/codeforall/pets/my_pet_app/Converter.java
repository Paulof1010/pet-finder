package codeforall.pets.my_pet_app;
import codeforall.pets.my_pet_app.Dto.AnimalDTO;
import codeforall.pets.my_pet_app.Dto.OccurrenceDTO;
import codeforall.pets.my_pet_app.Dto.UserDTO;
import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.Occurence.MissingOccurrence;
import codeforall.pets.my_pet_app.Model.Occurence.Occurrence;
import codeforall.pets.my_pet_app.Model.Occurence.OccurrenceType;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import codeforall.pets.my_pet_app.Model.User;
import codeforall.pets.my_pet_app.Service.AnimalService;
import codeforall.pets.my_pet_app.Service.OccurrenceService;
import codeforall.pets.my_pet_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class Converter {

    private UserService userService;
    private AnimalService animalService;
    private OccurrenceService occurrenceService;

    public User ownerDtoToOwner(UserDTO userDto) {

        User owner = (userDto.getId() != null) ? userService.findById(userDto.getId()) : new User();

        owner.setFirstName(userDto.getFirstName());
        owner.setLastName(userDto.getLastName());
        owner.setPrimaryPhone(userDto.getPrimaryPhone());
        owner.setSecondaryPhone(userDto.getSecondaryPhone());
        owner.setEmail(userDto.getEmail());

        return owner;
    }

    public UserDTO ownerToOwnerDto(User owner) {

        UserDTO userDto = new UserDTO();
        userDto.setId(owner.getId());
        userDto.setFirstName(owner.getFirstName());
        userDto.setLastName(owner.getLastName());
        userDto.setPrimaryPhone(owner.getPrimaryPhone());
        userDto.setSecondaryPhone(owner.getSecondaryPhone());
        userDto.setEmail(owner.getEmail());


        List<AnimalDTO> animalDTOS = new ArrayList<>();

        for (Animal animal : owner.getAnimal()) {

            animalDTOS.add(animalToAnimalDto(animal));
        }
        userDto.setAnimals(animalDTOS);

        return userDto;
    }

    public Animal animalDtoToAnimal(AnimalDTO animalDto) {


        Animal animal = (animalDto.getId() != null) ? animalService.findById(animalDto.getId()) : new Animal();

        animal.setName(animalDto.getName());
        animal.setType(animalDto.getType());
        animal.setColor(animalDto.getColor());
        animal.setBreed(animalDto.getBreed());
        animal.setPictureUrls(animalDto.getPictureUrls());

        return animal;
    }

    public AnimalDTO animalToAnimalDto(Animal animal)  {

        AnimalDTO animalDto = new AnimalDTO();

        animalDto.setId(animal.getId());
        animalDto.setName(animal.getName());
        animalDto.setType(animal.getType());
        animalDto.setColor(animal.getColor());
        animalDto.setBreed(animal.getBreed());
        animalDto.setPictureUrls(animal.getPictureUrls());

        return animalDto;
    }

    public Occurrence occurrenceDtoToOccurrence(OccurrenceDTO occurrenceDTO) {

        Occurrence occurrence;

        System.out.println("test converter. first");
        if (occurrenceDTO.getId() != null) {

            System.out.println("test converter. if not id null");
            occurrence = occurrenceService.findById(occurrenceDTO.getId());
        } else {

            System.out.println("test converter. if id is null");
            if (occurrenceDTO.getType() == "MISSING") {
                occurrence = new MissingOccurrence();
                ((MissingOccurrence) occurrence).setAnimal(animalDtoToAnimal(occurrenceDTO.getAnimal()));
                ((MissingOccurrence) occurrence).setOwner(ownerDtoToOwner(occurrenceDTO.getOwner()));
            } else {
                occurrence = new SightingOccurrence();
                System.out.println("test converter. if id is null and Sighting");

                ((SightingOccurrence) occurrence).setAnimal(animalDtoToAnimal(occurrenceDTO.getAnimal()));
            }
        }


        String[] date = occurrenceDTO.getDate().split("-");
        System.out.println(Integer.parseInt(date[0]));
        occurrence.setDate(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
        System.out.println(occurrence.getDate());
        occurrence.setLatitude(occurrenceDTO.getLatitude());
        occurrence.setLongitude(occurrenceDTO.getLongitude());
        occurrence.setComment(occurrenceDTO.getComment());

        System.out.println("test converter. about to return");
        return occurrence;

    }

    public OccurrenceDTO occurrenceToOccurrenceDto(Occurrence occurrence) {

        OccurrenceDTO occurrenceDTO = new OccurrenceDTO();

        if (occurrence instanceof MissingOccurrence mo) {
            occurrenceDTO.setType("MISSING");
            occurrenceDTO.setAnimal(animalToAnimalDto(mo.getAnimal()));
            occurrenceDTO.setOwner(ownerToOwnerDto(mo.getOwner()));
        } else if (occurrence instanceof SightingOccurrence so) {
            occurrenceDTO.setType("SIGHTING");
            occurrenceDTO.setAnimal(animalToAnimalDto(so.getAnimal()));
        }

        occurrenceDTO.setId(occurrence.getId());
        occurrenceDTO.setDate(occurrence.getDate().toString());
        occurrenceDTO.setLatitude(occurrence.getLatitude());
        occurrenceDTO.setLongitude(occurrence.getLongitude());
        occurrenceDTO.setComment(occurrence.getComment());

        return occurrenceDTO;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Autowired
    public void setOccurrenceService(OccurrenceService occurrenceService) {
        this.occurrenceService = occurrenceService;
    }
}
