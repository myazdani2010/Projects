package cs520.gefp.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.Department;
import cs520.gefp.model.Plan;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RunwayDao;
import cs520.gefp.model.dao.StageDao;


//@SessionAttributes will save the attribute in the session scope
//dont forget to use seComplete() on the generated Session attribute to remove it after using the session
@SessionAttributes({"plan", "department", "publishPlans", "publishPlan"})
@Controller
public class PlanController {

 
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
    @Autowired
    private StageDao stageDao;
    
    @Autowired
    private RunwayDao runwayDao;
    
    
    @RequestMapping("/department/plans/{planId}.html")
    public String plans(@PathVariable Long planId, ModelMap models, HttpSession session )
    {
    	
    	List<Plan> plans = departmentDao.getDepartment(planId).getPlans();
        models.put( "plans", plans );
        models.put( "department", departmentDao.getDepartment(planId));
       

        
        return "plans";
        	
    }
    
    //Student redirected here after login
	@RequestMapping(value="/department/plans/view/{id}.html")
	public String view( @PathVariable Long id, @RequestParam(value = "editStageId", required=false) Long editStageId , 
			@RequestParam(value = "editRunwayId", required=false) Long editRunwayId ,
			ModelMap models, HttpSession session )
	{

    	Plan p = planDao.getPlan(id);
		models.put( "plan", p );
		models.put( "department", p.getDepartment() );
		
		if(editStageId != null )
			models.put( "editStage", stageDao.getStage(editStageId) );
		
		if(editRunwayId != null )
			models.put( "editRunway", runwayDao.getRunway(editRunwayId) );
		
		return "viewPlan";
	}
    
	
    @RequestMapping(value="/department/plans/add.html", method= RequestMethod.POST)
    public String add(@RequestParam Long departmentId, @RequestParam String newPlan, HttpSession session)
    {
    	
		Plan plan = new Plan();
		plan.setDepartment(departmentDao.getDepartment(departmentId));
		plan.setName(newPlan);
    	planDao.savePlan(plan);
    	
    	
    	return "redirect:/department/plans/"+departmentId+".html";
    	
    }
    
    
    
    @RequestMapping(value="/department/plans/changePlan.html", method= RequestMethod.GET)
	public String changePlan(@RequestParam Long departmentId, @RequestParam Long planId, 
			ModelMap models, HttpSession session )
	{
    	
    	Plan plan = planDao.getPlan(planId);
    	plan.setPublishedDate(new Date());
    	
    	Department department = departmentDao.getDepartment(departmentId);
    	//department = departmentDao.changePlan(department, plan);
    	department.setCurrentPlan(plan);
    	department = departmentDao.saveDepartment(department);
    	departmentDao.refreshDepartment(department);
    	models.put("plan", plan);    
    	models.put( "department", department );
    	
    	return "plans";
	}
    
    
    @RequestMapping(value="/plan/publishPlans.html", method= RequestMethod.GET)
  	public String publishPLan( ModelMap models, HttpSession session )
  	{
    	List<Department> departments = departmentDao.getDepartments();
    	List<Plan> plans = new ArrayList<Plan>();
    	
    	for(Department dep : departments)
    	{
    		plans.add(dep.getCurrentPlan());
    	}
    	
    	models.put("publishPlans", plans);
    	return "publishPlans";
  	}
    
    @RequestMapping(value="/plan/viewPublishPlans.html", method= RequestMethod.GET)
   	public String publishPLan2( ModelMap models, HttpSession session,  @RequestParam Long planId)
   	{
     	
    	Plan plan = planDao.getPlan(planId);
     	
     	models.put("publishPlan", plan);
     	return "viewPublishPlans";
   	}
    
}
