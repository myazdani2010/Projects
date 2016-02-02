package cs520.gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable
{
  @Id
  @GeneratedValue
  Long id;

  String role;

  String description;

  // @OrderBy
  // @ManyToMany(mappedBy="roles")
  // List<User> users;

  public Long getId()
  {
	return id;
  }

  public void setId(Long id)
  {
	this.id = id;
  }

  public String getDescription()
  {
	return description;
  }

  public void setDescription(String description)
  {
	this.description = description;
  }

  // public List<User> getUsers() {
  // return users;
  // }
  //
  // public void setUsers(List<User> users) {
  // this.users = users;
  // }

  public String getRole()
  {
	return role;
  }

  public void setRole(String role)
  {
	this.role = role;
  }

}
