package cs520.gefp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cs520.gefp.model.User;
import cs520.gefp.model.dao.UserDao;


@Service("userService")//creates a bean. check in applicationContext.xml
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException, DataAccessException
    {
        User user = userDao.getUser( username );
        if( user == null )
            throw new UsernameNotFoundException( username + " is not found." );

        return user;
    }

}
