package com.amoad.amoadandroidsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class FormActivity extends HomeButtonActivity implements View.OnClickListener {
    private EditText mEditText;
    private Class<?> next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mEditText = (EditText) findViewById(R.id.edit_sid);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);

        try {
            next = Class.forName(getIntent().getStringExtra("next_activity"));
        } catch (ClassNotFoundException e) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                clearSid();
                break;
            case R.id.btn_next:
                next();
        }
    }

    private void clearSid() {
        mEditText.setError(null);
        mEditText.setText(null);
    }

    private String getSid() {
        return mEditText.getText().toString();
    }

    private void next() {
        String sid = getSid();
        if (sid == null || !Pattern.matches("[a-f0-9]{64}", sid)) {
            mEditText.requestFocus();
            mEditText.setError("sid不正です");
        } else {
            mEditText.setError(null);
            startActivity(new Intent(this, next).putExtra("sid", sid));
            finish();
        }
    }
}
