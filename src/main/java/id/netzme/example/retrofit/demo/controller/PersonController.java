package id.netzme.example.retrofit.demo.controller;

import id.netzme.example.retrofit.demo.dto.PersonDTO;
import id.netzme.example.retrofit.demo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping
    public PersonDTO getPerson() {
        return personService.getPerson();
    }
}
