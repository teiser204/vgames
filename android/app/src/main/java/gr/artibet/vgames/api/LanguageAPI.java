package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Language;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface LanguageAPI {

    @GET
    Call<List<Language>> getLanguages(@Url String url);
}
