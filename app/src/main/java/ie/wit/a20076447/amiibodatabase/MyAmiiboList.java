package ie.wit.a20076447.amiibodatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAmiiboList extends AppCompatActivity {

    private ArrayList<Amiibo> myAmiiboList = new ArrayList<>();
    private ListView listView;
    private DatabaseReference mDatabaseReference;
    private Button createAmiibo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_amiibo_list);

        myAmiiboList.clear();

        listView = findViewById(R.id.listView_list);
        createAmiibo = findViewById(R.id.buttonCreate);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("Saved Amiibos").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GenericTypeIndicator<HashMap<String, Amiibo>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Amiibo>>() {};
                        Map<String, Amiibo> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                        myAmiiboList = new ArrayList(objectHashMap.values());


                        fillMyListView(myAmiiboList);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("loadAmiibo:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        Toast.makeText(MyAmiiboList.this, "Failed to load Amiibos.",
                                Toast.LENGTH_SHORT).show();
                        // [END_EXCLUDE]
                    }
                });


        ArrayAdapter<Amiibo> arrayAdapter = new ArrayAdapter<>(MyAmiiboList.this,
                android.R.layout.simple_list_item_1, myAmiiboList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Amiibo getObj = (Amiibo) listView.getAdapter().getItem(i);

                String[] myAmiiboData = new String[12];
                myAmiiboData[0] = getObj.getAmiiboName();
                myAmiiboData[1] = getObj.getAmiiboSeries();
                myAmiiboData[2] = getObj.getGameSeries();
                myAmiiboData[3] = getObj.getHeadID();
                myAmiiboData[4] = getObj.getTailID();
                myAmiiboData[5] = getObj.getImage();
                myAmiiboData[6] = getObj.getReleaseAU();
                myAmiiboData[7] = getObj.getReleaseEU();
                myAmiiboData[8] = getObj.getReleaseJP();
                myAmiiboData[9] = getObj.getReleaseNA();
                myAmiiboData[10] = getObj.getType();
                myAmiiboData[11] = getObj.getCharacter();

                Intent intent = new Intent(MyAmiiboList.this, InfoListView.class);
                intent.putExtra("AmiiboData", myAmiiboData);
                startActivity(intent);
            }
        });

        createAmiibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAmiibos();
            }
        });

    }

    private void createAmiibos() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MyAmiiboList.this);
        builder.setTitle("Create New Amiibo");



        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);


        final EditText name = new EditText(this);
        name.setHint("Amiibo Name");
        layout.addView(name);

        final EditText aSeries = new EditText(this);
        aSeries.setHint("Amiibo Series");
        layout.addView(aSeries);

        final EditText chara = new EditText(this);
        chara.setHint("Character");
        layout.addView(chara);

        final EditText gSeries = new EditText(this);
        gSeries.setHint("Game Series");
        layout.addView(gSeries);

        final EditText hID = new EditText(this);
        hID.setHint("Head ID");
        layout.addView(hID);

        final EditText tID = new EditText(this);
        tID.setHint("Tail ID");
        layout.addView(tID);

        final EditText img = new EditText(this);
        img.setHint("Image URL (leave blank for default)");
        layout.addView(img);

//        final EditText releaseAu = new EditText(this);
//        releaseAu.setHint("AU ReleaseDate");
//        layout.addView(releaseAu);

        final EditText releaseEu = new EditText(this);
        releaseEu.setHint("EU ReleaseDate");
        layout.addView(releaseEu);

//        final EditText jp = new EditText(this);
//        jp.setHint("JP ReleaseDate");
//        layout.addView(jp);
//
//        final EditText na = new EditText(this);
//        na.setHint("NA ReleaseDate");
//        layout.addView(na);

        final EditText fType = new EditText(this);
        fType.setHint("Type");
        layout.addView(fType);

        builder.setView(layout);





        builder.setCancelable(true);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String amiiboName = name.getText().toString();
                String amiiboSeries = aSeries.getText().toString();
                String gameSeries = gSeries.getText().toString();
                String headID = hID.getText().toString();
                String tailID = tID.getText().toString();
                String image = img.getText().toString();
                String au = "N/A";
                String eu = releaseEu.getText().toString();
                String jp = "N/A";
                String na = "N/A";
                String type = fType.getText().toString();
                String character = chara.getText().toString();


                Amiibo amiibo = new Amiibo(amiiboName, amiiboSeries, gameSeries, headID, tailID, image, au, eu, jp, na, type, character);

                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Saved Amiibos/"+ headID+"-"+tailID);
                mDatabaseReference.setValue(amiibo);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void fillMyListView(ArrayList mAmiiboList){

        CustomAdapter myCustomAdapter = new CustomAdapter(MyAmiiboList.this, mAmiiboList);
        listView.setAdapter(myCustomAdapter);
    }
}





