package sg.edu.nus.iss.ssf_16l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.ssf_16l.model.Student;
import sg.edu.nus.iss.ssf_16l.service.StudentService;



@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok().body(students);
    }
    
    
    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody Student entity) {
        studentService.add(entity);

        // return new ResponseEntity<>("true", HttpStatus.OK);
        return ResponseEntity.ok("true");
    }
    
}
