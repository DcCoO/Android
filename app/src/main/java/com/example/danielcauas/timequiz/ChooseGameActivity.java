package com.example.danielcauas.timequiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ChooseGameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView l1, l2;

    String[] objetos_1 =    {"Carros",   "Esportes"};
    String[] subobjetos_1 = {"15 marcas em 1:30", "20 esportes em 2:00"};

    String[] objetos_2 =    {"Paises",   "Frutas"};
    String[] subobjetos_2 = {"50 paises em 3:00", "20 frutas em 1:30"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        FillListViews();
        //UpdateListViews();

        PersistenceController.showToast(this);
    }

    public void FillListViews() {
        l1 = (ListView) findViewById(R.id.listView1);
        l2 = (ListView) findViewById(R.id.listView2);

        CGAdapter a1 = new CGAdapter(objetos_1, subobjetos_1, this);
        CGAdapter a2 = new CGAdapter(objetos_2, subobjetos_2, this);
        l1.setAdapter(a1);
        l2.setAdapter(a2);

        l1.setOnItemClickListener(ChooseGameActivity.this);
        l2.setOnItemClickListener(ChooseGameActivity.this);

    }

    public void UpdateListViews(){

        System.out.println("L1 TEM " + l1.getChildCount() + " FILHOS");
        for(int i = 0; i < 2; i++){
            View v = l1.getChildAt(i);
            if(!PersistenceController.isLevelCompleted(ChooseGameActivity.this, objetos_1[i])){
                Util.changeShapeColor(v, 0xffffffff, 0xffe6e6e6);
            }
            else{
                Util.changeShapeColor(v, 0xffF2EEB3, 0xffDEDAA4);
            }
        }

        System.out.println("L2 TEM " + l2.getChildCount() + " FILHOS");

        for(int i = 0; i < 2; i++){
            View v = l2.getChildAt(i);
            if(!PersistenceController.isLevelCompleted(this, objetos_2[i])){
                Util.changeShapeColor(v, 0xffffffff, 0xffe6e6e6);
            }
            else{
                Util.changeShapeColor(v, 0xffF2EEB3, 0xffDEDAA4);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(ChooseGameActivity.this, (String)parent.getItemAtPosition(position),Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, GameActivity.class);
        String tipo = (String) parent.getItemAtPosition(position);
        i.putExtra("tipo", tipo);

        if(tipo.equals("Carros")){
            i.putExtra("filename", "Cars.txt");
            i.putExtra("total", 15);
            i.putExtra("tempo", 90);
        }
        else if(tipo.equals("Esportes")){
            i.putExtra("filename", "Sports.txt");
            i.putExtra("total", 20);
            i.putExtra("tempo", 120);
        }
        else if(tipo.equals("Paises")){
            i.putExtra("filename", "Country.txt");
            i.putExtra("total", 50);
            i.putExtra("tempo", 180);
        }
        else if(tipo.equals("Frutas")){
            i.putExtra("filename", "Fruit.txt");
            i.putExtra("total", 1); //20
            i.putExtra("tempo", 90);
        }

        startActivity(i);
        finish();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(MusicController.getInstance(this).playing() && !leaving){
            MusicController.getInstance(this).stop();
        }
    }

    @Override
    public void onResume(){
        if(!MusicController.getInstance(this).playing() && !MusicController.getInstance(this).paradoPorOpcao){
            MusicController.getInstance(this).play();
        }
        super.onResume();
    }

    public boolean leaving = false;

    @Override
    public void onBackPressed(){
        leaving = true;
        Util.navigate(this, MainActivity.class);
    }


    public void musicButtonPress(View view){
        if(MusicController.getInstance(this).playing()){
            MusicController.getInstance(this).stop();
            MusicController.getInstance(this).paradoPorOpcao = true;
        }
        else{
            MusicController.getInstance(this).play();
            MusicController.getInstance(this).paradoPorOpcao = false;
        }
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

            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/arlrbdb.ttf");
            titleTextView.setTypeface(font);
            subtitleTextView.setTypeface(font);

            //se nao completou

            if(!PersistenceController.isLevelCompleted(ChooseGameActivity.this, mItems[position].toString())){
                view.setBackgroundResource(R.drawable.custom_shape);
            }
            else{
                view.setBackgroundResource(R.drawable.custom_shape_completed);
            }



            return view;
        }
    }
}