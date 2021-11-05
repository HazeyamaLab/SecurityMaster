package lab.haze.SecurityMaster.Repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import lab.haze.SecurityMaster.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();
    User getOne(String id);
}
