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

    @GET
    Call<Game> getGameDetails(@Url String url);

    @GET
    Call<List<Game>> getTopGames(@Url String url);
}
