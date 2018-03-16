package ie.wit.a20076447.amiibodatabase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{

    Context mContext;
    ArrayList<Amiibo> amiibos = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Amiibo> amiibos) {
        mContext = context;
        this.amiibos = amiibos;
    }


    @Override
    public int getCount() {
        return amiibos.size();
    }

    @Override
    public Object getItem(int i) {
        return amiibos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_rows, viewGroup, false);
        }

        Amiibo tempAmiibo = (Amiibo) getItem(i);

        TextView list_amiiboName = (TextView)view.findViewById(R.id.list_amiiboName);
        TextView list_gameSeries = (TextView)view.findViewById(R.id.list_gameSeries);
        TextView list_amiiboSeries = (TextView)view.findViewById(R.id.list_amiiboSeries);
        TextView list_headID = (TextView)view.findViewById(R.id.list_headID);
        TextView list_tailID = (TextView)view.findViewById(R.id.list_tailID);
        ImageButton list_image  = (ImageButton) view.findViewById(R.id.list_image);

        list_amiiboName.setText(tempAmiibo.getAmiiboName());
        list_gameSeries.setText(tempAmiibo.getGameSeries());
        list_amiiboSeries.setText(tempAmiibo.getAmiiboSeries());
        list_headID.setText(tempAmiibo.getHeadID());
        list_tailID.setText(tempAmiibo.getTailID());
        //list_image.setImageDrawable(tempAmiibo.getImage());



        return view;
    }
}
