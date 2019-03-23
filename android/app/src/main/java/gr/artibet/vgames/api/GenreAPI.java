package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GenreAPI {

    @GET("genres.json")
    Call<List<Genre>> getGenres();
}
