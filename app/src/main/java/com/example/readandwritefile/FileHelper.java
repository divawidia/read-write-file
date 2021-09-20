package com.example.readandwritefile;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileHelper {
    static void simpanNote(String namaNote, String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(namaNote, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e){
            Log.e("Exeption", "Gagal membuat note: " + e.toString());
        }
    }
    static String membacaNote(Context context, String namaNote){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(namaNote);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String recieveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((recieveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(recieveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("Login activity", "note tidak ditemukan: " + e.toString());
        } catch (IOException e){
            Log.e("login activity", "tidak bisa membaca note: " + e.toString());
        }
        return ret;
    }
}
