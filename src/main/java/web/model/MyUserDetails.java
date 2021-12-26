package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class MyUserDetails implements UserDetails {

    private UserDto userDto;
    private Set<RoleDto> roleDtos;

    public MyUserDetails(UserDto userDto, Set<RoleDto> roleDtos) {
        this.userDto = userDto;
        this.roleDtos = roleDtos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleDtos;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
