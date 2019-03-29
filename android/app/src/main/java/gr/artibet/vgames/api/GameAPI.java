package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GameAPI {

    @GET("games.json")
    Call<List<Game>> getGames();
}
