package cs520.gefp.model.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Checkpoint;
import cs520.gefp.model.User;
import cs520.gefp.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)  //use type if u get Lazy Initialization.
	private EntityManager entityManager;
	
	@Override
	public User getUser(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getUsers() {
		return entityManager.createQuery("from User order by id", User.class).getResultList();
	}

	@Override
	public User getUser(String username) {
		   return entityManager.createQuery(
		            "from User where username = :username", User.class )
		            .setParameter( "username", username )
		            .getSingleResult();
	}

	//@Transactional will save the changes to DB
	@Transactional
    @Override
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }
	
	@Override
    public List<User> getStudents( String term )
    {
		term = term.toLowerCase();
	        String query = "from User where cin = :term or lower(username) = :term "
		            + "or lower(firstName) = :term or lower(lastName) = :term "
		            + "or lower(firstName || ' ' || lastName) = :term "
		            + "or lower(email) = :term"
		            + " order by firstName asc";
	        
	        List<User> users = entityManager.createQuery( query, User.class )
	        		.setParameter( "term", term )
	        		.getResultList();
		    List<User> students = new ArrayList<User>();
		    System.out.println("Users size select query: "+users.size());
	        for( User u: users )
	        {
	        	for( String r: u.getRoles() )
	        	{
	        		if(r.equalsIgnoreCase("ROLE_USER"))
	        			students.add(u);
	        	}
	        }
	        
	        System.out.println("Users size after keeping ROLE_USER only: " + students.size());
	        
	        return students;
	 }
	
    @Override
    public List<User> searchUsersByPrefix( String term, int maxResults )
    {
        term = term.toLowerCase();

        String query = "select u from User u where cin like :term || '%' "
            + "or lower(username) like :term || '%' "
            + "or lower(firstName) like :term || '%' "
            + "or lower(lastName) like :term || '%' "
            + "or lower(firstName || ' ' || lastName) like :term || '%' "
            + "or lower(email) like :term || '%' "
            + "order by firstName asc";

        List<User> users = entityManager.createQuery( query, User.class )
        		.setParameter( "term", term )
        		.getResultList();
	    List<User> students = new ArrayList<User>();

	    for( User u: users )
        {
        	if(maxResults>0 && students.size() == maxResults)
        		break;
        	for( String r: u.getRoles() )
        	{
        		if(r.equalsIgnoreCase("ROLE_USER"))
        			students.add(u);
        	}
        }
        
        
        return students;
    }


	@Transactional
	@Override
	public User removeCheckpoint(User user, Checkpoint checkpoint) 
	{
		List<Checkpoint> checkpoints = getUser(user.getId()).getCheckpoints();
		System.out.println("CALL TO REMOVE Checkpoint-----");
		System.out.println("Contains: "+ checkpoints.contains(checkpoint));
		checkpoints.remove(checkpoint);
		System.out.println("After removing - Contains: "+ checkpoints.contains(checkpoint));
		
		return saveUser(user);
	}

	@Transactional
	@Override
	public User addCheckpoint(User user, Checkpoint checkpoint) 
	{
		List<Checkpoint> checkpoints = getUser(user.getId()).getCheckpoints();
		System.out.println("CALL TO ADD Checkpoint+++++");
		System.out.println("Contains: "+ checkpoints.contains(checkpoint));
		if(!checkpoints.contains(checkpoint))
		{
			checkpoints.add(checkpoint);
			System.out.println("After Adding - Contains: "+ checkpoints.contains(checkpoint));
		}
		
		return saveUser(user);
	}
	
	

}
