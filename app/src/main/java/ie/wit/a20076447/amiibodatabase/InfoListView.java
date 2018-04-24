package ie.wit.a20076447.amiibodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoListView extends AppCompatActivity {


    private ArrayList<Amiibo> amiiboList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list_view);

//        Bundle bundle = getIntent().getExtras();
//
//        String myVal = bundle.getString("AmiiboID");
//
//
//        TextView textView = (TextView) findViewById(R.id.textViewer);
//
//        textView.setText(myVal);

        listView = findViewById(R.id.listView12);

        Amiibo amiiboInfo = new Amiibo("Mario", "Super Mario", "Super Mario Bros.", "00001000", "00002000", "https://raw.githubusercontent.com/N3evin/AmiiboAPI/master/images/icon_00080000-00030002.png", "2014-11-29", "2014-11-28", "2014-12-06", "2014-11-21", "Figure", "char");

        amiiboList.clear();

        amiiboList.add(amiiboInfo);

        fillListView();

    }
    private void fillListView() {

        CustomAdapter myCustomAdapter = new CustomAdapter(InfoListView.this, amiiboList);
        listView.setAdapter(myCustomAdapter);
    }

}