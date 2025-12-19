package codeforall.pets.my_pet_app.Service;

import codeforall.pets.my_pet_app.JPARepository.UserRepository;
import codeforall.pets.my_pet_app.Model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public User findById(int id) {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void add(User owner) {
        userRepository.save(owner);
    }

    public void edit(User owner) {
        userRepository.save(owner);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

}
