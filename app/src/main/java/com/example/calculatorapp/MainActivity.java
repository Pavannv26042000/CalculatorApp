package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView, textResult;
    MaterialButton button_c, button_percent,
            button_divide, button_7, button_8,
            button_9, button_4, button_5, button_6,
            button_1, button_2, button_3, button_multiply,
            button_minus, button_add, button_Zero,
            button_dot, button_equal;
    ImageButton button_backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textResult = findViewById(R.id.textResult);
        button_backspace = findViewById(R.id.button_backSpace);
        assignId(button_c, R.id.button_c);
        assignId(button_percent, R.id.button_percent);
        assignId(button_divide, R.id.button_divide);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_minus, R.id.button_minus);
        assignId(button_add, R.id.button_add);
        assignId(button_Zero, R.id.button_Zero);
        assignId(button_dot, R.id.button_dot);
        assignId(button_equal, R.id.button_equal);
        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fakeString = textResult.getText().toString();
                if (fakeString.length() > 0) {
                    fakeString = fakeString.substring(0, fakeString.length() - 1);
                    textResult.setText(fakeString);
                } else {

                }

            }
        });


    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = textResult.getText().toString();
        if (buttonText.contains("C")) {
            textResult.setText("");
            textView.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            textResult.setText(textView.getText());
            return;
        } else {
            dataToCalculate = dataToCalculate + buttonText;

        }
        textResult.setText(dataToCalculate);
        String finalResult = result(dataToCalculate);
        if (!finalResult.equals("Error")) {
            textView.setText(finalResult);
        }
    }

    public String result(String str) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String Result = context.evaluateString(scriptable, str, "Javascript", 1, null).toString();
            return Result;
        } catch (Exception e) {
            return "Error";
        }

    }

}