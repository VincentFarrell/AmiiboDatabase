package ie.wit.a20076447.amiibodatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAmiiboList extends AppCompatActivity {

    private ArrayList<Amiibo> myAmiiboList = new ArrayList<>();
    public ListView listView;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_amiibo_list);

        myAmiiboList.clear();

        listView = findViewById(R.id.listView_asd);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("Saved Amiibos").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Amiibo myAmiibos = dataSnapshot.getValue(Amiibo.class);

                        myAmiiboList.add(myAmiibos);

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





        fillMyListView();
    }

    private void fillMyListView() {

        CustomAdapter myCustomAdapter = new CustomAdapter(MyAmiiboList.this, myAmiiboList);
        listView.setAdapter(myCustomAdapter);
    }
}





