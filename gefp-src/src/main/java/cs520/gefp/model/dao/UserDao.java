package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Checkpoint;
import cs520.gefp.model.User;

public interface UserDao {
	User getUser( Long id );
	List<User> getUsers();
	List<User> getStudents(String term);
	User getUser( String username);
	User saveUser(User user);
	User removeCheckpoint(User user, Checkpoint checkpoint);
	User addCheckpoint(User user, Checkpoint checkpoint);
	List<User> searchUsersByPrefix(String term, int maxResults);
	
	
}
