package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.MyUserDetails;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public void addUser(User user) {
        checkRoles(user);
        userDAO.addUser(user);
    }

    private void checkRoles(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            List<Role> roleByRole = roleDAO.getAllRoles(role);
            if (roleByRole.isEmpty()) {
                roles.add(role);
            } else {
                roles.add(roleByRole.get(0));
            }
        }
        user.setRoles(roles);
    }

    @Override
    public User updateUser(User user) {
        checkRoles(user);
        return userDAO.updateUser(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
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
