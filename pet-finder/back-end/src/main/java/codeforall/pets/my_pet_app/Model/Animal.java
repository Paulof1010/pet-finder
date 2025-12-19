package codeforall.pets.my_pet_app.Model;

import codeforall.pets.my_pet_app.Model.Occurence.MissingOccurrence;
import codeforall.pets.my_pet_app.Model.Occurence.SightingOccurrence;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal extends AbstractModel {

    private String name;
    private String type;
    private String color;
    private String breed;
    private List<String> pictureUrls;

    @OneToOne
    @JsonManagedReference
    private MissingOccurrence missingOccurrence;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "animal",
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private Set<SightingOccurrence> sightingOccurrencesList = new HashSet<>();

    @ManyToOne
    private User owner;


    public void addSightingOccurrence(SightingOccurrence sightingOccurrence) {

        sightingOccurrencesList.add(sightingOccurrence);
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public MissingOccurrence getMissingOccurrence() {
        return missingOccurrence;
    }

    public void setMissingOccurrence(MissingOccurrence missingOccurrence) {
        this.missingOccurrence = missingOccurrence;
    }

    public Set<SightingOccurrence> getSightingOccurrencesList() {
        return sightingOccurrencesList;
    }

    public void setSightingOccurrencesList(Set<SightingOccurrence> sightingOccurrencesList) {
        this.sightingOccurrencesList = sightingOccurrencesList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
