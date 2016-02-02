package cs520.gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department implements Serializable
{
  @Id
  @GeneratedValue
  Long id;

  String name;

  @OneToOne
  @JoinColumn(name = "plan_id")
  Plan currentPlan;

  @OrderBy
  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  List<Plan> plans;

  public Long getId()
  {
	return id;
  }

  public void setId(Long id)
  {
	this.id = id;
  }

  public String getName()
  {
	return name;
  }

  public void setName(String name)
  {
	this.name = name;
  }

  public Plan getCurrentPlan()
  {
	return currentPlan;
  }

  public void setCurrentPlan(Plan currentPlan)
  {
	this.currentPlan = currentPlan;
  }

  public List<Plan> getPlans()
  {
	return plans;
  }

  public void setPlans(List<Plan> plans)
  {
	this.plans = plans;
  }

}
