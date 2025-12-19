package codeforall.pets.my_pet_app.Model;
import codeforall.pets.my_pet_app.Model.Occurence.MissingOccurrence;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractModel {


    private String firstName;
    private String lastName;
    private String primaryPhone;
    private String secondaryPhone;
    private String email;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "owner",
            fetch = FetchType.EAGER
    )
    private List<Animal> animal = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "owner",
            fetch = FetchType.EAGER
    )
    private Set<MissingOccurrence> occurrenceList = new HashSet<>();



    public void addOccurrence(MissingOccurrence occurrence) {

        occurrenceList.add(occurrence);

    }


    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<MissingOccurrence> getOccurrenceList() {
        return occurrenceList;
    }
}
