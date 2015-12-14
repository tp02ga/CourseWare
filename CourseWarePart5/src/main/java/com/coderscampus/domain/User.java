package com.coderscampus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coderscampus.security.Authorities;

@Entity
@Table(name="users")
public class User
{
  private Long id;
  private String email;
  private String password;
  private Set<Authorities> authorities = new HashSet<>();
  private Set<Course> courses = new HashSet<>();
  
  public User ()
  {
    
  }
  
  public User(User user)
  {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.authorities = user.getAuthorities();
    this.courses = user.getCourses();
  }
  
  @Id
  @GeneratedValue
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }
  
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user", orphanRemoval=true)
  public Set<Course> getCourses()
  {
    return courses;
  }

  public void setCourses(Set<Course> courses)
  {
    this.courses = courses;
  }

  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="user")
  public Set<Authorities> getAuthorities()
  {
    return authorities;
  }
  public void setAuthorities(Set<Authorities> authorities)
  {
    this.authorities = authorities;
  }
  
  
}
