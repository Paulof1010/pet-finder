package codeforall.pets.my_pet_app.Model.Occurence;

import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("MISSING")
public class MissingOccurrence extends Occurrence{

    @ManyToOne(optional = true)
    private User owner;

    @OneToOne
    @JsonBackReference
    private Animal animal;





    @Override
    public OccurrenceType getType() {

        return OccurrenceType.MISSING;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
