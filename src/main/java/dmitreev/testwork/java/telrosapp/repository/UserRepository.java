package dmitreev.testwork.java.telrosapp.repository;

import dmitreev.testwork.java.telrosapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String query);
}
