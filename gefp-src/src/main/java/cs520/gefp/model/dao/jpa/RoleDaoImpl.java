package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cs520.gefp.model.Role;
import cs520.gefp.model.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Role getRole(Long id) {
		return entityManager.find( Role.class, id );
	}

	@Override
	public List<Role> getRoles() {
		return entityManager.createQuery( "from Role order by id" , Role.class ).getResultList();
	}

	@Override
	public Role getRole(String role) {
		return entityManager.createQuery( "from Role order where role= :r" , Role.class ).setParameter("r", role).getSingleResult();
	}
	
	

}
