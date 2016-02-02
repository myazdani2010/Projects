package cs520.gefp.web.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import cs520.gefp.model.dao.CellDao;
import cs520.gefp.model.dao.CheckpointDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RunwayDao;
import cs520.gefp.model.dao.StageDao;

@Controller
@SessionAttributes("plan")
public class TableOrderController {
 
	@Autowired
	PlanDao planDao;
	
	@Autowired
	StageDao stageDao;
	
	@Autowired
	CheckpointDao checkpointDao;
	
	@Autowired
	RunwayDao runwayDao;
	
	@Autowired
	CellDao cellDao;
	
	
	  @RequestMapping(value="/orderTableStage.html", method= RequestMethod.POST)
	  public @ResponseBody String stage(@RequestParam(value="index") int index, @RequestParam(value="planId") Long planId,
		  @RequestParam(value="stageId") Long stageId )
	  {
		
		Stage stage = stageDao.getStage(stageId);
		Plan plan = planDao.getPlan(planId);
		
		List<Stage> stages = plan.getStages();
		
//		System.out.println("Before removing List: ...");
//		for(Stage s : plan.getStages())
//		  System.out.println(s.getIndex() + "- " + s.getName());
		
		stages.remove(stage.getIndex());
		
//		System.out.println("after removing List: ...");
//		for(Stage s : plan.getStages())
//		  System.out.println(s.getIndex() + "- " + s.getName());
		
		stages.add(index, stage);
		
//		System.out.println("After adding List: ...");
//		for(Stage s : plan.getStages())
//		  System.out.println(s.getIndex() + "- " + s.getName());
		
		plan = planDao.savePlan(plan);

		return null;

	  }	
	  
	  @RequestMapping(value="/orderTableCheckpoint.html", method= RequestMethod.POST)
	  public @ResponseBody String checkpoint(@RequestParam(value="index") int index, @RequestParam(value="planId") Long planId,
		  @RequestParam(value="checkpointId") Long checkpointId, ModelMap modelMap )
	  {
		
		Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpointId);
		Cell cell = cellDao.getCell(checkpoint.getCell().getId());
		Plan plan = planDao.getPlan(planId);
		
		
		List<Checkpoint> checkpoints = cell.getCheckpoints();
		
//		System.out.println("Before removing List: ...");
//		for(Checkpoint cp : checkpoints)
//		  System.out.println(cp.getIndex() + "- " + cp.getDescription());
		
		checkpoints.remove(checkpoint.getIndex());
		
//		System.out.println("after removing List: ...");
//		for(Checkpoint cp : checkpoints)
//		  System.out.println(cp.getIndex() + "- " + cp.getDescription());
		
		checkpoints.add(index, checkpoint);
		
//		System.out.println("After adding List: ...");
//		for(Checkpoint cp : checkpoints)
//		  System.out.println(cp.getIndex() + "- " + cp.getDescription());
		
		cell = cellDao.saveCell(cell);
		
		planDao.refreshPlan(plan);
		modelMap.put("plan", plan);

		return null;

	  }	
	  
	  @RequestMapping(value="/orderTableRunway.html")
	  public @ResponseBody String runway(@RequestParam(value="indexStart") int indexStart,
		  @RequestParam(value="indexEnd") int indexEnd, @RequestParam(value="planId") Long planId  )
	  {
		
		Plan plan = planDao.getPlan(planId);
		List<Runway> runways = plan.getRunways();
		
		Runway runway = runways.get(indexStart);
		
		
		runways.remove(indexStart);
//		System.out.println("After adding List: ...");
//		for(Runway r : runways)
//		  System.out.println(r.getIndex() + "- " + r.getName());
		
		runways.add(indexEnd, runway);
		
//		System.out.println("After adding List: ...");
//		for(Runway r : runways)
//		  System.out.println(r.getIndex() + "- " + r.getName());
		
		planDao.savePlan(plan);
		
		return null;
	  }

}