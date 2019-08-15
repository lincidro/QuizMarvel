package com.eduardo.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    private TextView tvQuestion;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;

    private Question[] questions;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initXML();
        initVars();
    }

    private void initXML(){
        tvQuestion = findViewById(R.id.tv_question);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnNext = findViewById(R.id.btn_next);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void initVars(){
        currentPosition = 0;
        questions = new Question[]{
            new Question(R.string.question_1,true),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,false),
            new Question(R.string.question_4,true)
        };

        //Inicializar pregunta y boton
        int question = questions[currentPosition].getId();
        tvQuestion.setText(question);
        btnNext.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_navigate_next_black_24dp,0,0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_true:
                checkAnswer(true);
                break;
            case R.id.btn_false:
                checkAnswer(false);
                break;
            case R.id.btn_next:
                Log.d(TAG, "onClick: btn_next"+currentPosition+"_"+(questions.length-1));
                int arraySize = questions.length-1;
                currentPosition++;
                if(currentPosition == arraySize) {
                    btnNext.setText(R.string.txt_restart);
                    btnNext.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_refresh_black_24dp,0,0);
                    updateQuestion();
                }
                else if(currentPosition > arraySize) {
                    currentPosition = 0;
                    updateQuestion();
                    btnNext.setText(R.string.txt_next);
                    btnNext.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_navigate_next_black_24dp,0,0);
                }
                else{
                    btnNext.setText(R.string.txt_next);
                    btnNext.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_navigate_next_black_24dp,0,0);
                    updateQuestion();
                }

                break;
        }
    }

    private void updateQuestion(){
        tvQuestion.setText(questions[currentPosition].getId());
    }

    public void checkAnswer(boolean pressedTrue){
        int message = 0;
        boolean answerIsTrue = questions[currentPosition].isAnswerTrue();
        if(pressedTrue == answerIsTrue)
            message = R.string.txt_correct_answer;
        else
            message = R.string.txt_incorrect_answer;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
