package codeforall.pets.my_pet_app.Dto;

import codeforall.pets.my_pet_app.Model.User;

import java.time.LocalDate;

public class OccurrenceDTO {

    private Integer id;
    private String type;
    private String date;
    private double latitude;
    private double longitude;
    private AnimalDTO animal;
    private String comment;
    private UserDTO owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
