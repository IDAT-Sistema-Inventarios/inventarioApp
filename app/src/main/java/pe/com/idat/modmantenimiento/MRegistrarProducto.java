package pe.com.idat.modmantenimiento;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pe.com.idat.DataBase;
import pe.com.idat.bd.BD;
import pe.com.idat.databinding.ActivityMregistrarProductoBinding;
import pe.com.idat.entities.Producto;

public class MRegistrarProducto extends AppCompatActivity {

    private ActivityMregistrarProductoBinding binding;
    private int incremento=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMregistrarProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Evento click para la toma de foto
                camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                // Luego de lanzar la cámara, realiza la otra acción
                Accion(view);
            }
        });


        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Agregar(view);

            }
        });

        binding.btnQuitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Quitar(view);

            }
        });

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarProducto(view);
            }
        });

    }




    private void RegistrarProducto(View view){

        String producto= binding.txtProducto.getText().toString();
        String codigoBarras= binding.txtCodigoBarras.getText().toString();
        String descripcion= binding.txtDescripcion.getText().toString();
        int cantidad= Integer.valueOf(binding.txtCantidad.getText().toString());

        // Utilizar AsyncTask para realizar la operación de base de datos en un hilo separado
        new InsertarProductoTask(this,binding).execute(new Producto(
                0,
                producto,
                codigoBarras,
                descripcion,
                cantidad
        ));
    }

    private static class InsertarProductoTask extends AsyncTask<Producto, Void, Void> {
        private final Context context;
        private ActivityMregistrarProductoBinding binding;


        public InsertarProductoTask(Context context, ActivityMregistrarProductoBinding binding ) {
            this.context = context;
            this.binding = binding;
        }

        @Override
        protected Void doInBackground(Producto... productos) {
            // Realizar la operación de base de datos en un hilo separado
            BD.appDatabase.daoProducto().insertarProducto(productos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Aquí puedes realizar cualquier acción necesaria después de completar la operación
            Toast.makeText(context, "El producto se registró con éxito", Toast.LENGTH_SHORT).show();

            binding.txtProducto.setText("");
            binding.txtCodigoBarras.setText("");
            binding.txtDescripcion.setText("");
            binding.txtCantidad.setText("");

            binding.txtProducto.requestFocus();
        }
    }

    ActivityResultLauncher<Intent>camaraLauncher = registerForActivityResult(//objeto para obtener la imagen
            new ActivityResultContracts.StartActivityForResult() // clase por defecto
            , new ActivityResultCallback<ActivityResult>() {//funcion call back
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode()==RESULT_OK){ //si es ok la toma de foto
                        Bundle extras= o.getData().getExtras();//obtenemos toda la data
                        Bitmap imgbitmap =(Bitmap) extras.get("data");//obtenemos la img en formato bitmap
                        binding.ivFoto.setImageBitmap(imgbitmap);
                    }
                }
            }

    );

    private void Accion(View view){

        ViewGroup.LayoutParams layoutParams = binding.ivFoto.getLayoutParams();
        layoutParams.height = 200; // Cambia este valor según tus necesidades
        binding.ivFoto.setLayoutParams(layoutParams);

        binding.ivFoto.setVisibility(View.VISIBLE);

    }


    private void Agregar(View viewm){
        incremento++;
        binding.txtCantidad.setText(String.valueOf(incremento));

    }

    private void Quitar(View view){

        // Disminuir el conteo
        incremento--;

        // Asegurar que el conteo no sea menor que cero
        incremento = Math.max(0, incremento);

        // Actualizar el TextView
        binding.txtCantidad.setText(String.valueOf(incremento));

    }

}