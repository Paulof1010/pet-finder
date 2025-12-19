package codeforall.pets.my_pet_app.Model.Occurence;
import codeforall.pets.my_pet_app.Model.Animal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SIGHTING")
public class SightingOccurrence extends Occurrence {


    @ManyToOne(
            optional = true
    )
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Override
    public OccurrenceType getType() {

        return OccurrenceType.SIGHTING;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {

        return animal;
    }

}
