package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Company;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CompanyAPI {

    @GET
    Call<List<Company>> getCompanies(@Url String url);
}
