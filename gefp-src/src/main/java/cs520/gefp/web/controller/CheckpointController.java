package cs520.gefp.web.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.EnumeratedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.Cell;
import cs520.gefp.model.Checkpoint;
import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;
import cs520.gefp.model.Stage;
import cs520.gefp.model.User;
import cs520.gefp.model.dao.CellDao;
import cs520.gefp.model.dao.CheckpointDao;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RunwayDao;
import cs520.gefp.model.dao.StageDao;
import cs520.gefp.model.dao.UserDao;


@Controller
@SessionAttributes({"cell", "checkpoint", "plan"})
public class CheckpointController {

    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
    @Autowired
    private StageDao stageDao;
    
    @Autowired
    private CellDao cellDao;
    
    @Autowired
    private RunwayDao runwayDao;
    
    @Autowired
    private CheckpointDao checkpointDao;
    
    @Autowired
    private UserDao userDao;
    
    
    @RequestMapping(value="/department/plans/add/addCheckpoint.html", method= RequestMethod.GET)
    public String addCheckpoint1()
    {
      
      return "addCheckpoint";
    }
 
    @RequestMapping(value="/department/plans/add/addCheckpoint.html", method= RequestMethod.POST)
    public String addCheckpoint2(@RequestParam Long planId, @RequestParam Long departmentId, ModelMap models,
    		@RequestParam Long stageId, @RequestParam Long runwayId,
    		@RequestParam String newCheckpoint, HttpSession session)
    {
    	Plan plan = planDao.getPlan(planId);
    	Runway runway = runwayDao.getRunway(runwayId);
    	Stage stage = stageDao.getStage(stageId);
    	
    	Checkpoint cp = new Checkpoint();
    	cp.setDescription(newCheckpoint);
    	Cell cell = cellDao.getCell(plan, runway, stage);
    	boolean isNewCell = false;
    	if(cell == null)
    	{
    		isNewCell=true;
    		System.out.println("cell is null");
    		cell = new Cell();
    		cell.setPlan(plan);
    		cell.setRunway(runway);
    		cell.setCheckpoints(new ArrayList<Checkpoint>());
    		cell.setStage(stage);
    		
    		cell = cellDao.saveCell(cell);
//    		cell.getCheckpoints().add(cp);
//    		cp.setCell(cell);
//        	cp = checkpointDao.saveCheckpoint(cp);
    	}
    	
		//cell = cellDao.saveCell(cell);
		//cell.getCheckpoints().add(cp);
		cp.setCell(cell);
    	cp = checkpointDao.saveCheckpoint(cp);
    		
    
    	
    	

    	
    	if(isNewCell)
    	{
    		if(plan.getCells() == null)
    			plan.setCells(new ArrayList<Cell>());
    			
    		plan.getCells().add(cell);
    	}
   	
    	
    	System.out.println("cell id = "+cell.getId());
    	for(Cell c : plan.getCells())
    	{
    		System.out.println(c.getId());
    		if(c.getId().equals(cell.getId()))
    		{
    			System.out.println("checkpoint added to the cell of Plan");
    			c.getCheckpoints().add(cp);
    		}
    	}
    	
    	
    	plan = planDao.savePlan(plan);
    	
    	
    	for(Cell c : plan.getCells())
    	{
    		for(Checkpoint check : c.getCheckpoints())
    			System.out.println(cell.getId()+ "- "+check.getDescription());
    	}

    	//plan = planDao.getPlan(planId);
    	//models.put("plan", plan);
    	//session.removeAttribute("plan");
    	
    	return "redirect:/department/plans/view/"+planId+".html"; 
    }
    
    
    @RequestMapping(value="/department/plans/edit/editCheckpoint.html", method= RequestMethod.GET)
    public String editCheckpoint1(@RequestParam Long checkpointId, HttpSession session, 
    		ModelMap models)
    {
//    	if(session.getAttribute("userLogin")==null)
//    		return "redirect:/index.jsp";
    	
    	models.put("checkpoint", checkpointDao.getCheckpoint(checkpointId));
    	return "editCheckpoint";
    
    }
    
