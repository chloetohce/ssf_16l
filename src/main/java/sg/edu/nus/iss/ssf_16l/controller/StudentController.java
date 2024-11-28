package sg.edu.nus.iss.ssf_16l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.ssf_16l.model.Student;
import sg.edu.nus.iss.ssf_16l.service.StudentRestService;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRestService studentRestService;

    @GetMapping("")
    public String studentForm(Model model) {
        Student s = new Student();
        model.addAttribute("student", s);
        
        return "studentForm";
    }

    @PostMapping("")
    public String postStudentForm(@ModelAttribute("student") Student entity) {
        
        studentRestService.createStudent(entity);

        return "redirect:/students/list";
    }
    

@GetMapping("/list")
public String getMethodName(Model model) {
    List<Student> students = new ArrayList<>();
    model.addAttribute("students", students);
    return "studentList";
}

    
}
