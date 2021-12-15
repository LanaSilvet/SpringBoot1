package web.dao;

import org.springframework.stereotype.Service;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT user FROM User user";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        String jpql = "SELECT user FROM User user WHERE user.name = :name";
        return em.createQuery(jpql, User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }
}
