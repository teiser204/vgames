package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Feature;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FeatureAPI {

    @GET
    Call<List<Feature>> getFeatures(@Url String url);
}

