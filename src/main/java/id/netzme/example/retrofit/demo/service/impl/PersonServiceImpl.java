package id.netzme.example.retrofit.demo.service.impl;

import id.netzme.example.retrofit.demo.dto.PersonDTO;
import id.netzme.example.retrofit.demo.model.RandomUser;
import id.netzme.example.retrofit.demo.repository.IRandomUserRepository;
import id.netzme.example.retrofit.demo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IRandomUserRepository userRepository;

    @Override
    public PersonDTO getPerson() {
        try {
            Call<RandomUser> callSync = userRepository.getPerson();
            Response<RandomUser> response = callSync.execute();
            RandomUser randomUser = response.body();

            PersonDTO person = null;
            if (randomUser.getResults().size() > 0){
                RandomUser.Result result = randomUser.getResults().get(0);
                String fullname = String.join(" ", result.getName().get("title"), result.getName().get("first"),
                        result.getName().get("last"));
                String address = String.join(" ",result.getLocation().getStreet().get("number"),
                        result.getLocation().getStreet().get("name"), result.getLocation().getCity());

                person = new PersonDTO();
                person.setGender(result.getGender());
                person.setFullname(fullname);
                person.setAddress(address);
                person.setPicture(result.getPicture().get("large"));
            }

            return person;
        } catch (Exception e) {
            return null;
        }
    }
}
