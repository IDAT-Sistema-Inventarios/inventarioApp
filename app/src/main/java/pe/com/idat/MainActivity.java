package pe.com.idat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.com.idat.databinding.ActivityHomeBinding;
import pe.com.idat.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegistrarLogin(view);
            }
        });

        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAutenticacion(view);
            }
        });


    }


    public void showRegistrarLogin(View view) {
        Intent registrar = new Intent(this,RegistrarLogin.class);
        startActivity(registrar);
    }

    public void showAutenticacion(View view) {
        String usuario = binding.txtUsuario.getText().toString();
        String password = binding.txtPassword.getText().toString();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            signIn(usuario, password);
        } else {
            Toast.makeText(this, "Ambos campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarActividadHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    boolean conexion= false;
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(MainActivity.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            conexion = true;
                            iniciarActividadHome(); // Mover aquí
                        } else {
                            // If sign in fails
                            Toast.makeText(MainActivity.this, "Falló la Autenticación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}