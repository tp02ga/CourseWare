package com.coderscampus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Lesson
{
  private Long id;
  private Integer number;
  private String title;
  private Section section;
  
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
  public Integer getNumber()
  {
    return number;
  }
  public void setNumber(Integer number)
  {
    this.number = number;
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  @ManyToOne
  public Section getSection()
  {
    return section;
  }
  public void setSection(Section section)
  {
    this.section = section;
  }
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((number == null) ? 0 : number.hashCode());
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
    Lesson other = (Lesson) obj;
    if (number == null)
    {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    return true;
  }
  
}
