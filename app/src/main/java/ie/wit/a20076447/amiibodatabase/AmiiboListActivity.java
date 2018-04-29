package ie.wit.a20076447.amiibodatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AmiiboListActivity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue mQueue;
    private String AmiiboData;

    private ArrayList<Amiibo> amiiboList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amiibo_list);


        listView = findViewById(R.id.listView_result);

        ArrayAdapter<Amiibo> arAdapter = new ArrayAdapter<Amiibo>(AmiiboListActivity.this,
                android.R.layout.simple_list_item_1, amiiboList);

        listView.setAdapter(arAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Amiibo obj = (Amiibo) listView.getAdapter().getItem(i);

                String[] amiiboData = new String[12];
                amiiboData[0] = obj.getAmiiboName();
                amiiboData[1] = obj.getAmiiboSeries();
                amiiboData[2] = obj.getGameSeries();
                amiiboData[3] = obj.getHeadID();
                amiiboData[4] = obj.getTailID();
                amiiboData[5] = obj.getImage();
                amiiboData[6] = obj.getReleaseAU();
                amiiboData[7] = obj.getReleaseEU();
                amiiboData[8] = obj.getReleaseJP();
                amiiboData[9] = obj.getReleaseNA();
                amiiboData[10] = obj.getType();
                amiiboData[11] = obj.getCharacter();


                Intent intent = new Intent(AmiiboListActivity.this, InfoListView.class);
                intent.putExtra("AmiiboData", amiiboData);
                startActivity(intent);
            }
        });
        mQueue = Volley.newRequestQueue(this);

        configureSearchButton();

        amiiboList.clear();
        jsonParse();

    }


    private void configureSearchButton() {

        Button switchButton = (Button) findViewById(R.id.buttonSearch);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AmiiboListActivity.this, SearchScreen.class));

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
                                String au = nestObj.optString("au");
                                String eu = nestObj.optString("eu");
                                String jp = nestObj.optString("jp");
                                String na = nestObj.optString("na");

                                if(au == "null") {
                                    au = "Not Released";
                                } if (eu == "null") {
                                    eu = "Not Released";
                                } if (jp == "null") {
                                    jp = "Not Released";
                                } if (na == "null") {
                                    na = "Not Released";
                                }

                                String tailID = amiibos.getString("tail");
                                String type = amiibos.getString("type");

                                Amiibo amiibo = new Amiibo(amiiboName, amiiboSeries, gameSeries, headID, tailID, image, au, eu, jp, na, type, character);
                                amiiboList.add(amiibo);


                                fillListView();

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

        CustomAdapter myCustomAdapter = new CustomAdapter(AmiiboListActivity.this, amiiboList);
        listView.setAdapter(myCustomAdapter);
    }

}
