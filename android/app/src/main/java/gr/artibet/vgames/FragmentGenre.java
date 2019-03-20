package gr.artibet.vgames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.artibet.vgames.api.GenreAPI;
import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGenre extends Fragment {

    private TextView textView;

    public FragmentGenre() {
        // Required empty public constructor
    }

    public static FragmentGenre newInstance() {
        FragmentGenre fragment = new FragmentGenre();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        textView = view.findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GenreAPI genreAPI = retrofit.create(GenreAPI.class);

        Call<List<Genre>> call = genreAPI.getGenres();

        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }

                List<Genre> genres = response.body();

                // TODO: Add to RecycleView

                for (Genre genre : genres) {
                    String content = "";
                    content += "id: " + genre.getId() + "\n";
                    content += "Description: " + genre.getDesc() + "\n\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

        return view;
    }




}
