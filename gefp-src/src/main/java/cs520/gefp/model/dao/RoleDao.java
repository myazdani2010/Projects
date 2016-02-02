package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Role;

public interface RoleDao {
	Role getRole( Long id );
	List<Role> getRoles();
	Role getRole( String role );
}
