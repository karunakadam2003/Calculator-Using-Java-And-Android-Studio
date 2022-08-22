package com.example.calculator;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV,solnTV;
    MaterialButton button_plus,button_sub,button_mul,button_div,button_7,button_8,button_9,
            button_Parantheses,button_Parentheses2,button_4,button_5,button_6,button_1,button_2,button_3,button_C,button_AC,
            button_0,button_dot,button_equalTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.resultTV);
        solnTV = findViewById(R.id.solnTV);
        assignID(button_C,R.id.button_C);
        assignID(button_plus,R.id.button_plus);
        assignID(button_Parantheses,R.id.button_Parantheses);
        assignID(button_Parentheses2,R.id.button_Parentheses2);
        assignID(button_1,R.id.button_1);
        assignID(button_2,R.id.button_2);
        assignID(button_3,R.id.button_3);
        assignID(button_4,R.id.button_4);
        assignID(button_5,R.id.button_5);
        assignID(button_6,R.id.button_6);
        assignID(button_7,R.id.button_7);
        assignID(button_8,R.id.button_8);
        assignID(button_9,R.id.button_9);
        assignID(button_0,R.id.button_0);
        assignID(button_sub,R.id.button_sub);
        assignID(button_mul,R.id.button_mul);
        assignID(button_div,R.id.button_div);
        assignID(button_equalTo,R.id.button_equalTo);
        assignID(button_AC,R.id.button_AC);


    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button  = (MaterialButton)view ;
        String buttonText = button.getText().toString();
        String dataCalculate = solnTV.getText().toString();
        if(buttonText.equals("AC")){
            solnTV.setText("");
            resultTV.setText("0");
            return;

        }
        if(buttonText.equals("=")){
            solnTV.setText(resultTV.getText());

            return;

        }
        if(buttonText.equals("C")){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);

        }else{
            dataCalculate= dataCalculate +buttonText;
        }
        solnTV.setText(dataCalculate);
        String finalRes = getResult(dataCalculate);
        if(!finalRes.equals("Error")){
            resultTV.setText(finalRes);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;

        }catch(Exception e){
            return "Error";

        }


    }
}
