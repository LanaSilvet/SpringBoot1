package web.model;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {

    private int id;
    private String role;

    public RoleDto() {
    }

    public RoleDto(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleDto(int id) {
        this.id = id;
    }

    public RoleDto(String role) {
        this.role = role;
    }

    public RoleDto(RoleDto roleDto) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
