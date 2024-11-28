package sg.edu.nus.iss.ssf_16l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.ssf_16l.model.Country;
import sg.edu.nus.iss.ssf_16l.service.CountryRestService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path="/api/countries")
public class CountryRestController {
    
    @Autowired
    CountryRestService countryRestService;

    
    @GetMapping("")
    // Always return a response entity for an API call
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryRestService.getCountries();

        return ResponseEntity.ok().body(countries);
    }
    

}
