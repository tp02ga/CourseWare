package com.coderscampus.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.coderscampus.domain.User;

public class CustomUserDetails extends User implements UserDetails
{

  // authorities have a ManyToOne relationship with users
  // therefore, a User has a OneToMany relationship with authorities
  
  private static final long serialVersionUID = -2052691227087262541L;

  public CustomUserDetails () {}
  
  public CustomUserDetails(User user)
  {
    super(user);
  }

  @Override
  public Set<Authorities> getAuthorities()
  {
    return super.getAuthorities();
  }
  
  @Override
  public String getUsername()
  {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }

}
