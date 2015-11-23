package com.coderscampus.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Section
{
  private Long id;
  private String name;
  private Set<Lesson> lessons = new TreeSet<Lesson>();
  private Course course;
  
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
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  
  @OneToMany(fetch=FetchType.EAGER, mappedBy="section", cascade=CascadeType.ALL)
  public Set<Lesson> getLessons()
  {
    return lessons;
  }
  public void setLessons(Set<Lesson> lessons)
  {
    this.lessons = lessons;
  }
  @ManyToOne
  public Course getCourse()
  {
    return course;
  }
  public void setCourse(Course course)
  {
    this.course = course;
  }
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Section other = (Section) obj;
    if (id == null)
    {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
}
