package cs520.gefp.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.Cell;
import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;
import cs520.gefp.model.Stage;
import cs520.gefp.model.dao.CellDao;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RunwayDao;
import cs520.gefp.model.dao.StageDao;


@Controller
@SessionAttributes({"stage", "cells","plan"})
public class StageController {

    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
    @Autowired
    private StageDao stageDao;
    
    @Autowired
    private RunwayDao runwayDao;
    
    @Autowired
    private CellDao cellDao;
    
    
    
    public void addCell(Cell cell)
	{
    	cell = cellDao.saveCell(cell);
		
	}
        
 	
	
    @RequestMapping(value="/department/plans/view/add/stage.html", method= RequestMethod.POST)
    public String addStage(@RequestParam Long planId, @RequestParam String newStage, HttpSession session, ModelMap modelMap)
    {
//    	if(session.getAttribute("userLogin")==null)
//    		return "redirect:/index.jsp";
    	Stage stage = new Stage();
    	stage.setPlan(planDao.getPlan(planId));
    	stage.setName(newStage);
    	stage = stageDao.saveStage(stage);
    	Plan plan = planDao.getPlan(planId);
    	if(plan.getStages()==null)
    		plan.setStages(new ArrayList<Stage>());
    	plan.getStages().add(stage);
    	plan = planDao.savePlan(plan);
    	modelMap.put("plan", plan);
    	
    	System.out.println("Stage added: "+ newStage);
    	return "redirect:/department/plans/view/"+planId+".html"; 
    }
    
    @RequestMapping(value="/department/plans/view/edit/stage.html", method= RequestMethod.POST)
    public String editStage(@RequestParam Long planId, @RequestParam Long stageId, @RequestParam String stageName,
    		HttpSession session, ModelMap modelMap)
    {
    	Plan plan = planDao.getPlan(planId);
    	Stage stage = stageDao.getStage(stageId);
    	for(Stage s : plan.getStages())
    	{
    		if(s.getId() == stageId)
    			s.setName(stageName);
    	}
    	
    	stage.setName(stageName);
    	stage = stageDao.saveStage(stage);
    	plan = planDao.savePlan(plan);
    	modelMap.put("plan", plan);
    	return "redirect:/department/plans/view/"+planId+".html";
    }
    
}
