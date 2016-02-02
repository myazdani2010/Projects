package cs520.gefp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails
{

  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false, unique = true)
  String username;

  String password;

  String email;

  String contact;

  String cin;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @ElementCollection
  // (fetch = FetchType.EAGER, targetClass = java.lang.String.class)
  @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "authority")
  List<String> roles;

  boolean enabled = true;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy
  @JoinTable(name = "user_checkpoints", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "checkpoint_id"))
  List<Checkpoint> checkpoints;

  @ManyToOne
  @JoinColumn(name = "department_id")
  Department major;

  @ManyToOne
  @JoinColumn(name = "plan_id")
  Plan plan;

  // public Role getRole() {
  // return role;
  // }
  //
  // public void setRole(Role role) {
  // this.role = role;
  // }

  public User(Long id, String username, String password, String email,
	  String contact, String cin, String firstName, String lastName,
	  List<String> roles, boolean enabled, List<Checkpoint> checkpoints,
	  Department major, Plan plan)
  {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.email = email;
	this.contact = contact;
	this.cin = cin;
	this.firstName = firstName;
	this.lastName = lastName;
	this.roles = roles;
	this.enabled = enabled;
	this.checkpoints = checkpoints;
	this.major = major;
	this.plan = plan;
  }

  public String getEmail()
  {
	return email;
  }

  public String getCin()
  {
	return cin;
  }

  public void setCin(String cin)
  {
	this.cin = cin;
  }

  public void setEmail(String email)
  {
	this.email = email;
  }

  public String getContact()
  {
	return contact;
  }

  public void setContact(String contact)
  {
	this.contact = contact;
  }

  public String getFirstName()
  {
	return firstName;
  }

  public void setFirstName(String firstName)
  {
	this.firstName = firstName;
  }

  public String getLastName()
  {
	return lastName;
  }

  public void setLastName(String lastName)
  {
	this.lastName = lastName;
  }

  public User()
  {
	enabled = true;
  }

  public Long getId()
  {
	return id;
  }

  public void setId(Long id)
  {
	this.id = id;
  }

  public String getUsername()
  {
	return username;
  }

  public void setUsername(String username)
  {
	this.username = username;
  }

  public String getPassword()
  {
	return password;
  }

  public void setPassword(String password)
  {
	this.password = password;
  }

  // public List<Role> getRoles() {
  // return roles;
  // }
  //
  // public void setRoles(List<Role> roles) {
  // this.roles = roles;
  // }

  // public Boolean getEnabled() {
  // return enabled;
  // }

  public List<String> getRoles()
  {
	return roles;
  }

  public void setRoles(List<String> roles)
  {
	this.roles = roles;
  }

  public void setEnabled(Boolean enabled)
  {
	this.enabled = enabled;
  }

  public List<Checkpoint> getCheckpoints()
  {
	return checkpoints;
  }

  public void setCheckpoints(List<Checkpoint> checkpoints)
  {
	this.checkpoints = checkpoints;
  }

  public Department getMajor()
  {
	return major;
  }

  public void setMajor(Department major)
  {
	this.major = major;
  }

  public Plan getPlan()
  {
	return plan;
  }

  public void setPlan(Plan plan)
  {
	this.plan = plan;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities()
  {
	Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	for (String role : roles)
	  authorities.add(new SimpleGrantedAuthority(role));
	return authorities;
  }

  @Override
  public boolean isAccountNonExpired()
  {
	return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
	return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
	return true;
  }

  @Override
  public boolean isEnabled()
  {
	return enabled;
  }

  public void setEnabled(boolean enabled)
  {
	this.enabled = enabled;
  }

}
