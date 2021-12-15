package web.dao;

import org.springframework.stereotype.Service;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles(Role role) {
        return em.createQuery("from Role where role = :role")
                .setParameter("role", role.getRole())
                .getResultList();
    }
}
