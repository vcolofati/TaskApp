package com.example.tasks.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasks.R;
import com.example.tasks.entities.Response;
import com.example.tasks.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Incializa as variáveis
        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Mapeamento dos elementos
        this.componentMapping();

        // Listeners
        this.setListeners();

        // Cria observadores
        this.loadObservers();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_login) {
            String email = this.mViewHolder.editEmail.getText().toString();
            String password = this.mViewHolder.editPassword.getText().toString();

            this.mLoginViewModel.login(email, password);
        }
    }

    private void loadObservers() {
        this.mLoginViewModel.loginResponse.observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                String message = response.isSuccess() ? "SUCESSO" : response.getMessage();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void componentMapping() {
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = findViewById(R.id.edit_password);
        this.mViewHolder.buttonLogin = findViewById(R.id.button_login);
    }

    private void setListeners() {
        this.mViewHolder.buttonLogin.setOnClickListener(this);
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editEmail, editPassword;
        Button buttonLogin;
    }

}