package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvResults, tvSolution;
    MaterialButton btnC,btnBrackOpen, btnBrackClose;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton btnDivide, btnPlus, btnMinus,btnMultiply, btnEqual;
    MaterialButton btnAC,btnDot;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvResults = findViewById(R.id.result_tv);
        tvSolution = findViewById(R.id.solution_tv);

        assignID(btnC, R.id.btn_c);
        assignID(btnBrackOpen, R.id.btn_open_bracket);
        assignID(btnBrackClose, R.id.btn_close_bracket);
        assignID(btn1, R.id.btn_1);
        assignID(btn2, R.id.btn_2);
        assignID(btn3, R.id.btn_3);
        assignID(btn4, R.id.btn_4);
        assignID(btn5, R.id.btn_5);
        assignID(btn6, R.id.btn_6);
        assignID(btn7, R.id.btn_7);
        assignID(btn8, R.id.btn_8);
        assignID(btn9, R.id.btn_9);
        assignID(btn0, R.id.btn_0);
        assignID(btnDivide, R.id.btn_divide);
        assignID(btnPlus, R.id.btn_plus);
        assignID(btnMinus, R.id.btn_minus);
        assignID(btnMultiply, R.id.btn_multiply);
        assignID(btnAC, R.id.btn_ac);
        assignID(btnDot, R.id.btn_dot);
        assignID(btnEqual,R.id.btn_equal);








    }

    public void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String buttonText =  btn.getText().toString();
        String dataToCalculate = tvSolution.getText().toString();

        if (buttonText.equals("AC")) {
            tvSolution.setText("");
            tvResults.setText("0");
            return;
        }

        if(buttonText.equals("=")) {
            tvSolution.setText(tvResults.getText());
            return;
        }

        if(buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate += buttonText;
        }

        tvSolution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")) {
            tvResults.setText(finalResult);
        }

    }

    public String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0","");
            }

            return finalResult;

        } catch (Exception e) {
            return "Error";
        }

    }
}