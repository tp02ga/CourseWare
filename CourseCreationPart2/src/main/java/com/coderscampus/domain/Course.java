package com.coderscampus.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Course
{
  private Long id;
  private String name;
  private Set<Section> sections = new TreeSet<>();
  
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

  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="course")
  public Set<Section> getSections()
  {
    return sections;
  }

  public void setSections(Set<Section> sections)
  {
    this.sections = sections;
  }

}
