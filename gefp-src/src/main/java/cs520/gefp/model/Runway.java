package cs520.gefp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "runways")
public class Runway implements Serializable
{
  @Id
  @GeneratedValue
  Long id;

  int index = 0;
  
  String name;

  @ManyToOne
  @JoinColumn(name = "plan_id")
  Plan plan;

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

  public String getName()
  {
	return name;
  }

  public void setName(String name)
  {
	this.name = name;
  }

  public Plan getPlan()
  {
	return plan;
  }

  public void setPlan(Plan plan)
  {
	this.plan = plan;
  }

}
