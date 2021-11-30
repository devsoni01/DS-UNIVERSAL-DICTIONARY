package com.universal.dsdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.universal.dsdictionary.Adapters.MeaningAdapter;
import com.universal.dsdictionary.Adapters.PhoneticsAdapter;
import com.universal.dsdictionary.Models.Apiresponse;

public class MainActivity extends AppCompatActivity {

    SearchView search_view;
    TextView textview_word;
    RecyclerView recycler_phonetics,recycler_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        textview_word = findViewById(R.id.textview_word);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        Requestmanager manager = new Requestmanager(MainActivity.this);
        manager.getWordMeaning(listener, "dream");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching Response For " + query);
                progressDialog.show();
                Requestmanager manager = new Requestmanager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(Apiresponse apiresponse, String message) {
            progressDialog.dismiss();
            if (apiresponse==null){
                Toast.makeText(MainActivity.this, "No Data Found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiresponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(Apiresponse apiresponse) {
        textview_word.setText("Word: " + apiresponse.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiresponse.getPhonetics());
        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiresponse.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);
    }
}