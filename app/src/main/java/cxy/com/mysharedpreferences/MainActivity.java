package cxy.com.mysharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
/*
 *  *- 小陈-*
 *
 *
 */

public class MainActivity extends AppCompatActivity {
    private Button btn_play,btn_zhuce;
    private EditText et_name,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_name);
        et_password=findViewById(R.id.et_password);
        btn_play=findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_name.getText().toString();
                String password=et_password.getText().toString();
                    SpsaveQQ(name,password);
            }
        });
        btn_zhuce=findViewById(R.id.btn_zhuce);
        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
    public void  SpsaveQQ(String name,String password){
        try {
        //获取sp对象  参数data表示文件名，MODE_PRIVATE 表示文件操作格式
        SharedPreferences sp=this.getSharedPreferences("data",this.MODE_PRIVATE);
        //获取到JSON数据
        String name1=sp.getString("data",null);
        //解析数据
        JSONObject jsonObject=new JSONObject(name1);
            System.out.println(jsonObject);
        if (jsonObject.getString(name)!=null &&password.equals(jsonObject.getString(name))){
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"用户名或者密码错误",Toast.LENGTH_LONG).show();
        }
        } catch (JSONException e) {
            Toast.makeText(MainActivity.this,"没有获取到数据", Toast.LENGTH_SHORT).show();
        }
    }

}
