package com.example.calculator;


import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.util.*;

public class CalculatorView extends LinearLayout
{

    TextView text;
    Button oneButton;

    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context){
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        setOrientation(LinearLayout.VERTICAL);

        text = new TextView(context,null);
        text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        text.setText("");
        this.addView(text);


        this.addView(getOperands(context));

    }

    protected View getOperands(Context context){
        LinearLayout rows = new LinearLayout (context);
        rows.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        rows.setOrientation(LinearLayout.VERTICAL);

        for (int i=2; i >= -1; i--){

            LinearLayout columns = new LinearLayout (context);
            columns.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            columns.setOrientation(LinearLayout.HORIZONTAL);

            for(int j=0; j < 3; j++){
                columns.addView(getButton( (i*3) + j+1, context));
            }

            rows.addView(columns);
        }


        return rows;
    }

    protected View getButton(int num,Context context){
        Button button = new Button(context);
        button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        if(num==(-2))
            button.setText("CLR");
        else if(num==(-1))
            button.setText("" + 0);
        else if(num==(0))
            button.setText(".");
        else
            button.setText("" + num);

        button.setTag(num);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if((Integer) v.getTag()==(-2))
                {
                    text.setText("");
                }
                else if(text.getText().toString().equals("0") && (Integer) v.getTag()==-1 )
                {
                    text.setText("0");
                }
                else if((Integer) v.getTag()==-1)
                {
                    text.setText(text.getText() + "0");
                }
                else if((Integer) v.getTag()==0)
                {
                    text.setText(text.getText() + ".");
                }
                else
                {
                    if(text.getText().toString().equals("0"))
                        text.setText(((Integer) v.getTag()).toString());
                    else
                        text.setText((text.getText()) + ((Integer) v.getTag()).toString());
                }
            }
        });

        return button;
    }

}
