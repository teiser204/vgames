package gr.artibet.vgames.api;

import java.util.List;

import gr.artibet.vgames.models.Feature;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FeatureAPI {

    @GET("features.json")
    Call<List<Feature>> getFeatures();
}

