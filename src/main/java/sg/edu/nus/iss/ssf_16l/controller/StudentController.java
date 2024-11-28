package sg.edu.nus.iss.ssf_16l.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.ssf_16l.model.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
    @GetMapping("")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("")
    public String postStudentForm(@ModelAttribute Student entity) {
        return "redirect:/students/list";
    }
    

@GetMapping("/list")
public String getMethodName(@RequestParam String param) {
    return "studentList";
}

    
}
