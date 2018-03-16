package ie.wit.a20076447.amiibodatabase;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue mQueue;

    private ArrayList<Amiibo> amiiboList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonParse = findViewById(R.id.button_parse);

        listView = findViewById(R.id.listView_result);

        mQueue = Volley.newRequestQueue(this);

        configureButton();

        amiiboList.clear();
        jsonParse();

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fillListView();
            }
        });


    }

    private void configureButton() {

        Button switchButton = (Button)findViewById(R.id.buttonSearch);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchScreen.class));

            }
        });
    }


    private void jsonParse() {
        String url = "http://www.amiiboapi.com/api/amiibo/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("amiibo");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject amiibos = jsonArray.getJSONObject(i);

                                String amiiboSeries = amiibos.getString("amiiboSeries");
                                String character = amiibos.getString("character");
                                String gameSeries = amiibos.getString("gameSeries");
                                String headID = amiibos.getString("head");
                                String image = amiibos.getString("image");
                                String amiiboName = amiibos.getString("name");

                                JSONObject nestObj = amiibos.getJSONObject("release");
                                String au = nestObj.getString("au");
                                String eu = nestObj.getString("eu");
                                String jp = nestObj.getString("jp");
                                String na = nestObj.getString("na");

                                String tailID = amiibos.getString("tail");
                                String type = amiibos.getString("type");

                                Amiibo amiibo = new Amiibo(amiiboName, amiiboSeries, gameSeries, headID, tailID, image);
                                amiiboList.add(amiibo);





                                //mTextViewResult.append(amiibo.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void fillListView() {

        CustomAdapter myCustomAdapter = new CustomAdapter(MainActivity.this, amiiboList);
        listView.setAdapter(myCustomAdapter);
    }


}
