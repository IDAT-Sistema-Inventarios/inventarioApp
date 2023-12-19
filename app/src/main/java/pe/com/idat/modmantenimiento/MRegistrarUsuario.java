package pe.com.idat.modmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import pe.com.idat.ApiRest;
import pe.com.idat.databinding.ActivityMregistrarUsuarioBinding;
import pe.com.idat.response.User_Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MRegistrarUsuario extends AppCompatActivity {

    private ActivityMregistrarUsuarioBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMregistrarUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombre.getText().toString();
                String dni = binding.txtDNI.getText().toString();
                String celular = binding.txtCelular.getText().toString();
                String correo = binding.txtCorreo.getText().toString();


                //eliminar
                Log.d("Retrofit", "Antes de la llamada a Retrofit");


                // Crear una instancia de Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.16:8089/ModConfig/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Crear instancia de la interfaz de API
                ApiRest apiRest = retrofit.create(ApiRest.class);

                // Crear una instancia de User_Response con los datos
                User_Response newUser = new User_Response(nombre, dni, celular, correo);

                // Hacer la llamada para insertar el usuario
                Call<User_Response> call = apiRest.insertUser(newUser);
                call.enqueue(new Callback<User_Response>() {
                    @Override
                    public void onResponse(Call<User_Response> call, Response<User_Response> response) {
                        Log.d("Retrofit", "Dentro de onResponse");

                        // Manejar la respuesta aquí
                        if (response.isSuccessful()) {
                            User_Response userResponse = response.body();
                            limpiarControles();
                            // Realizar acciones con los datos recibidos si es necesario
                            Toast.makeText(MRegistrarUsuario.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // Manejar errores
                            Toast.makeText(MRegistrarUsuario.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User_Response> call, Throwable t) {
                        Log.e("Retrofit", "Dentro de onFailure", t);
                        // Manejar fallos en la llamada
                        Toast.makeText(MRegistrarUsuario.this, "Error de red al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    private void limpiarControles(){
        binding.txtDNI.setText("");
        binding.txtCelular.setText("");
        binding.txtCorreo.setText("");
        binding.txtNombre.setText("");
        binding.txtNombre.requestFocus();

    }



}