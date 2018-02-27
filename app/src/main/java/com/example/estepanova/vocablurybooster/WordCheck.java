package com.example.estepanova.vocablurybooster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class WordCheck extends AppCompatActivity {

    private Dictionary currentDictionary;

    private TextView trans1;
    private Button btnCheck;

    private Integer count;

    private String word;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_check);

        trans1 = (TextView) findViewById(R.id.trans1View);
        btnCheck = (Button) findViewById(R.id.checkBtn);

        Intent saveIntent = getIntent();

        if (saveIntent.getExtras() == null) {
            Log.i("Wordcheck:", "no words to check");
        } else {
            currentDictionary = (Dictionary) saveIntent.getSerializableExtra("dictionary");
            if(saveIntent.getSerializableExtra("count")==null){
                count=0;
            } else {
                count = (Integer) saveIntent.getSerializableExtra("count");
            }
            Log.i("Wordcheck:", count.toString());
        }

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), WordAnswer.class);
                i.putExtra("dictionary", currentDictionary);
                i.putExtra("word", word);
                i.putExtra("count", count);
                startActivity(i);

            }
        });

        initializeCheck();

    }

    private void initializeCheck(){

        word = currentDictionary.testWord();
        HashMap<String, String> wordsMap = currentDictionary.getWordsMap();
        String translation = wordsMap.get(word);
        trans1.setText(translation);
        count++;
    }
}
