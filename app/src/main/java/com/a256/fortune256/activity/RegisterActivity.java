package com.a256.fortune256.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a256.fortune256.R;

/**
 * Created by de165 on 2018/2/5.
 */

public class RegisterActivity extends Activity  implements View.OnClickListener{
    private Button cancel;
    private Button sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initRegister();
    }
    private void initRegister() {
        sure = findViewById(R.id.register_btn_sure);
        cancel = findViewById(R.id.register_btn_cancel);

        sure.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.register_btn_cancel:
                this.finish();
                break;

            case R.id.register_btn_sure:
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
            default:break;
        }
    }
}
