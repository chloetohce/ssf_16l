package sg.edu.nus.iss.ssf_16l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ssf_16l.model.Student;

@Service
public class StudentRestService {

    RestTemplate restTemplate = new RestTemplate();

    private static final String STUDENT_URL = "http://localhost:8080/api/students"; //call the endpoint

    public List<Student> findAll() {
        ResponseEntity<String> data = restTemplate.getForEntity(STUDENT_URL, String.class); //returns a string
        // data contains a header and body information. Need to remove header and pass only the payload to the reader.

        String payload = data.getBody();
        
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray jArray = reader.readArray();

        List<Student> students = new ArrayList<>();


        //Loop through JSONarray. Each is a jsonobject

        for (int i = 0; i< jArray.size(); i++) {
            JsonObject jObject = jArray.getJsonObject(i);
            Student s = new Student(
                jObject.getInt("id"),
                jObject.getString("name"),
                jObject.getString("email"),
                jObject.getString("phoneNo")
            );
            students.add(s);
        }

        return students;
    }

    public String createStudent(Student s) {
        // Convert to JSON String using Json-P
        JsonObjectBuilder jObjectBuilder = Json.createObjectBuilder();
        jObjectBuilder.add("id", s.getId())
                .add("name", s.getName())
                .add("email", s.getEmail())
                .add("phoneNo", s.getPhoneNo());
        String requestPayload = jObjectBuilder.build().toString();


        RequestEntity<String> requestEntity = RequestEntity.post(STUDENT_URL + "/create").body(requestPayload);
        

        // Call service using RestTemplate
        // Write using .exchange()
        // Calls StudentRestController::create endpoint
        ResponseEntity<String> responseResult = restTemplate.exchange(requestEntity, String.class);

        return responseResult.getBody();
    }
}
