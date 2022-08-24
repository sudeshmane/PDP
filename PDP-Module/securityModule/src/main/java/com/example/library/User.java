package com.example.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
 
//@Entity
//@Table(name = "users")
public class User {
 
  //  @Id
   // @Column(name = "user_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String username ="abcd";
   //pqr private String password = "$2a$10$G6pA4PpyuOBi9RXCY7q79eD0qIvFjfDNZoqfv0T0m8O9ubmfRhMM6";
 //  private String password = "$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG";
   String password = "password";
    private boolean enabled = true;
     
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
	   
	public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
	      return grantedAuthoritiesList;
	   }
	   public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
	      this.grantedAuthoritiesList = grantedAuthoritiesList;
	   }
	// @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   // @JoinTable(
     //       name = "users_roles",
       //     joinColumns = @JoinColumn(name = "user_id"),
         //   inverseJoinColumns = @JoinColumn(name = "role_id")
           // )
    private Set<Role> roles = new HashSet<>();
 
    public Long getId() {
        return id;
    }
 
}
