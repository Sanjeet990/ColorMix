package com.gravity.colormixapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gravity.colormix.ColorSelector;

public class MainActivity extends AppCompatActivity {

    //Declaring views
    EditText initcoloralpha = null;
    EditText initcolorred = null;
    EditText initcolorgreen = null;
    EditText initcolorblue = null;
    CheckBox alpha = null;
    CheckBox preview = null;
    CheckBox advanced = null;
    Button select = null;
    LinearLayout pallate = null;
    TextView colorName = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defining views
        initcoloralpha = (EditText) findViewById(R.id.initcoloralpha);
        initcolorred = (EditText) findViewById(R.id.initcolorred);
        initcolorgreen = (EditText) findViewById(R.id.initcolorgreen);
        initcolorblue = (EditText) findViewById(R.id.initcolorblue);

        alpha = (CheckBox) findViewById(R.id.alpha);
        preview = (CheckBox) findViewById(R.id.preview);
        advanced = (CheckBox) findViewById(R.id.advanced);

        select = (Button) findViewById(R.id.select);
        pallate = (LinearLayout) findViewById(R.id.pallate);
        colorName = (TextView) findViewById(R.id.color);

        //Set onc;licklistener on button
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector cs = new ColorSelector(MainActivity.this,
                        new ColorSelector.ColorControl() {
                    @Override
                    public void onCancelled() {
                        //Show toast that user cancelled it
                        Toast.makeText(MainActivity.this, "Cancelled by the user",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onColorSelected(int color) {
                        pallate.setBackgroundColor(color);
                        colorName.setText("" + color);
                    }
                }, 3);

                cs.setARGBColor(Integer.valueOf(initcoloralpha.getText().toString()),
                        Integer.valueOf(initcolorred.getText().toString()),
                        Integer.valueOf(initcolorgreen.getText().toString()),
                        Integer.valueOf(initcolorblue.getText().toString()));
                cs.supportsAlpha(alpha.isChecked());
                cs.colorPreview(preview.isChecked());
                cs.showLEDS(advanced.isChecked());
                cs.show();
            }
        });
    }
}
