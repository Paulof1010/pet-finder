package codeforall.pets.my_pet_app.JPARepository;

import codeforall.pets.my_pet_app.Model.Occurence.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Integer> {
}
