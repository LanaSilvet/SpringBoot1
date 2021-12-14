package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.MyUserDetails;
import web.model.Role;
import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @PersistenceContext
    private EntityManager em;

//    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public void addUser(User user) {
//        Set<Role> roles = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            Role roleByRole = roleRepository.findRoleByRole(role.getRole());
//            if (roleByRole == null) {
//                roles.add(role);
//            } else {
//                roles.add(roleByRole);
//            }
//        }
//        user.setRoles(roles);
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
//        Set<Role> roles = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            Role roleByRole = roleRepository.findRoleByRole(role.getRole());
//            if (roleByRole == null) {
//                roles.add(role);
//            } else {
//                roles.add(roleByRole);
//            }
//        }
//        user.setRoles(roles);
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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = getUserByName(name);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }

        Set<Role> roles = user.getRoles();

        return new MyUserDetails(user, roles);
    }
}
