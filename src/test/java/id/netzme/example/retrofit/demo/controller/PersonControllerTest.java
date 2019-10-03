package id.netzme.example.retrofit.demo.controller;

import id.netzme.example.retrofit.demo.dto.PersonDTO;
import id.netzme.example.retrofit.demo.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPersonService personService;

    @Test
    public void givenPerson_whengetPerson_thenReturnJsonPersonDTO() throws Exception {

        //given
        PersonDTO person = new PersonDTO();
        person.setGender("male");
        person.setFullname("Mr Axel Clement");
        person.setAddress("5271 Rue du 8 Mai 1945 Versailles");
        person.setPicture("https://randomuser.me/api/portraits/men/52.jpg");

        //when
        when(personService.getPerson()).thenReturn(person);

        //then
        mockMvc.perform(get("/person")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gender", is(person.getGender())))
                .andExpect(jsonPath("$.fullname", is(person.getFullname())))
                .andExpect(jsonPath("$.address", is(person.getAddress())))
                .andExpect(jsonPath("$.picture", is(person.getPicture())));
    }
}
