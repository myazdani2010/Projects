package cs520.gefp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.User;
import cs520.gefp.model.dao.DepartmentDao;
import cs520.gefp.model.dao.UserDao;
import cs520.gefp.security.SecurityUtils;


//@SessionAttributes will save the attribute in the session scope
//dont forget to use seComplete() on the generated Session attribute to remove it after using the session
@Controller
@SessionAttributes("departments")
public class DepartmentController {

 
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private UserDao userDao;

    @RequestMapping("/departments.html")
    public String departments( ModelMap models )
    {
//        models.put( "departments", departmentDao.getDepartments() );
//    	List<User> user = userDao.getUsers();
    	User secureU = SecurityUtils.getUser();
    	System.out.println("Username: "+secureU.getUsername());    	
        return "departments";
    }
    
    
}
