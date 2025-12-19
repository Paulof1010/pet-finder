package codeforall.pets.my_pet_app.JPARepository;

import codeforall.pets.my_pet_app.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

}
