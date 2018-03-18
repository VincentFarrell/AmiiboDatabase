package ie.wit.a20076447.amiibodatabase;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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


public class SearchScreen extends AppCompatActivity {

    private RequestQueue mQueue;
    private ListView listView;
    private String searchInput ="";
    private int choice;
    private CharSequence[] values = {" Search By Name "," Search By Amiibo ID "," Search By Game Series "};
    private String url;

    private ArrayList<Amiibo> amiiboSearchList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchscreen);

        configureBackButton();

        listView = findViewById(R.id.listView_result);


        final AlertDialog.Builder builder = new AlertDialog.Builder(SearchScreen.this);

        builder.setTitle("Search Amiibos");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        choice = item;
                        break;
                    case 1:
                        choice = item;
                        break;
                    case 2:
                        choice = item;
                        break;
                }
                builder.setMessage("Please Choose Type");
            }
        });


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(input);

        builder.setCancelable(true);
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                searchInput = input.getText().toString();

                amiiboSearchList.clear();

                jsonParseSearch();

            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        mQueue = Volley.newRequestQueue(this);

    }

    private void configureBackButton() {
        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void jsonParseSearch() {
        if(choice == 0){
            String url = "http://www.amiiboapi.com/api/amiibo/?character=" + searchInput;
            this.url = url;
        }
        else
        if(choice == 1){
            String url = "http://www.amiiboapi.com/api/amiibo/?head=" + searchInput;
            this.url = url;
        }
        else
        if(choice == 2){
            String url = "http://www.amiiboapi.com/api/amiibo/?gameseries=" + searchInput;
            this.url = url;
        }
        else
        {
            Toast.makeText(SearchScreen.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }
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
                                amiiboSearchList.add(amiibo);


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

        CustomAdapter myCustomAdapter = new CustomAdapter(SearchScreen.this, amiiboSearchList);
        listView.setAdapter(myCustomAdapter);
    }

}
