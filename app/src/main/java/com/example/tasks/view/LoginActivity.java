package com.example.tasks.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasks.R;
import com.example.tasks.entities.Response;
import com.example.tasks.util.BiometricHelper;
import com.example.tasks.viewmodel.LoginViewModel;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final ViewHolder mViewHolder = new ViewHolder();
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

        this.mLoginViewModel.isBiometricAvailable();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_login) {
            String email = this.mViewHolder.editEmail.getText().toString();
            String password = this.mViewHolder.editPassword.getText().toString();

            this.mLoginViewModel.login(email, password);
        } else if (id == R.id.text_signup) {
            startActivity(new Intent(getApplicationContext(), SignupActivity.class));
        }
    }

    private void loadObservers() {
        this.mLoginViewModel.loginResponse.observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response.isSuccess()) {
                    startMain();
                } else {
                    Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });
        this.mLoginViewModel.biometric.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLogged) {
                if (isLogged) {
                    openAuthentication();
                }
            }
        });
    }



    private void startMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void componentMapping() {
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = findViewById(R.id.edit_password);
        this.mViewHolder.buttonLogin = findViewById(R.id.button_login);
        this.mViewHolder.textSignup = findViewById(R.id.text_signup);
    }

    private void setListeners() {
        this.mViewHolder.buttonLogin.setOnClickListener(this);
        this.mViewHolder.textSignup.setOnClickListener(this);
    }

    private void openAuthentication() {
        // Passos biometria
        // Executor
        Executor executor = ContextCompat.getMainExecutor(this);
        // BiometricPrompt
        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startMain();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        // BiometricInfo
        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.insert_biometric))
                .setNegativeButtonText("Cancelar")
                .build();

        biometricPrompt.authenticate(info);
    }


    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editEmail, editPassword;
        Button buttonLogin;
        TextView textSignup;
    }

}