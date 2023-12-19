package pe.com.idat.modmantenimiento;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.IOException;
import java.util.List;

import pe.com.idat.ApiRest;
import pe.com.idat.adapter.UsuarioAdapterApi;
import pe.com.idat.databinding.ActivityMlistarUsuarioBinding;
import pe.com.idat.response.User_Response;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MListarUsuario extends AppCompatActivity {

    private ActivityMlistarUsuarioBinding binding;
    private UsuarioAdapterApi usuarioAdapter;
    private ApiRest apiRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMlistarUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvUsuario.setLayoutManager(new LinearLayoutManager(this));

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.16:8089/ModConfig/") // Reemplaza con la URL base de tu servicio
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de la interfaz de la API
        apiRest = retrofit.create(ApiRest.class);

        // Inicializar y configurar el adaptador con una lista vacía (puedes cambiarlo según tu lógica)
        usuarioAdapter = new UsuarioAdapterApi(null);
        binding.rvUsuario.setAdapter(usuarioAdapter);

        binding.btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarRegistrarUsuario(view);
            }
        });

        // Llamar a la tarea asincrónica para cargar la lista de usuarios
        new ListarUsuariosTask().execute();
    }

    private void mostrarRegistrarUsuario(View view) {
        Intent intent = new Intent(this, MRegistrarUsuario.class);
        startActivity(intent);
    }

    // Tarea asincrónica para listar usuarios
    private class ListarUsuariosTask extends AsyncTask<Void, Void, List<User_Response>> {

        @Override
        protected List<User_Response> doInBackground(Void... voids) {
            try {
                // Verificar la conexión a Internet aquí si es necesario

                // Realizar la llamada a la API para obtener la lista de usuarios
                Call<List<User_Response>> call = apiRest.listarUsuarios();
                Response<List<User_Response>> response = call.execute();

                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    // Manejar el caso de error según sea necesario
                    // Por ejemplo, puedes mostrar un mensaje de error o registrar el error
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar la excepción según sea necesario
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User_Response> listaDeUsuarios) {
            super.onPostExecute(listaDeUsuarios);
            if (listaDeUsuarios != null) {
                // Actualizar el adaptador con la lista de usuarios
                usuarioAdapter.actualizarListaUsuarios(listaDeUsuarios);
            } else {
                // Manejar el caso de error o falta de conexión aquí
                Toast.makeText(MListarUsuario.this, "Error al cargar la lista de usuarios", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
