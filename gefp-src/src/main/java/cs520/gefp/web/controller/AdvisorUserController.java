package cs520.gefp.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.Checkpoint;
import cs520.gefp.model.User;
import cs520.gefp.model.dao.CheckpointDao;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.RoleDao;
import cs520.gefp.model.dao.UserDao;


@Controller
@SessionAttributes("student")
public class AdvisorUserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private CheckpointDao checkpointDao;
    

     
    @RequestMapping(value="/plan/student/viewPlan.html", method= RequestMethod.GET)
    public String search2( HttpSession session, ModelMap modelMap, @RequestParam Long studentId)
    {
    	
    	User student = userDao.getUser(studentId);
    	System.out.println(student.getUsername());
    	
    	modelMap.put("student", student);
    	modelMap.put("plan", student.getPlan());

    	return "viewStudentPlan2";
    }
    
    
    //checkpoint check/uncheck by Advisor
    @RequestMapping(value="/plan/student/checkCheckpoint.html")
	public @ResponseBody String checkCheckpointByAdvisor( @RequestParam Long checkpointId, ModelMap models, HttpSession session, HttpServletRequest req )
	{
    	Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpointId);
    	User user = (User) session.getAttribute("student");
    	
    	System.out.println(checkpoint.getDescription());
    	
    	System.out.println("Srudent: "+user.getUsername());
    	
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
     
    
    
}
