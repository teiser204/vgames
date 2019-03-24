package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Language;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LanguageAPI {

    @GET("languages.json")
    Call<List<Language>> getLanguages();
}
