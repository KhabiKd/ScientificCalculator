package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity {

    private EditText inputExpression;
    private TextView displayAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        inputExpression = findViewById(R.id.inputText);
        displayAnswer = findViewById(R.id.displayAnswer);

        inputExpression.setShowSoftInputOnFocus(false);

    }


    private void updateText(String strAdd){
        String oldStr = inputExpression.getText().toString();

        int cursorPos = inputExpression.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        inputExpression.setText(String.format("%s%s%s", leftStr, strAdd, rightStr));
        inputExpression.setSelection(cursorPos + strAdd.length());
    }

    public void changeScreenOrientation(View view) {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
        if (Settings.System.getInt(getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }, 4000);
        }
    }

    public void zeroBtnPush(View view){
        updateText(getResources().getString(R.string.zero));
    }

    public void oneBtnPush(View view){
        updateText(getResources().getString(R.string.oneText));
    }

    public void twoBtnPush(View view){
        updateText(getResources().getString(R.string.twoText));
    }

    public void threeBtnPush(View view){
        updateText(getResources().getString(R.string.threeText));
    }

    public void fourBtnPush(View view){
        updateText(getResources().getString(R.string.fourText));
    }

    public void fiveBtnPush(View view){
        updateText(getResources().getString(R.string.fiveText));
    }

    public void sixBtnPush(View view){
        updateText(getResources().getString(R.string.sixText));
    }

    public void sevenBtnPush(View view){
        updateText(getResources().getString(R.string.sevenText));
    }

    public void eightBtnPush(View view){
        updateText(getResources().getString(R.string.eightText));
    }

    public void nineBtnPush(View view){
        updateText(getResources().getString(R.string.nineText));
    }

    public void addBtnPush(View view){
        updateText(getResources().getString(R.string.add));
    }

    public void subtractBtnPush(View view){
        updateText(getResources().getString(R.string.subtract));
    }

    public void multiplyBtnPush(View view){
        updateText(getResources().getString(R.string.multiply));
    }

    public void divideBtnPush(View view){
        updateText(getResources().getString(R.string.divide));
    }

    public void clearBtnPush(View view){
        inputExpression.setText("");
    }

    public void pointBtnPush(View view){
        updateText(getResources().getString(R.string.point));
    }

    public void percentBtnPush(View view){
        updateText(getResources().getString(R.string.percent));
    }

    public void parenthesesBtnPush(View view){
        int cursorPos = inputExpression.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = inputExpression.getText().length();

        for(int i = 0; i < cursorPos; i++){
            if(inputExpression.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if(inputExpression.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }
        if(openPar == closedPar || inputExpression.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if(closedPar < openPar && !inputExpression.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        inputExpression.setSelection(cursorPos+1);
    }

    public void negativeBtnPush(View view){
        updateText("(-");
    }

    public void equalsBtnPush(View view){
        String userExpression = inputExpression.getText().toString();

        userExpression = userExpression.replaceAll(getResources().getString(R.string.divide), "/");
        userExpression = userExpression.replaceAll(getResources().getString(R.string.multiply), "*");
        userExpression = userExpression.replaceAll(getResources().getString(R.string.sqrt), "sqrt");
        userExpression = userExpression.replaceAll(getResources().getString(R.string.pi), "pi");

        Expression exp = new Expression(userExpression);
        String result = String.valueOf(exp.calculate());

        displayAnswer.setText(result);

    }

    public void backspaceBtnPush(View view){
        int cursorPos = inputExpression.getSelectionStart();
        int textLen = inputExpression.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) inputExpression.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            inputExpression.setText(selection);
            inputExpression.setSelection(cursorPos-1);
        }
    }

    public void SwitchBtnPush(View view){

    }

    public void RadianBtnPush(View view){
        updateText("rad(");
    }

    public void sqrtBtnPush(View view){
        updateText(getResources().getString(R.string.sqrt) + "(");
    }

    public void sinBtnPush(View view){
        updateText("sin(");
    }

    public void cosBtnPush(View view){
        updateText("cos(");
    }

    public void tanBtnPush(View view){
        updateText("tan(");
    }

    public void lnBtnPush(View view){
        updateText("ln(");
    }

    public void logBtnPush(View view){
        updateText("log(");
    }

    public void oneDivideXBtnPush(View view){
        updateText("1" + getResources().getString(R.string.divide));
    }

    public void expInXBtnPush(View view){
        updateText("e^(");
    }

    public void squareXBtnPush(View view){
        updateText("^(2)");
    }

    public void xInYBtnPush(View view){
        updateText("^(");
    }

    public void absXBtnPush(View view){
        updateText("abs(");
    }

    public void piBtnPush(View view){
        updateText(getResources().getString(R.string.pi));
    }

    public void expBtnPush(View view){
        updateText("e");
    }
}
