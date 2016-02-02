package cs520.gefp.web.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cs520.gefp.model.Department;
import cs520.gefp.model.Plan;
import cs520.gefp.model.User;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.PlanDao;
import cs520.gefp.model.dao.RoleDao;
import cs520.gefp.model.dao.UserDao;
import cs520.gefp.security.SecurityUtils;


//@SessionAttributes will save the attribute in the session scope
//dont forget to use seComplete() on the generated Session attribute to remove it after using the session
@Controller
@SessionAttributes({"userLogin","departments","plans","department", "plan"})
//@SessionAttributes({"userLogin","departments"})
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
//    @RequestMapping(value="/index.html", method= RequestMethod.GET)
//    public String logout( ModelMap models, SessionStatus status)
//    {
//    	status.setComplete();
//        return "redirect:/j_spring_security_logout";
//    }
    
    @RequestMapping(value="/index.html", method= RequestMethod.POST)
    public String login( @RequestParam String username, 
    		@RequestParam String password ,ModelMap models)
    {
    	User u = SecurityUtils.getUser();
    	if( u != null )
    	{
    		if(u.getPassword().equals(password))
    		{
    			//models.put("userLogin", u);
    			
    			if(u.getRoles().get(0).equalsIgnoreCase("ROLE_ADMIN"))
    			{
    				System.out.println("Admin Login");  
    				return "redirect:/index.jsp";
    			}
    			else if(u.getRoles().get(0).equalsIgnoreCase("ROLE_USER"))
    			{
    		    	List<Department> departments = departmentDao.getDepartments();
    		    	models.put("departments", departments);
    		    	System.out.println("Student Login");
    				return "redirect:department/plans/view/"+ u.getPlan().getId() +".html";
    				    	
    			}
    			else if(u.getRoles().get(0).equalsIgnoreCase("ROLE_ADVIS"))
    			{
    		    	List<Department> departments = departmentDao.getDepartments();
    		    	models.put("departments", departments);
    				System.out.println("Advisor Login");    	
    				return "redirect:plan/student/search.html";
    			}
    		}
    	}
        return "redirect:/index.jsp";
    }
    
    @RequestMapping("/user/view/flightPlan.html")
    public String userHome( HttpSession session, ModelMap models )
    {
//    	List<Plan> plans = departmentDao.getDepartment(SecurityUtils.getUser().getPlan().getId()).getPlans();
        //models.put( "plans", plans );
        //models.put( "department", departmentDao.getDepartment(SecurityUtils.getUser().getPlan().getId()));
		return "viewStudentPlan";
    }
    
    @RequestMapping("/user/profile.html")
    public String userProfile( HttpSession session, ModelMap models )
    {
//    	User user = (User)session.getAttribute("userLogin");
    	User user = SecurityUtils.getUser();
//    	if(user == null)
//    		return "redirect:/index.jsp";
    	
    	List<Department> departments = departmentDao.getDepartments();
    	models.put("userProfile", user);
    	models.put("departments", departments);
                
        return "studentProfile";
    }
    
    //saves the changed profile
    @RequestMapping(value="/user/profile.html", method=RequestMethod.POST)
    public String userProfile2( HttpSession session, ModelMap models,
    		@RequestParam String firstName, @RequestParam String lastName,
    		@RequestParam String password1, @RequestParam String password2,
    		@RequestParam String email, @RequestParam String contact)
    {
    	System.out.println(firstName);
    	System.out.println(lastName);
    	System.out.println(password1 +"  "+ password2);
    	System.out.println(email);
    	System.out.println(contact);
    	System.out.println("FORM Submited !!!");
    	 
//    	User user = (User)session.getAttribute("userLogin");
    	User user = SecurityUtils.getUser();
    	if(user == null)
    		return "redirect:/index.jsp";
    	
    	if(password1.trim().length()>3 && password2.trim().length()>3 && password1.equals(password2))
    	{
    		String passPattern1 = "(.*[0-9])(.*[a-z])";
    		String passPattern2 = "(.*[a-z])(.*[0-9])";
    		if(password1.matches(passPattern1) || password1.matches(passPattern2))
    		{
    			user.setPassword(password1);
	    		user.setFirstName(firstName);
		    	user.setLastName(lastName);
		    	user.setEmail(email);
		    	user.setContact(contact);
		    	user= userDao.saveUser(user);
    		}
    		else
    		{
    			models.put("passError", "enter a valid password!");
        		return "studentProfile";
    		}
    			
    		
    	}
    	else if(password1.trim().length()==0 && password2.trim().length()==0)
    	{
	    	user.setFirstName(firstName);
	    	user.setLastName(lastName);
	    	user.setEmail(email);
	    	user.setContact(contact);
	    	user= userDao.saveUser(user);
    	}
    	else
    	{
    		models.put("passError", "enter a valid password");
    		return "studentProfile";
    	}
    	
    	
                
        return "viewStudentPlan";
    }
    
    //Changes the department of Student
    @RequestMapping(value="/user/view/changeDep.html", method=RequestMethod.POST)
    public String chengeDep( HttpSession session, ModelMap models,
    		@RequestParam Long departmentId, @RequestParam(required=false) Long planId)
    {
    	
 //   	User user = (User)session.getAttribute("userLogin");
    	User user = SecurityUtils.getUser();
    	if(user == null)
    		return "redirect:/index.jsp";
    	Department dep = departmentDao.getDepartment(departmentId);
    	
    	user.setMajor(dep);
    	user.setPlan(dep.getCurrentPlan());
    	
    	user= userDao.saveUser(user);
    	//models.remove("userLogin");
    	models.put("userLogin", user);
    	
    	Department department = user.getMajor();
    	Plan userPlan = userDao.getUser(user.getUsername()).getPlan();
    	//List<Plan> plans = department.getPlans();
    	
    	session.removeAttribute("plan");
    	session.removeAttribute("department");
       // models.put("plans", plans );
        models.put("department",department );
        models.put("plan", userPlan);
        
//        session.setAttribute("runways", userPlan.getRunways());
//        session.setAttribute("stages", userPlan.getStages());
//        session.setAttribute("cells", userPlan.getCells());
        
        System.out.println("Major Changed.");        
       // return "redirect:/department/plans/view/"+ user.getPlan().getId() +".html";
    	//return "redirect:/user/view/flightPlan.html";
        return "viewStudentPlan";
    }
  
    
}
