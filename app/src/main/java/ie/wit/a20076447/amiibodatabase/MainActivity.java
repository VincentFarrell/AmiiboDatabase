package ie.wit.a20076447.amiibodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.textView_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }


    private void jsonParse() {
        String url = "https://api.myjson.com/bins/14yyr1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("amiibo");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject amiibo = jsonArray.getJSONObject(i);

                                String amiiboSeries = amiibo.getString("amiiboSeries");
                                String character = amiibo.getString("character");
                                String gameSeries = amiibo.getString("gameSeries");
                                String headID = amiibo.getString("head");
                                //String image = amiibo.getString("image");
                                String amiiboName = amiibo.getString("name");
                                //String release = amiibo.getString("release");
                                //String au = amiibo.getString("au");
                                //String eu = amiibo.getString("eu");
                                //String jp = amiibo.getString("jp");
                                //String na = amiibo.getString("na");
                                String tailID = amiibo.getString("tail");
                                String type = amiibo.getString("type");

                                mTextViewResult.append(character + " - " + amiiboName + ", "
                                        + amiiboSeries + ", " + gameSeries + ", "
                                        + type + ", " + headID + "/" + tailID + "\n \n");
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
}
