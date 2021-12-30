package com.example.tasks.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasks.R;
import com.example.tasks.entities.Response;
import com.example.tasks.viewmodel.RegisterViewModel;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private final ViewHolder mViewHolder = new ViewHolder();
    private RegisterViewModel mRegisterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Mapeamento de elementos
        this.componentMapping();

        // Listeners
        mViewHolder.buttonSignup.setOnClickListener(this);
        mViewHolder.textLogin.setOnClickListener(this);

        // Incializa variáveis
        this.mRegisterViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Cria observadores
        this.loadObservers();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_signup) {
            String name = this.mViewHolder.editName.getText().toString();
            String email = this.mViewHolder.editEmail.getText().toString();
            String password = this.mViewHolder.editPassword.getText().toString();

            this.mRegisterViewModel.signup(name, email, password);
        } else if (id == R.id.text_login) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    private void loadObservers() {
        this.mRegisterViewModel.signResponse.observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response.isSuccess()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void componentMapping() {
        this.mViewHolder.editName = findViewById(R.id.edit_name);
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = findViewById(R.id.edit_password);
        this.mViewHolder.buttonSignup = findViewById(R.id.button_signup);
        this.mViewHolder.textLogin = findViewById(R.id.text_login);
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editName, editEmail, editPassword;
        Button buttonSignup;
        TextView textLogin;
    }
}