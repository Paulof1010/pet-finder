package codeforall.pets.my_pet_app.JPARepository;

import codeforall.pets.my_pet_app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
