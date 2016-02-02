package cs520.gefp.web.controller;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs520.gefp.model.User;
import cs520.gefp.model.dao.UserDao;

@SessionAttributes("students")
@Controller
public class SearchController {
 
	List<User> students= new ArrayList<User>();
 
	@Autowired
	UserDao userDao;
	
	
	
	@RequestMapping(value = "/example.html", method = RequestMethod.GET)
	public String getPages() {
		System.out.println("Example");
 
		//ModelAndView model = new ModelAndView("example");
		return "example";
	}
	
    @RequestMapping(value="/plan/student/search.html", method= RequestMethod.GET)
    public String search( HttpSession session, ModelMap modelMap, 
    		@RequestParam(value="search", required=false) String search)
    {
    	System.out.println("search");
		List<User> students = null;
		if(search!=null)
		{
			students = userDao.searchUsersByPrefix(search, -1);
			modelMap.put("students", students);
			modelMap.put("term", search);
		}
		System.out.println(students);
		//modelMap.put("searchstring", term);

        return "search";
    }
 
	@RequestMapping(value = "/plan/student/search.html", method = RequestMethod.POST)
	public String searchStudent(ModelMap modelMap,
			@RequestParam(value="search", required=false) String search) {

		System.out.println("search");
		List<User> students = null;
		if(search!=null)
		{
			students = userDao.searchUsersByPrefix(search, -1);
			modelMap.put("students", students);
			modelMap.put("term", search);
		}
		System.out.println(students);
		//modelMap.put("searchstring", term);

		return "search";
	}
	
    @RequestMapping(value = "/autoComplete.html")
    public String courses( ModelMap modelMap, @RequestParam String term,
        HttpServletResponse response ) throws JSONException, IOException
    {
    	System.out.println("autoComplete");
        JSONArray jsonArray = new JSONArray();

        for( User u : userDao.searchUsersByPrefix(term, 5) )
        {
            String label = u.getCin() + " " + u.getFirstName() + " " + u.getLastName();
            Map<String, String> json = new HashMap<String, String>();
            json.put( "id", u.getUsername() );
            json.put( "value", u.getUsername() );
            json.put( "label", label);
            jsonArray.put( json );
        }
        modelMap.put("term", term);
        
        response.setContentType( "application/json" );
        jsonArray.write( response.getWriter() );
        return null;
    }

}