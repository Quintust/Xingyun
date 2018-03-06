package com.a256.fortune256.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a256.fortune256.R;

/**
 * Created by de165 on 2018/2/8.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login,register,unsign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initLogin();
    }
    private void initLogin() {
        login = findViewById(R.id.login_btn_login);
        register = findViewById(R.id.login_btn_register);
        unsign = findViewById(R.id.login_btn_cancle);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        unsign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.login_btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                this.finish();
                break;

            case R.id.login_btn_login:
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                this.finish();
                break;

            case R.id.login_btn_cancle:
                Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
            default:break;
        }
    }
}
