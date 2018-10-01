package com.example.jhonarendra.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PindahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pindah);

        Bundle b = getIntent().getExtras();

        TextView nama = (TextView) findViewById(R.id.namaValue);
        TextView agama = (TextView) findViewById(R.id.agamaValue);
        TextView jeniskelamin = (TextView) findViewById(R.id.jeniskelaminValue);

        nama.setText(b.getCharSequence("nama"));
        agama.setText(b.getCharSequence("agama"));
        jeniskelamin.setText(b.getCharSequence("jeniskelamin"));

    }
}
