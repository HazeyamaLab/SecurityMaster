package lab.haze.SecurityMaster.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import lab.haze.SecurityMaster.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();
}
