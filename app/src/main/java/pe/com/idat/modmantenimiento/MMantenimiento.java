package pe.com.idat.modmantenimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.com.idat.R;
import pe.com.idat.databinding.ActivityMmantenimientoBinding;
import pe.com.idat.databinding.ActivityMregistrarProductoBinding;

public class MMantenimiento extends AppCompatActivity {

    private ActivityMmantenimientoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMmantenimientoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cvProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarListarProducto(view);
            }
        });

        binding.cvUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarListarUsuario(view);
            }
        });



    }

    private void mostrarListarProducto(View view){

        Intent intent= new Intent(this, MListarProducto.class);
        startActivity(intent);

    }

    private void mostrarListarUsuario(View view){

        Intent intent= new Intent(this, MListarUsuario.class);
        startActivity(intent);

    }



}