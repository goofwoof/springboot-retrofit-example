package id.netzme.example.retrofit.demo.config;

import id.netzme.example.retrofit.demo.repository.IRandomUserRepository;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public IRandomUserRepository randomUserService(Retrofit retrofit) {
        return retrofit.create(IRandomUserRepository.class);
    }

}
