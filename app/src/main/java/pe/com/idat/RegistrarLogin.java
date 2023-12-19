package pe.com.idat;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.com.idat.bd.BD;
import pe.com.idat.databinding.ActivityRegistrarLoginBinding;
import pe.com.idat.entities.Producto;

public class RegistrarLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    private ActivityRegistrarLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        // Agrega el OnClickListener al botón
        binding.btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClick(view);
            }
        });

        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarActivityPrincipal(view);
            }
        });

    }





    // Método para manejar el clic del botón "Registrar"
    public void onRegisterButtonClick(View view) {
        String email = binding.txtUsuario.getText().toString();
        String password = binding.txtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {

            NetworkUtils conexionInternet = new NetworkUtils(getApplicationContext());


            if(conexionInternet.isNetworkAvailable()){

                createAccount(email, password);

                //saveToLocalDatabase(email, password);

            }else{
                //createAccount(email, password);
                // No hay conexión a Internet, almacenar localmente
                //saveToLocalDatabase(email, password);
                // Muestra un mensaje indicando que la operación se realizará cuando haya conexión
                Toast.makeText(this, "No hay conexión a Internet. Los datos se almacenarán localmente y se sincronizarán más tarde.", Toast.LENGTH_LONG).show();
            }

        } else {

            Toast.makeText(this, "Por favor, complete ambos campos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso, actualizar UI con la información del usuario registrado
                        FirebaseUser FirebaseUser = mAuth.getCurrentUser();
/*
                        Usuario localUsuario= new Usuario();

                        localUsuario.setCorreo(FirebaseUser.getEmail());
                        localUsuario.setPassword("");

                        new InsertarUsuario().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, localUsuario);
*/

                        Toast.makeText(RegistrarLogin.this, "¡Su registro fue exitoso!", Toast.LENGTH_LONG).show();


                    } else {
                        // Si falla el registro, mostrar un mensaje al usuario.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegistrarLogin.this, "Error en la autenticación.",
                                Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void saveToLocalDatabase(String email, String password) {
        // Implementa la lógica para almacenar localmente los datos
        // Puedes usar Room o cualquier otro método que prefieras
        // Aquí, por ejemplo, asumo que tienes una instancia de RoomDatabase llamada BD
        Usuario localUsuario = new Usuario();
        localUsuario.setCorreo(email);
        localUsuario.setPassword(password);

        new InsertarUsuario().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, localUsuario);
    }


    private static class InsertarUsuario extends AsyncTask<Usuario, Void, Void> {
        @Override
        protected Void doInBackground(Usuario... users) {
            //BD.appDatabase.daoUsuario().insertarProducto(Producto dd);
            return null;
        }
    }


    public void regresarActivityPrincipal(View view){

        Intent regresar= new Intent(this,MainActivity.class);
        startActivity(regresar);
    }

}
