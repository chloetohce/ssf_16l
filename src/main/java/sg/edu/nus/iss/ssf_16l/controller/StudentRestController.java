package sg.edu.nus.iss.ssf_16l.controller;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ssf_16l.model.Student;
import sg.edu.nus.iss.ssf_16l.service.StudentService;

@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<String> findAll() {
        List<Student> students = studentService.findAll();

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Student s : students) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("id", s.getId())
                    .add("name", s.getName())
                    .add("email", s.getEmail())
                    .add("phoneNo", s.getPhoneNo())
                    .build();

            jsonArrayBuilder.add(obj);
        }
        JsonArray arr = jsonArrayBuilder.build();

        return ResponseEntity.ok().body(arr.toString());
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody String entity) {

        // Convert Json String to Student object
        JsonReader reader = Json.createReader(new StringReader(entity));
        JsonObject jObject = reader.readObject();

        Student s = new Student(
                jObject.getInt("id"),
                jObject.getString("name"),
                jObject.getString("email"),
                jObject.getString("phoneNo")
        );

        studentService.add(s);

        // return new ResponseEntity<>("true", HttpStatus.OK);
        return ResponseEntity.ok("true");
    }

}
