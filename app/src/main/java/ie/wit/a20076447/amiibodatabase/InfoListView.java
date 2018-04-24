package ie.wit.a20076447.amiibodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list_view);

        Bundle bundle = getIntent().getExtras();

        String[] amiiboData = (String[]) bundle.get("AmiiboData");


        TextView name = (TextView) findViewById(R.id.info_amiiboName);
        TextView amiiboSeries = (TextView) findViewById(R.id.info_amiiboSeries);
        TextView gameSeries = (TextView) findViewById(R.id.info_gameSeries);
        TextView headID = (TextView) findViewById(R.id.info_headID);
        TextView tailID = (TextView) findViewById(R.id.info_tailID);
        ImageView image = (ImageView) findViewById(R.id.info_image);
        TextView releaseAu = (TextView) findViewById(R.id.info_auRelease);
        TextView releaseEu = (TextView) findViewById(R.id.info_euRelease);
        TextView releaseJp = (TextView) findViewById(R.id.info_jpRelease);
        TextView releaseNa = (TextView) findViewById(R.id.info_naRelease);
        TextView type = (TextView) findViewById(R.id.info_type);
        TextView character = (TextView) findViewById(R.id.info_character);


        name.setText(amiiboData[0]);
        amiiboSeries.setText(amiiboData[1]);
        gameSeries.setText(amiiboData[2]);
        headID.setText(amiiboData[3]);
        tailID.setText(amiiboData[4]);
        releaseAu.setText(amiiboData[6]);
        releaseEu.setText(amiiboData[7]);
        releaseJp.setText(amiiboData[8]);
        releaseNa.setText(amiiboData[9]);
        type.setText(amiiboData[10]);
        character.setText(amiiboData[11]);
        image.setTag(amiiboData[5]);
        new DownloadImagesTask().execute(image);


    }

}