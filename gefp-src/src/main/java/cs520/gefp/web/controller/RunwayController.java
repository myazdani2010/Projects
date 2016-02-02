package cs520.gefp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cs520.gefp.model.Department;
import cs520.gefp.model.Runway;
import cs520.gefp.model.Stage;
import cs520.gefp.model.User;
import cs520.gefp.model.Plan;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RunwayDao;
import cs520.gefp.model.dao.UserDao;


//@SessionAttributes will save the attribute in the session scope
//dont forget to use seComplete() on the generated Session attribute to remove it after using the session

@Controller
@SessionAttributes({"runway2", "plan"})
public class RunwayController {

    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
    @Autowired
    private RunwayDao runwayDao;
	
	
    @RequestMapping(value="/department/plans/view/add/runway.html", method= RequestMethod.POST)
    public String addRunway(@RequestParam Long planId, @RequestParam String newRunway, HttpSession session, ModelMap modelMap)
    {
//    	if(session.getAttribute("userLogin")==null)
//    		return "redirect:/index.jsp";
    	Plan plan = planDao.getPlan(planId);
    	Runway runway = new Runway();
    	runway.setPlan(plan);
    	runway.setName(newRunway);
    	runway = runwayDao.saveRunway(runway);
    	if(plan.getRunways()==null)
    		plan.setRunways(new ArrayList<Runway>());
    	
    	plan.getRunways().add(runway);
    	plan = planDao.savePlan(plan);
    	modelMap.put("plan", plan);
    	return "redirect:/department/plans/view/"+planId+".html";
    }
    
    
    @RequestMapping(value="/department/plans/view/edit/runway.html", method= RequestMethod.POST)
    public String editRunway(@RequestParam Long planId, @RequestParam Long runwayId, @RequestParam String runwayName,
    		HttpSession session, ModelMap modelMap)
    {
    	Plan plan = planDao.getPlan(planId);
    	Runway runway = runwayDao.getRunway(runwayId);
    	for(Runway r : plan.getRunways())
    	{
    		if(r.getId() == runwayId)
    			r.setName(runwayName);
    	}
    	
    	runway.setName(runwayName);
    	runway = runwayDao.saveRunway(runway);
    	plan = planDao.savePlan(plan);
    	modelMap.put("plan", plan);
    	return "redirect:/department/plans/view/"+planId+".html";
    }
}
