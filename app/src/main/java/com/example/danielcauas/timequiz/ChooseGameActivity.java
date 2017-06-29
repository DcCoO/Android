package com.example.danielcauas.timequiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseGameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        FillListViews();
    }

    public class GameInfo{
        String nomeArquivo;
        int tempo, total;



        public GameInfo(String na, int tot, int tem){
            nomeArquivo = na; tempo = tem; total = tot;
        }

        public GameInfo get(String name){
            if(name.equals("Marcas de carro")) return new GameInfo("Country.txt", 90, 15);
            if(name.equals("Esportes")) return new GameInfo("Country.txt", 120, 20);
            if(name.equals("Paises do mundo")) return new GameInfo("Country.txt", 90, 15);
            if(name.equals("Marcas de carro")) return new GameInfo("Country.txt", 90, 15);
            return null;
        }
    }

    public void FillListViews() {
        ListView l1 = (ListView) findViewById(R.id.listView1);
        ListView l2 = (ListView) findViewById(R.id.listView2);

        String[] objetos_1 =    {"Marcas de carro",   "Esportes"};
        String[] subobjetos_1 = {"15 marcas em 1:30", "20 esportes em 2:00"};

        String[] objetos_2 =    {"Paises do mundo",   "Frutas"};
        String[] subobjetos_2 = {"50 paises em 3:00", "20 frutas em 1:30"};

        CGAdapter a1 = new CGAdapter(objetos_1, subobjetos_1, this);
        CGAdapter a2 = new CGAdapter(objetos_2, subobjetos_2, this);
        l1.setAdapter(a1);
        l2.setAdapter(a2);

        l1.setOnItemClickListener(ChooseGameActivity.this);
        l2.setOnItemClickListener(ChooseGameActivity.this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(ChooseGameActivity.this, (String)parent.getItemAtPosition(position),Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, GameActivity.class);
        String tipo = (String) parent.getItemAtPosition(position);
        i.putExtra("tipo", tipo);

        if(tipo.equals("Marcas de carro")){
            i.putExtra("filename", "Country.txt");
            i.putExtra("total", 15);
            i.putExtra("tempo", 90);
        }
        else if(tipo.equals("Esportes")){
            i.putExtra("filename", "Country.txt");
            i.putExtra("total", 20);
            i.putExtra("tempo", 120);
        }
        else if(tipo.equals("Paises do mundo")){
            i.putExtra("filename", "Country.txt");
            i.putExtra("total", 50);
            i.putExtra("tempo", 180);
        }
        else if(tipo.equals("Frutas")){
            i.putExtra("filename", "Country.txt");
            i.putExtra("total", 20);
            i.putExtra("tempo", 90);
        }

        startActivity(i);
        finish();
    }



    public class CGAdapter extends BaseAdapter {
        private String[] mItems;
        private String[] mSubitems;
        private Context mContext;

        public CGAdapter(String[] items, String[] subitems, Context context) {
            mItems = items;
            mSubitems = subitems;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            View view = inflater.inflate(R.layout.list_view_item, null);

            AppCompatTextView titleTextView = (AppCompatTextView)view.findViewById(R.id.list_view_item_default);
            titleTextView.setText(mItems[position]);
            AppCompatTextView subtitleTextView = (AppCompatTextView)view.findViewById(R.id.list_view_subitem_default);
            subtitleTextView.setText(mSubitems[position]);

            return view;
        }
    }
}