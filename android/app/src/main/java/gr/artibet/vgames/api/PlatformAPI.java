package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Platform;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlatformAPI {

    @GET("platforms.json")
    Call<List<Platform>> getPlatforms();
}
