package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import web.model.MyUserDetails;
import web.model.RoleDto;
import web.model.UserDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ClientServiceImpl implements UserDetailsService {

    static final String URL_ADD = "http://localhost:8081/admin/adduser";
    static final String URL_USERS = "http://localhost:8081/admin/users";
    static final String URL_EDIT = "http://localhost:8081/admin/edit";
    static final String URL_UPDATE = "http://localhost:8081/admin/update";
    static final String URL_DELETE = "http://localhost:8081/admin/delete";

    static final String URL_NAME = "http://localhost:8081/admin/name";

    @Autowired
    private RestTemplate restTemplate;

    public void addUser(UserDto user) {
        restTemplate.postForObject(URL_ADD, user, UserDto.class);
    }

    public UserDto editUser(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_EDIT)
                .queryParam("id", id);
        HttpEntity<UserDto> entity = new HttpEntity<>(new UserDto(id));
        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserDto.class).getBody();
    }

    public void updateUser(UserDto user) {
        restTemplate.put(URL_UPDATE, user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        HttpEntity<UserDto> entity = new HttpEntity<>(new UserDto());

        ParameterizedTypeReference<List<UserDto>> typeRef = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<UserDto>> responseEntity = restTemplate.exchange(URL_USERS, HttpMethod.GET, entity, typeRef);
        List<UserDto> myModelClasses = responseEntity.getBody();
        return myModelClasses;
    }

    public UserDto getUserByName(String name) {
        HttpEntity<UserDto> entity = new HttpEntity<>(new UserDto(name));
        return restTemplate.exchange(URL_NAME, HttpMethod.POST, entity, UserDto.class).getBody();
    }

    public void deleteUser(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL_DELETE)
                .queryParam("id", id);
        restTemplate.delete(builder.toUriString());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDto userDto = getUserByName(name);

        if (userDto == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }

        Set<RoleDto> roleDtos = userDto.getRoles();

        return new MyUserDetails(userDto, roleDtos);
    }
}
