package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Company;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyAPI {

    @GET("companies.json")
    Call<List<Company>> getCompanies();
}
