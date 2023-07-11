package dmitreev.testwork.java.telrosapp.repository;

import dmitreev.testwork.java.telrosapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "SELECT * FROM users " +
//            "WHERE (LOWER(name) LIKE '%' || ?1 || '%')",
//            nativeQuery = true)
    User findUserByEmail(String query);
}
