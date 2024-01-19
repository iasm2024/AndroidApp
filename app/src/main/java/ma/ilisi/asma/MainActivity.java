package ma.ilisi.asma;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ma.ilisi.asma.model.Item;
import ma.ilisi.asma.service.ItemAdapter;
import ma.ilisi.asma.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private final List<Item> itemList = new ArrayList<>();
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter for the RecyclerView
        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
        // Execute API call
        executeAPICall();
    }

    private void executeAPICall() {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=2")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzcxMjNhYzYxNGRmZmUyMWE4OWMxMTE2ZTFkODlmNyIsInN1YiI6IjY1OGRkNTRhMjBlNmE1MzUzYjhiNDdkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GNPo2UECX0U4rezSfIhH8ZTB3aLXDUMRUXyBeczx6uc")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull  Call call, @NonNull  Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                parseAndDisplayItems(responseData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull  Call call,@NonNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void parseAndDisplayItems(String responseData) throws JSONException {
        JSONObject responseJson = new JSONObject(responseData);
        JSONArray resultsArray = responseJson.getJSONArray("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject itemObject = resultsArray.getJSONObject(i);

            // Extract data from JSON object
            String name = itemObject.getString("name");
            String overview = itemObject.getString("overview");
            String firstAirDate = itemObject.getString("first_air_date");
            double voteAverage = itemObject.getDouble("vote_average");
            String imageUrl = "https://image.tmdb.org/t/p/w500" + itemObject.getString("poster_path");

            Item item = new Item(name, overview, firstAirDate, voteAverage,imageUrl);

            itemList.add(item);
        }

        itemAdapter.notifyDataSetChanged();
    }

}
