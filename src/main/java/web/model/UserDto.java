package web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private Set<RoleDto> roleDtos = new HashSet<RoleDto>();

    public UserDto() {
    }

    public UserDto(String name) {
        this.name = name;
    }
    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDto(String name, String email, String password, Set<RoleDto> roleDtos) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleDtos = roleDtos;
    }
    public UserDto(int id, String name, String email, String password, Set<RoleDto> roleDtos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleDtos = roleDtos;
    }
    public UserDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(int id) {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roleDtos;
    }

    public void setRoles(Set<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStringRoles() {
        String result = "";
        for (RoleDto roleDto : roleDtos) {
            result += " " + roleDto.toString();
        }
        return result;
    }

    @Override
    public String toString() {
        return name + " " + email;
    }
}