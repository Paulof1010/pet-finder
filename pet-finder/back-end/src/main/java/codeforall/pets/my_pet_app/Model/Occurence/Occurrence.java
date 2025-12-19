package codeforall.pets.my_pet_app.Model.Occurence;

import codeforall.pets.my_pet_app.Model.AbstractModel;
import codeforall.pets.my_pet_app.Model.Animal;
import codeforall.pets.my_pet_app.Model.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
@Table(name = "occurences")
public abstract class Occurrence extends AbstractModel {


    // flagged ****
    private LocalDate date;
    private double latitude;
    private double longitude;
    private String comment;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public abstract OccurrenceType getType();

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
