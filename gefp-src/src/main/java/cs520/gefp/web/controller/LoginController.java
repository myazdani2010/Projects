package cs520.gefp.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class LoginController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private PlanDao planDao;
    
    //First page for all users.
    @RequestMapping(value="/login.html", method= RequestMethod.GET)
    public String login( ModelMap models, SessionStatus status)
    {
    	models.put( "departments", departmentDao.getDepartments() );
    	System.out.println("Login page");
        return "login";
    }
    
    @RequestMapping(value="/logout.html",  method= RequestMethod.GET)
    public String logout( ModelMap models, SessionStatus status)
    {
    	System.out.println("Logout...GET");
    	status.setComplete();
        return "redirect:/j_spring_security_logout";
    }
    
    //upon success login welcome will be called.
    @RequestMapping(value="/welcome.html", method= RequestMethod.GET)
    public String welcome( ModelMap models, SessionStatus status, HttpSession session)
    {
   
    	User secureU = SecurityUtils.getUser();
    	
    	System.out.println("Welcome Page");
    	System.out.println("Username: "+secureU.getUsername());
    	System.out.println("User password: "+secureU.getPassword());
    	System.out.println("Role: " + secureU.getRoles().get(0));
    	
    	models.put("userLogin", secureU);// keeps the user in the session.
        //models.put( "departments", departmentDao.getDepartments() );
        
        
        if(secureU.getRoles().get(0).equals("ROLE_ADMIN"))
        	return "redirect:departments.html";
        else if(secureU.getRoles().get(0).equals("ROLE_ADVIS"))
        {
        	
    		return "redirect:/plan/student/search.html";
        }
        else
        {
        	//user is student.
        	
        	Department department = secureU.getMajor();
        	Plan userPlan = planDao.getPlan(secureU.getPlan().getId());
        	List<Plan> plans = department.getPlans();
        	
        	System.out.println("User plan Name:" + userPlan.getName());
        	System.out.println("User Major Name:" + department.getName());
        	
        	
            models.put("plans", plans );
            models.put("department",department );
            models.put("plan", userPlan);
//            session.setAttribute("runways", userPlan.getRunways());
//            session.setAttribute("stages", userPlan.getStages());
//            session.setAttribute("cells", userPlan.getCells());
        	return "redirect:/user/view/flightPlan.html";
        }
    }
    
    @RequestMapping(value="/loginfailed.html", method= RequestMethod.GET)
    public String logout2( ModelMap models, SessionStatus status)
    {
    	System.out.println("Login Failed!!!");
        return "login";
    }
        
}
