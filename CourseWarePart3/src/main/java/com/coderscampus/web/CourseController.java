package com.coderscampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.Course;
import com.coderscampus.domain.Lesson;
import com.coderscampus.domain.Section;
import com.coderscampus.repository.CourseRepository;
import com.coderscampus.repository.SectionRepository;

@Controller
public class CourseController
{
  private CourseRepository courseRepo;
  private SectionRepository sectionRepo;
  
  @RequestMapping("/")
  public String rootPath ()
  {
    return "redirect:/courses";
  }
  
  @RequestMapping(value="courses", method=RequestMethod.GET)
  public String courses (ModelMap model)
  {
    
    Page<Course> courses = courseRepo.findAll(new PageRequest(0, 3));
    model.put("courses", courses);
    Course course = new Course();
    model.put("course", course);
    
    return "courses";
  }
  
  @RequestMapping(value="courses", method=RequestMethod.POST)
  public String coursesPost (@ModelAttribute Course course, ModelMap model)
  {
    Course savedCourse = courseRepo.save(course);
    return "redirect:/editCourse/" + savedCourse.getId();
  }
  
  @RequestMapping(value="editCourse/{courseId}", method=RequestMethod.GET)
  public String editCourseGet (@PathVariable Long courseId, ModelMap model)
  {
    Course course = courseRepo.findOne(courseId);
    if (course == null)
      return "redirect:/";
    model.put("course", course);
    return "editCourse";
  }

  @RequestMapping(value="editCourse/createSection", method=RequestMethod.POST)
  public @ResponseBody Course createSection (@RequestParam Long courseId, @RequestParam String sectionName)
  {
    Course course = courseRepo.findOne(courseId);
    Section section = new Section();
    section.setCourse(course);
    section.setName(sectionName);
    course.getSections().add(section);
    courseRepo.save(course);
    return course;
  }
  
  @RequestMapping(value="editCourse/createLesson", method=RequestMethod.POST)
  public @ResponseBody Course createLesson (@RequestParam Long courseId, 
      @RequestParam Long sectionId,
      @RequestParam String lessonTitle,
      @RequestParam Integer lessonNumber)
  {
    Course course = courseRepo.findOne(courseId);
    for (Section section : course.getSections())
    {
      if (section.getId().equals(sectionId))
      {
        Lesson lesson = new Lesson();
        lesson.setNumber(lessonNumber);
        lesson.setTitle(lessonTitle);
        lesson.setSection(section);
        section.getLessons().add(lesson);
        sectionRepo.save(section);
        break;
      }
    }
    return course;
  }
  
  @Autowired
  public void setCourseRepo(CourseRepository courseRepo)
  {
    this.courseRepo = courseRepo;
  }
  @Autowired
  public void setSectionRepo(SectionRepository sectionRepo)
  {
    this.sectionRepo = sectionRepo;
  }
  
}
