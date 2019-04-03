package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GameAPI {

    @GET
    Call<List<Game>> getGames(@Url String query);

    @GET("games/{id}")
    Call<Game> getGame(@Path("id") int id);

    @GET("games/top.json")
    Call<List<Game>> getTopGames();
}