    @RequestMapping(value="/department/plans/edit/editCheckpoint.html", method= RequestMethod.POST)
    public String editCheckpoint2(HttpSession session, 
    		@ModelAttribute Checkpoint checkpoint,
    		@RequestParam Long stageId, @RequestParam Long planId,
    		@RequestParam Long runwayId)
    {
//    	if(session.getAttribute("userLogin")==null)
//    		return "redirect:/index.jsp";
    	
    	//Plan plan = checkpoint.getCell().getPlan();
    	Plan plan = planDao.getPlan(planId);
    	Runway runway = runwayDao.getRunway(runwayId);
    	Stage stage = stageDao.getStage(stageId);
    	
    	Cell cell = cellDao.getCell(plan, runway, stage);
    	if(cell == null)
    	{
    		cell = new Cell();
    		cell.setPlan(plan);
    		cell.setRunway(runway);
    		cell.setStage(stage);
    	}
    	    	
    	cell = cellDao.saveCell(cell);
    	
    	checkpoint.setCell(cell);
    	
    	checkpointDao.saveCheckpoint(checkpoint);
    	
    	//without this changes will not be shown in the plan although it has been saved in the database
    	planDao.refreshPlan(plan);
    	
    	return "redirect:/department/plans/view/"+ plan.getId() +".html";
    
    }
    
    //checkpoint check/uncheck by student
    @RequestMapping(value="/user/view/checkCheckpoint.html")
	public @ResponseBody String checkCheckpoint( @RequestParam Long checkpointId, ModelMap models, HttpSession session, HttpServletRequest req )
	{
    	Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpointId);
    	User user = (User) session.getAttribute("userLogin");
    	
//    	if(user != null)
//    		System.out.println(user.getUsername());
    	
    	System.out.println(checkpoint.getDescription());
    	
    	boolean isCheckpointFound = false;
    	for(Checkpoint c : user.getCheckpoints())
    	{
    		if(c.getId() == checkpoint.getId())
    		{
    			isCheckpointFound = true;
    			checkpoint= c;
    		}
    	}
   
    	if(isCheckpointFound)
    	{
    		int index = -1;
    		for(Checkpoint c : user.getCheckpoints())
    		{
    			if(c.getId() == checkpoint.getId()) 
    				index = user.getCheckpoints().indexOf(c);
    		}
    		System.out.println("Index: "+ index);
    		user.getCheckpoints().remove(index);
    		System.out.println("Checkpoint Removed***");
    	}
    	else
    	{
    		user.getCheckpoints().add(checkpoint);
    		System.out.println("Checkpoint Added");
    	}
    	
    	System.out.println(user.getCheckpoints().size());
    	user = userDao.saveUser(user);
    	
    	if(isCheckpointFound)
    		return "UNCHECKED: "+checkpoint.getDescription();
    	else
    		return "CHECKED: "+checkpoint.getDescription();
	
	}
    
    @RequestMapping(value="/department/plans/edit/deleteCheckpoint.html")
    public String deleteCehckpoint(@RequestParam Long planId, @RequestParam Long checkpointId,
    		@RequestParam Long cellId, ModelMap models, HttpSession session)
    {
    	
    	Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpointId);
    	checkpoint.setDeleted(true);
    	checkpoint = checkpointDao.saveCheckpoint(checkpoint);
    	
    	Cell cell = cellDao.getCell(cellId);
    	cell = cellDao.saveCell(cell);
    	
//    	Cell cell = checkpoint.getCell();
    	
//    	System.out.println("***checkpoints after delete set true(before saving the Cell in DB):");
//    	for(Checkpoint c : cell.getCheckpoints())
//    	{
//    		System.out.println(c.getDescription() + " - " + c.isDeleted());
//    	}
    	
    	
    	
//    	System.out.println("***checkpoints after delete set true:");
//    	for(Checkpoint c : cell.getCheckpoints())
//    	{
//    		System.out.println(c.getDescription() + " - " + c.isDeleted());
//    	}
    	
    	Plan plan = planDao.getPlan(planId);
    	
    	
    	int index = -1;
    	System.out.println("**checkpoints from Plan:");
    	for(Cell c : plan.getCells())
    	{
    		if(c.getId() == cellId)
    		{
    			index = plan.getCells().indexOf(c);
    		}
    	}
    	if(index >-1)
    	{
    		System.out.println("*********** new cell added to plan added");
    		plan.getCells().remove(index);
    		plan.getCells().add(cell);
    	}
    	System.out.println("***********8Cell data:");
    	for(Cell c : plan.getCells())
    	{
    		if(c.getId() == cellId)
    		{
    			for(Checkpoint ch : c.getCheckpoints())
    			{
    				System.out.println(ch.getDescription() + " - " + ch.isDeleted());
    			}
    		}
    	}
    	plan = planDao.savePlan(plan);
    	
    	models.put("plan", plan);
    	
    	return "redirect:/department/plans/view/" + planId + ".html";
    	
    }
    

}
