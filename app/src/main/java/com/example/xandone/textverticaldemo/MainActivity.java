package com.example.xandone.textverticaldemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xandone.textverticaldemo.view.TextVerticalGroup;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextVerticalGroup g3 = (TextVerticalGroup) this.findViewById(R.id.g3);
        g3.setContent("西风烈," +
                "长空雁叫霜晨月," +
                "霜晨月," +
                "马蹄声碎," +
                "喇叭声咽," +
                "雄关漫道真如铁," +
                "而今迈步从头越," +
                "从头越," +
                "苍山如海," +
                "残阳如血", 3);
        g3.setContentColor(Color.BLACK);
        g3.setContentSize(30);
    }
}
