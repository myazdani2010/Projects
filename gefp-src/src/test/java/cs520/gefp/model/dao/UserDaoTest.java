package cs520.gefp.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "UserDao")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests
{
    @Autowired
    UserDao userDao;

    @Test
    public void getUser()
    {
        assert userDao.getUser("cysun").getUsername().equalsIgnoreCase("cysun");
    }

    @Test
    public void getUsers()
    {
    	assert userDao.getUsers().size()>0;
    }
}
