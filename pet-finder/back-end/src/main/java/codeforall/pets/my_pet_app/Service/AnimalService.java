package codeforall.pets.my_pet_app.Service;

import codeforall.pets.my_pet_app.JPARepository.AnimalRepository;
import codeforall.pets.my_pet_app.Model.Animal;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnimalService {


    private AnimalRepository animalRepository;

    public Animal findById(int id) {

        return animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Animal> listAll() {
        return animalRepository.findAll();
    }

    public void add(Animal animal) {
        animalRepository.save(animal);
    }

    public void edit(Animal animal) {
        animalRepository.save(animal);
    }

    public void delete(int id) {
        animalRepository.deleteById(id);
    }

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

}
