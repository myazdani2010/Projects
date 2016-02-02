package cs520.gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "checkpoints")
public class Checkpoint implements Serializable
{
  @Id
  @GeneratedValue
  Long id;
  
  int index = 0;

  String description;

  String info;

  @ManyToOne
  @JoinColumn(name = "cell_id")
  Cell cell;

  boolean deleted = false;

  // @OrderBy
  // @ManyToMany(mappedBy="checkpoints")
  // List<User> users;

  public Long getId()
  {
	return id;
  }

  public void setId(Long id)
  {
	this.id = id;
  }

  public int getIndex()
  {
    return index;
  }

  public void setIndex(int index)
  {
    this.index = index;
  }

  public String getDescription()
  {
	return description;
  }

  public void setDescription(String description)
  {
	this.description = description;
  }

  public Cell getCell()
  {
	return cell;
  }

  public void setCell(Cell cell)
  {
	this.cell = cell;
  }

  // public List<User> getUsers() {
  // return users;
  // }
  //
  // public void setUsers(List<User> users) {
  // this.users = users;
  // }

  public String getInfo()
  {
	return info;
  }

  public void setInfo(String info)
  {
	this.info = info;
  }

  public boolean isDeleted()
  {
	return deleted;
  }

  public void setDeleted(boolean deleted)
  {
	this.deleted = deleted;
  }

}
