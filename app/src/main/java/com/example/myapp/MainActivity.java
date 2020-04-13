package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ComposeShader;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp.Retofit.INodeJS;
import com.example.myapp.Retofit.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    MaterialEditText edt_username, edt_password;
    MaterialButton btn_login, btn_register;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        edt_username = (MaterialEditText)findViewById(R.id.edt_username);
        edt_password = (MaterialEditText)findViewById(R.id.edt_password);

        btn_login = (MaterialButton)findViewById(R.id.btn_login);
        btn_register = (MaterialButton)findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(edt_username.getText().toString(), edt_password.getText().toString());
            }
        });

    }

    private void loginUser(String username, String password) {
        compositeDisposable.add(myAPI.loginUser(username,password)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, ""+s.contains("login"), Toast.LENGTH_SHORT).show();
                    }
                })
        );

    }
}
