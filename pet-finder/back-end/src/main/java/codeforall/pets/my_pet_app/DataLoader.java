package codeforall.pets.my_pet_app;

import codeforall.pets.my_pet_app.JPARepository.AnimalRepository;
import codeforall.pets.my_pet_app.JPARepository.OccurrenceRepository;
import codeforall.pets.my_pet_app.JPARepository.UserRepository;
import codeforall.pets.my_pet_app.Model.*;
import codeforall.pets.my_pet_app.JPARepository.*;
import codeforall.pets.my_pet_app.Model.Occurence.MissingOccurrence;
import codeforall.pets.my_pet_app.Model.Occurence.Occurrence;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final OccurrenceRepository occurrenceRepository;

    public DataLoader(AnimalRepository animalRepository,
                      UserRepository userRepository,
                      OccurrenceRepository occurrenceRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
        this.occurrenceRepository = occurrenceRepository;
    }

    @Override
    public void run(String... args) {

// Clear previous data
        occurrenceRepository.deleteAll();
        animalRepository.deleteAll();
        userRepository.deleteAll();

// ------------------- USERS -------------------
        User owner1 = new User();
        owner1.setFirstName("Owner");
        owner1.setLastName("1");
        owner1.setEmail("owner1@example.com");
        owner1.setPrimaryPhone("111-111-1111");

        User owner2 = new User();
        owner2.setFirstName("Owner");
        owner2.setLastName("2");
        owner2.setEmail("owner2@example.com");
        owner2.setPrimaryPhone("222-222-2222");

        User owner3 = new User();
        owner3.setFirstName("Alice");
        owner3.setLastName("Wonder");
        owner3.setEmail("alice@example.com");
        owner3.setPrimaryPhone("333-333-3333");

        User owner4 = new User();
        owner4.setFirstName("Bob");
        owner4.setLastName("Builder");
        owner4.setEmail("bob@example.com");
        owner4.setPrimaryPhone("444-444-4444");

        userRepository.saveAll(List.of(owner1, owner2, owner3, owner4));

// ------------------- ANIMALS -------------------
// Missing animals with names
        Animal dog = new Animal();
        dog.setType("dog");
        dog.setColor("Brown");
        dog.setBreed("Labrador");
        dog.setPictureUrls(List.of("/images/dog1.jpg", "/images/dog2.jpg", "/images/dog3.jpg"));
        dog.setName("Buddy");

        Animal rabbit = new Animal();
        rabbit.setType("rabbit");
        rabbit.setColor("Gray");
        rabbit.setBreed("Netherland Dwarf");
        rabbit.setPictureUrls(List.of("/images/rabbit1.jpg"));
        rabbit.setName("Thumper");

        Animal dog2 = new Animal();
        dog2.setType("dog");
        dog2.setColor("Black");
        dog2.setBreed("German Shepherd");
        dog2.setPictureUrls(List.of("/images/dog4.jpg"));
        dog2.setName("Max");

        Animal cat2 = new Animal();
        cat2.setType("cat");
        cat2.setColor("Black");
        cat2.setBreed("Siamese");
        cat2.setPictureUrls(List.of("/images/cat3.jpg", "/images/cat4.jpg"));
        cat2.setName("Shadow");

        Animal hamster = new Animal();
        hamster.setType("hamster");
        hamster.setColor("Golden");
        hamster.setBreed("Syrian");
        hamster.setPictureUrls(List.of("/images/hamster1.jpg"));
        hamster.setName("Nibbles");

// Sighting animals WITHOUT names
        Animal cat = new Animal();
        cat.setType("cat");
        cat.setColor("White");
        cat.setBreed("Persian");
        cat.setPictureUrls(List.of("/images/cat1.jpg", "/images/cat2.jpg"));

        Animal parrot = new Animal();
        parrot.setType("bird");
        parrot.setColor("Green");
        parrot.setBreed("Parakeet");
        parrot.setPictureUrls(List.of("/images/parrot1.jpg"));

        Animal turtle = new Animal();
        turtle.setType("turtle");
        turtle.setColor("Green");
        turtle.setBreed("Red-Eared Slider");
        turtle.setPictureUrls(List.of("/images/turtle1.jpg"));

        Animal cat3 = new Animal();
        cat3.setType("cat");
        cat3.setColor("Gray");
        cat3.setBreed("Maine Coon");
        cat3.setPictureUrls(List.of("/images/cat5.jpg"));

        animalRepository.saveAll(List.of(dog, rabbit, dog2, cat2, hamster, cat, parrot, turtle, cat3));

// ------------------- OCCURRENCES -------------------
// Missing occurrences
        MissingOccurrence lostDog = new MissingOccurrence();
        lostDog.setAnimal(dog);
        lostDog.setOwner(owner1);
        lostDog.setLatitude(38.7169);
        lostDog.setLongitude(-9.1395);
        lostDog.setDate(LocalDate.of(2025, 12, 15));
        lostDog.setComment("Buddy has a small white spot on his chest and loves to play fetch.");

        MissingOccurrence lostRabbit = new MissingOccurrence();
        lostRabbit.setAnimal(rabbit);
        lostRabbit.setOwner(owner4);
        lostRabbit.setLatitude(38.7175);
        lostRabbit.setLongitude(-9.1420);
        lostRabbit.setDate(LocalDate.of(2025, 12, 14));
        lostRabbit.setComment("Thumper is shy, gray-colored with slightly longer ears than usual.");

        MissingOccurrence lostCat2 = new MissingOccurrence();
        lostCat2.setAnimal(cat2);
        lostCat2.setOwner(owner2);
        lostCat2.setLatitude(38.7210);
        lostCat2.setLongitude(-9.1400);
        lostCat2.setDate(LocalDate.of(2025, 12, 13));
        lostCat2.setComment("Shadow is very shy, black Siamese with bright green eyes.");

        MissingOccurrence lostHamster = new MissingOccurrence();
        lostHamster.setAnimal(hamster);
        lostHamster.setOwner(owner3);
        lostHamster.setLatitude(38.7192);
        lostHamster.setLongitude(-9.1415);
        lostHamster.setDate(LocalDate.of(2025, 12, 18));
        lostHamster.setComment("Nibbles is tiny, golden-colored, and very curious.");

// Sighting occurrences
        SightingOccurrence foundCat = new SightingOccurrence();
        foundCat.setAnimal(cat);
        foundCat.setLatitude(38.7223);
        foundCat.setLongitude(-9.1393);
        foundCat.setDate(LocalDate.of(2025, 12, 16));
        foundCat.setComment("Snowball was seen near a cafe, playing with some children.");

        SightingOccurrence foundParrot = new SightingOccurrence();
        foundParrot.setAnimal(parrot);
        foundParrot.setLatitude(38.7200);
        foundParrot.setLongitude(-9.1375);
        foundParrot.setDate(LocalDate.of(2025, 12, 17));
        foundParrot.setComment("Kiwi was perched on a balcony, whistling tunes loudly.");

        SightingOccurrence foundDog2 = new SightingOccurrence();
        foundDog2.setAnimal(dog2);
        foundDog2.setLatitude(38.7187);
        foundDog2.setLongitude(-9.1382);
        foundDog2.setDate(LocalDate.of(2025, 12, 16));
        foundDog2.setComment("Max was running across the park, very energetic and playful.");

        SightingOccurrence foundTurtle = new SightingOccurrence();
        foundTurtle.setAnimal(turtle);
        foundTurtle.setLatitude(38.7230);
        foundTurtle.setLongitude(-9.1360);
        foundTurtle.setDate(LocalDate.of(2025, 12, 18));
        foundTurtle.setComment("Shelly was seen slowly moving near a pond in the park.");

        SightingOccurrence foundCat3 = new SightingOccurrence();
        foundCat3.setAnimal(cat3);
        foundCat3.setLatitude(38.7178);
        foundCat3.setLongitude(-9.1388);
        foundCat3.setDate(LocalDate.of(2025, 12, 19));
        foundCat3.setComment("Misty was spotted climbing a tree, very agile and playful.");

        occurrenceRepository.saveAll(List.of(
                lostDog, lostRabbit, lostCat2, lostHamster,
                foundCat, foundParrot, foundDog2, foundTurtle, foundCat3
        ));
    }
}