package cs520.gefp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.criteria.Fetch;

@Entity
@Table(name="plans")
public class Plan implements Serializable
{
	@Id
	@GeneratedValue
	Long id;

    String name;
    
	@OrderColumn(name="index")
    @OneToMany(mappedBy="plan", cascade=CascadeType.ALL)
    List<Stage> stages;
    
	@OrderColumn(name="index")
    @OneToMany(mappedBy="plan", cascade=CascadeType.ALL)
    List<Runway> runways;
    
    @OrderBy
    @OneToMany(mappedBy="plan", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    List<Cell> cells;
    
    @ManyToOne
    Department department;
     
    @Column(name="published_date")
    Date publishedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	public List<Runway> getRunways() {
		return runways;
	}

	public void setRunways(List<Runway> runways) {
		this.runways = runways;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
    
    
}
