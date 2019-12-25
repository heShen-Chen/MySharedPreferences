package cxy.com.mysharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/*
 *  *- 小陈-*
 */

public class Main2Activity extends AppCompatActivity {
    private Button btn_one;
    private EditText et_one,et_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_one=findViewById(R.id.btn_one);
        et_one=findViewById(R.id.et_one);
        et_two=findViewById(R.id.et_two);
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                //改用JSON
                try {
                    Map map=new HashMap();
                    map=sp.getAll();
                    JSONObject jsonObject=new JSONObject(map);
                    jsonObject.put(et_one.getText().toString(),et_two.getText().toString());
                    editor.putString("data",jsonObject.toString());
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(Main2Activity.this,"注册成功",Toast.LENGTH_LONG).show();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                finish();
            }
        });

    }
}
