package lab.haze.SecurityMaster.Model;

import lombok.Data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
	@Id
	private String id;
	private String name;
	private String companyName;
	private int companyWorth;
	private String password;
	private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("USER"));
		return authorityList;
	}

	@Override
	public String getUsername() {
		return this.id;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}

	@Override
	public boolean isAccountNonExpired(){
		return true;
	}


}

