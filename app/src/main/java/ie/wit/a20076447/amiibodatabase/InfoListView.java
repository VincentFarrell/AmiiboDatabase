package ie.wit.a20076447.amiibodatabase;

import android.graphics.drawable.Drawable;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoListView extends AppCompatActivity {

    private String[] amiiboData = new String[11];
    private DatabaseReference mDatabase;
    private Amiibo amiiboObj = new Amiibo();

    Button addAmiiboButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list_view);

        addAmiiboButton = (Button)findViewById(R.id.addAmiibo_Button);

        Bundle bundle = getIntent().getExtras();

        amiiboData = (String[]) bundle.get("AmiiboData");


        amiiboObj = new Amiibo(amiiboData[0],amiiboData[1],amiiboData[2],amiiboData[3],amiiboData[4],amiiboData[5],
                amiiboData[6],amiiboData[7],amiiboData[8],amiiboData[9],amiiboData[10],amiiboData[11]);


        final String amiiboID = (amiiboData[3]+"-"+amiiboData[4]);

        addAmiiboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickAddAmiibo(amiiboID);

            }
        });

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


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Saved Amiibos").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (amiiboID == null) {
                            Toast.makeText(InfoListView.this,"Error could not get AmiiboID", Toast.LENGTH_SHORT).show();
                        }
                        else if (dataSnapshot.hasChild(amiiboID)) {
                            addAmiiboButton.setBackgroundResource(R.drawable.added_icon);
                        }
                        else {
                            addAmiiboButton.setBackgroundResource(R.drawable.add_icon);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }


    public void onClickAddAmiibo(final String amiiboID) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Saved Amiibos").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // [START_EXCLUDE]
                        if (amiiboID == null) {
                            Toast.makeText(InfoListView.this,"Error could not fetch Amiibo", Toast.LENGTH_SHORT).show();
                        }
                        else if (dataSnapshot.hasChild(amiiboID)) {
                            Toast.makeText(InfoListView.this,"This Amiibo is already in MyList", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            addAmiibo(amiiboID);
                        }

//                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
//                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
//                        // [START_EXCLUDE]
//                        setEditingEnabled(true);
//                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }


    public void addAmiibo(String amiiboID) {

        mDatabase = FirebaseDatabase.getInstance().getReference("Saved Amiibos/"+ amiiboID);
        mDatabase.setValue(amiiboObj);

        Toast.makeText(this, "Amiibo Added to MyList", Toast.LENGTH_SHORT).show();

        addAmiiboButton.setBackgroundResource(R.drawable.added_icon);


    }

}