package pe.com.idat.modmantenimiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pe.com.idat.R;
import pe.com.idat.adapter.ProductoAdapter;
import pe.com.idat.bd.BD;
import pe.com.idat.databinding.ActivityMlistarProductoBinding;
import pe.com.idat.entities.Producto;

public class MListarProducto extends AppCompatActivity {
    private ActivityMlistarProductoBinding binding;
    private ProductoAdapter productoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMlistarProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializa el RecyclerView
        binding.rvProductos.setLayoutManager(new LinearLayoutManager(this));

        // Obtén la lista de productos (puedes usar tu método obtenerProductos)
        //List<Producto> listaProductos = // Obtén la lista de productos

        // Crea un adaptador y configúralo en el RecyclerView
        productoAdapter = new ProductoAdapter(new ArrayList<>());
        binding.rvProductos.setAdapter(productoAdapter);

        obtenerListaDeProductosDesdeRoom();

        binding.btnRegistrarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarRegistrarProducto(view);
            }
        });

    }

    private void mostrarRegistrarProducto(View view){

        Intent intent= new Intent(this, MRegistrarProducto.class);
        startActivity(intent);

    }


    // Método para obtener la lista de productos desde Room
    private void obtenerListaDeProductosDesdeRoom() {
        // Utiliza AsyncTask o hilos/threads para realizar operaciones en segundo plano
        // y no bloquear el hilo principal

        AsyncTask.execute(() -> {
            // Obtiene la lista de productos desde Room
            List<Producto> listaProductos = BD.appDatabase.daoProducto().obtenerProductos();

            // Actualiza el RecyclerView en el hilo principal
            runOnUiThread(() -> productoAdapter.actualizarListaProductos(listaProductos));
        });
    }


}