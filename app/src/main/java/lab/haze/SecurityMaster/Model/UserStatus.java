package lab.haze.SecurityMaster.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_status")
@Data
public class UserStatus {
    @Id
    private int id;
    private String userId;
    private String ltd;
    private int learnId;
    private int correct;
}
