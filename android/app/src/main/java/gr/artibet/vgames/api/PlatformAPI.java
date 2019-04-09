package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Platform;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PlatformAPI {

    @GET
    Call<List<Platform>> getPlatforms(@Url String url);
}
