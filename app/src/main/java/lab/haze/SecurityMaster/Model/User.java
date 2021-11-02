package lab.haze.SecurityMaster.Model;

import lombok.Data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	private String id;
	private String name;
	private String companyName;
	private int companyWorth;
	private String password;
	private int role;

}

