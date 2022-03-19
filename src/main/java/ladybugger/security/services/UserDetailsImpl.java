package ladybugger.security.services;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ladybugger.model.Employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private String email;
	private String name;
	private String middleName;
	private String lastName;
	
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	public UserDetailsImpl(Long id, String email, String password, String name, String middleName, 
		String lastName,Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.middleName = middleName;
		this.authorities = authorities;
	}
	public static UserDetailsImpl build(Employee user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				user.getId(), 
				user.getEmail(),
				user.getPassword(), 
				user.getName(), 
				user.getMiddleName(), 
				user.getLastName(), 
				authorities);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}