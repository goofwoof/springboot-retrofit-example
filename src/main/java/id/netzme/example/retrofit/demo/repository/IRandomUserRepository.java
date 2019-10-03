package id.netzme.example.retrofit.demo.repository;

import id.netzme.example.retrofit.demo.model.RandomUser;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IRandomUserRepository {

    @GET("/api/")
    Call<RandomUser> getPerson();
}
