package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GenreAPI {

    @GET
    Call<List<Genre>> getGenres(@Url String url);
}
