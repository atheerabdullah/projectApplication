package com.example.fproject.Model;

import com.example.fproject.Service.MyUserDetailService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String username;


    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String password;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null check ( role='customer' or role='admin')")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<MyOrder> orders;

    public boolean isAdmin() {
        return role.contains("ADMIN");
    }

}
