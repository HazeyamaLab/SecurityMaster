package lab.haze.SecurityMaster.Model;

import lombok.Data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	private String id;
	private String name;
	private String password;
	private String role;
	private String created_user;
	private Date created_at;
	private String updated_user;
	private Date updated_at;
	private String deleted_user;
	private Date deleted_at;
	private int version;
}

