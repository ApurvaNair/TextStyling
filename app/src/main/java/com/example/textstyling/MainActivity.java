package com.example.textstyling;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    private Button upButton;
    private Button downButton,btn;
    private EditText editText,editText1;
    private int uprange = 70;
    private int downrange = 0;
    private int currentFontSize;
    Spinner fontSpinner;
    String[] fontNames;
    Typeface selectedFont;
    private Button colorPickerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.textView3);
        upButton = (Button) findViewById(R.id.button2);
        downButton = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextNumber);
        editText1=(EditText) findViewById(R.id.edt1);
        btn=(Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText1.getText().toString();
                text.setText(str);
            }
        });
        currentFontSize = (int) getResources().getDimension(R.dimen.default_font_size);
        text.setTextSize(currentFontSize);
        editText.setText(""+currentFontSize);

        upButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentFontSize < uprange) {
                    currentFontSize += getResources().getDimensionPixelSize(R.dimen.font_size_increment);
                    if (currentFontSize > uprange) {
                        currentFontSize = uprange;
                    }
                    editText.setText("" + currentFontSize);
                    text.setTextSize(currentFontSize);
                }
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (currentFontSize > downrange) {
                    currentFontSize -= getResources().getDimensionPixelSize(R.dimen.font_size_increment);
                    if (currentFontSize < downrange) {
                        currentFontSize = downrange;
                    }
                    editText.setText("" + currentFontSize);
                    text.setTextSize(currentFontSize);
                }
            }
        });

        fontSpinner = findViewById(R.id.fontSpinner);
        fontNames = getResources().getStringArray(R.array.font_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fontNames);
        fontSpinner.setAdapter(adapter);

        fontSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFont = (String) parent.getItemAtPosition(position);
                Typeface typeface = Typeface.create(selectedFont,Typeface.NORMAL);
                text.setTypeface(typeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    float x,y;
    float dx,dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x=event.getX();
            y=event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            dx=event.getX() -x;
            dy=event.getY() -y;

            text.setX(text.getX() +dx);
            text.setY(text.getY() +dy);

            x=event.getX();
            y=event.getY();
        }
        return super.onTouchEvent(event);
    }

}