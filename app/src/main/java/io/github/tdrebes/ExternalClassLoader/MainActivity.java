package io.github.tdrebes.ExternalClassLoader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Path to dex file
            String path = getExternalFilesDir("lib") + "/classes.dex";
            ExternalClass class1 = new ExternalClass(path);

            Log.e("Main", class1.helloWorld());
            Log.e("Main", class1.helloWorld(" with args"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
