package cs520.gefp.model;

public class UserTag
{

  public Long id;
  public String username;
  public String firstName;
  public String lastName;
  public String cin;

  public UserTag(Long id, String username, String firstName, String lastName,
	  String cin)
  {
	super();
	this.id = id;
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
	this.cin = cin;
  }

  public UserTag()
  {
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

  public String getCin()
  {
	return cin;
  }

  public void setCin(String cin)
  {
	this.cin = cin;
  }

}