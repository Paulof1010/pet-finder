package codeforall.pets.my_pet_app.Controllers;

import codeforall.pets.my_pet_app.Converter;
import codeforall.pets.my_pet_app.Dto.UserDTO;
import codeforall.pets.my_pet_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    // dependencies
    private UserService userService;
    private Converter converter;


    // get requests
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int userId) {

        try {
            return new ResponseEntity<>(converter.ownerToOwnerDto(userService.findById(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> listUser() {


        try{

            List<UserDTO> userDtoList = new ArrayList<>();
            userService.listAll().forEach(o -> userDtoList.add(converter.ownerToOwnerDto(o)));

            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } catch (Exception e ) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    // post requests
    @PostMapping(path = "/user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDto) {

        try {

            userService.add(converter.ownerDtoToOwner(userDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // put requests
    @PutMapping(path = "/user")
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDto) {

        try{

            userService.edit(converter.ownerDtoToOwner(userDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // delete requests
    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){

        try{
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
