package ie.wit.a20076447.amiibodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private ListView listView1;

    ArrayList<HashMap<String, String>> amiiboList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextViewResult = findViewById(R.id.textView_result);
        Button buttonParse = findViewById(R.id.button_parse);

        amiiboList = new ArrayList<>();

        listView1 = (ListView) findViewById(R.id.listView_result);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }


    private void jsonParse() {
        String url = "https://api.myjson.com/bins/7ax9h";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("amiibos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject amiibos = jsonArray.getJSONObject(i);


                                //JSON Objects
                                String amiiboSeries = amiibos.getString("amiiboSeries");
                                String character = amiibos.getString("character");
                                String gameSeries = amiibos.getString("gameSeries");
                                String headID = amiibos.getString("head");
                                String image = amiibos.getString("image");
                                String amiiboName = amiibos.getString("name");

                                //Nested JSON Object
                                JSONObject nestObj = amiibos.getJSONObject("release");
                                String au = nestObj.getString("au");
                                String eu = nestObj.getString("eu");
                                String jp = nestObj.getString("jp");
                                String na = nestObj.getString("na");

                                //JSON Objects
                                String tailID = amiibos.getString("tail");
                                String type = amiibos.getString("type");

                                HashMap<String, String> amiibo = new HashMap<>();

                                amiibo.put("AmiiboName", amiiboName);
                                amiibo.put("AmiiboSeries", amiiboSeries);
                                amiibo.put("GameSeries", gameSeries);
                                amiibo.put("HeadID", headID);
                                amiibo.put("TailID", tailID);

                                amiiboList.add(amiibo);


                                //mTextViewResult.append(amiiboName + " - " + amiiboSeries + " (" + gameSeries + ") " + headID + tailID);

                                ListAdapter adapter = new SimpleAdapter(
                                        MainActivity.this, amiiboList,
                                        R.layout.list_item_rows, new String[]{"AmiiboName", "AmiiboSeries",
                                        "GameSeries", "HeadID", "TailID"}, new int[]{R.id.list_AmiiboName,
                                        R.id.list_amiiboSeries, R.id.list_gameSeries, R.id.list_headID, R.id.list_tailID});

                                listView1.setAdapter(adapter);


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
