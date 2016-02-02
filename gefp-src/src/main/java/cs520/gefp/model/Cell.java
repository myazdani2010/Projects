package cs520.gefp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cells")
public class Cell implements Serializable
{
  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  @JoinColumn(name = "plan_id")
  Plan plan;

  @ManyToOne
  @JoinColumn(name = "stage_id")
  Stage stage;

  @ManyToOne
  @JoinColumn(name = "runway_id")
  Runway runway;

  @OrderColumn(name="index")
  @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL)
  List<Checkpoint> checkpoints;

  public Long getId()
  {
	return id;
  }

  public void setId(Long id)
  {
	this.id = id;
  }

  public Plan getPlan()
  {
	return plan;
  }

  public void setPlan(Plan plan)
  {
	this.plan = plan;
  }

  public Stage getStage()
  {
	return stage;
  }

  public void setStage(Stage stage)
  {
	this.stage = stage;
  }

  public Runway getRunway()
  {
	return runway;
  }

  public void setRunway(Runway runway)
  {
	this.runway = runway;
  }

  public List<Checkpoint> getCheckpoints()
  {
	return checkpoints;
  }

  public void setCheckpoints(List<Checkpoint> checkpoints)
  {
	this.checkpoints = checkpoints;
  }

}
