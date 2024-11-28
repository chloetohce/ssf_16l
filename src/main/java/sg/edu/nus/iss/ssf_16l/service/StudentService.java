package sg.edu.nus.iss.ssf_16l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf_16l.model.Student;
import sg.edu.nus.iss.ssf_16l.repository.ListRepository;
import sg.edu.nus.iss.ssf_16l.utility.Constant;

@Service
public class StudentService {
    @Autowired
    private ListRepository listRepository;

    public void add(Student s) {
        listRepository.rightPush(Constant.REDIS_STUDENTS, s.toString());
    }

    public List<Student> findAll() {
        List<String> studentsStr = listRepository.getList(Constant.REDIS_STUDENTS);
        List<Student> students = new ArrayList<>();
        // JsonArrayBuilder studentsBuilder = Json.createArrayBuilder();

        // TODO: Use JSON-p to cast to list of students
        for (String raw : studentsStr) {
            String[] dataArr = raw.split(",");
            // JsonObject student = Json.createObjectBuilder()
            //     .add("id", Integer.parseInt(dataArr[0]))
            //     .add("name", dataArr[1])
            //     .add("email", dataArr[2])
            //     .add("phoneNo", dataArr[3])
            //     .build();

            // Place JsonObject into a JsonArray
            // studentsBuilder.add(student);

            Student s = new Student(Integer.parseInt(dataArr[0]), dataArr[1], dataArr[2], dataArr[3]);
            students.add(s);
        }

        return students;
    }
}
