package com.example.readandwritefile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editKonten;
    EditText editJudul;
    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button baru = (Button) findViewById(R.id.tombol_baru);
        Button buka = (Button) findViewById(R.id.tombol_buka);
        Button simpan = (Button) findViewById(R.id.tombol_simpan);
        editKonten = (EditText) findViewById(R.id.edit_konten);
        editJudul = (EditText) findViewById(R.id.edit_judul);

        baru.setOnClickListener(this::onClick);
        buka.setOnClickListener(this::onClick);
        simpan.setOnClickListener(this::onClick);
        path = getFilesDir();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tombol_baru:
                editJudul.setText("");
                editKonten.setText("");
                Toast.makeText(this, "Membersihkan note", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tombol_buka:
                final ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(path.list())));
                final CharSequence[] items = arrayList.toArray(new CharSequence[0]);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pilih note yang diinginkan");
                builder.setItems(items, (dialog, item) -> memuatData(items[item].toString()));
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.tombol_simpan:
                if (editJudul.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Judul harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    String judul = editJudul.getText().toString();
                    String text = editKonten.getText().toString();
                    FileHelper.simpanNote(judul, text, this);
                    Toast.makeText(this, "Menyimpan note " + editJudul.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void memuatData(String judul) {
        String teks = FileHelper.membacaNote(this, judul);
        editJudul.setText(judul);
        editKonten.setText(teks);
        Toast.makeText(this, "Memuat note" + judul, Toast.LENGTH_SHORT).show();
    }

}