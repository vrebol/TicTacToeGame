package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static String z = "zmagovalec";
    CharSequence c = "X";
    CharSequence d = "O";
    int[][] mreza = new int[3][3];
    int stevec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Disable(View v){
        Button b = (Button) v;
        b.setEnabled(false);
        Drawable buttonDrawable = b.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, Color.WHITE);
        b.setBackground(buttonDrawable);
        b.setText(c);
        stevec++;
        int a = b.getId();
        int vpis;
        // O = 1
        // X = 2
        // PRAZEN = NIC,


        /*String s = Integer.toString(a);

        Log.d("idgumba",s);*/

        if(c.equals("O")){
            c = "X";
            d = "O";
            vpis = 1;
        }else{
            c = "O";
            d = "X";
            vpis = 2;
        }
        switch(a) {
            case R.id.button1:
                mreza[0][0] = vpis;
                break;
            case R.id.button2:
                mreza[0][1] = vpis;
                break;
            case R.id.button3:
                mreza[0][2] = vpis;
                break;
            case R.id.button4:
                mreza[1][0] = vpis;
                break;
            case R.id.button5:
                mreza[1][1] = vpis;
                break;
            case R.id.button6:
                mreza[1][2] = vpis;
                break;
            case R.id.button7:
                mreza[2][0] = vpis;
                break;
            case R.id.button8:
                mreza[2][1] = vpis;
                break;
            case R.id.button9:
                mreza[2][2] = vpis;
                break;
            default:
                break;

        }
        Log.d("mreza", Arrays.deepToString(mreza));
        if(Preveri()) {
            Log.d("pogoj", "sem v pogoju");
            /*Toast toast = Toast.makeText(getApplicationContext(), "Konec", Toast.LENGTH_LONG);
            toast.show();*/
            Intent i = new Intent(this, GameOverActivity.class);
            i.putExtra(z, d);
            startActivity(i);
        }else if(stevec == 9){
            Intent i = new Intent(this, GameOverActivity.class);
            i.putExtra(z, "d");
            startActivity(i);
        }
        /*
        Button prvi = (Button) findViewById(R.id.button1);
        String s = (String) prvi.getText();
        */

        //Log.d("hejla", s);

    }

    private boolean Preveri() {
        return preveriVertikalno() || preveriHorizontalno() || preveriDiagonalno();
    }

    private boolean preveriDiagonalno() {
        if(mreza[1][1] == 0){
            return false;
        }
        return (mreza[1][1] == mreza[0][0] && mreza[1][1] == mreza[2][2]) ||
                (mreza[1][1] == mreza[0][2] && mreza[1][1] == mreza[2][0]);
    }

    private boolean preveriHorizontalno() {
        for(int i = 0; i < 3; i++){
            if(mreza[i][0] == 0){
                continue;
            }
            if(mreza[i][0] == mreza[i][1] && mreza[i][1] == mreza[i][2]){
                return true;
            }
        }
        return false;
    }

    private boolean preveriVertikalno() {
        for(int i = 0; i < 3; i++){
            if(mreza[0][i] == 0){
                continue;
            }
            if(mreza[0][i] == mreza[1][i] && mreza[1][i] == mreza[2][i]){
                return true;
            }
        }
        return false;
    }
}