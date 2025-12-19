package codeforall.pets.my_pet_app.Service;

import codeforall.pets.my_pet_app.JPARepository.AnimalRepository;
import codeforall.pets.my_pet_app.JPARepository.OccurrenceRepository;
import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.Occurence.Occurrence;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Transactional
public class OccurrenceService {

    private OccurrenceRepository occurrenceRepository;

    public Occurrence findById(int id) {

        return occurrenceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Occurrence> listAll() {
        return occurrenceRepository.findAll();
    }


    public void add(Occurrence occurrence)
    {
        System.out.println("testing again " + ((SightingOccurrence) occurrence).getAnimal().getName());
        occurrenceRepository.saveAndFlush(occurrence);
    }

    public void edit(Occurrence occurrence) {
        occurrenceRepository.save(occurrence);
    }

    public void delete(int id) {
        occurrenceRepository.deleteById(id);
    }

    @Autowired
    public void setOccurrenceRepository(OccurrenceRepository occurrenceRepository) {
        this.occurrenceRepository = occurrenceRepository;
    }
}
