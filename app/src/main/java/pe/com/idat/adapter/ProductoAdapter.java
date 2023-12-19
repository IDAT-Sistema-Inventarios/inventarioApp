package pe.com.idat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pe.com.idat.R;
import pe.com.idat.databinding.ItemProductoBinding;
import pe.com.idat.entities.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemProductoBinding binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.binding.txtNombre.setText(producto.getNombre());
        holder.binding.txtCodigoBarras.setText(producto.getCodigoBarra());
        holder.binding.txtDescripcion.setText(producto.getDescripcion());
        holder.binding.txtCantidad.setText(String.valueOf(producto.getCantidad())); // Convertir a String


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProductoBinding binding;

        public ViewHolder(@NotNull ItemProductoBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


    // Método para actualizar la lista de productos y notificar al RecyclerView
    public void actualizarListaProductos(List<Producto> nuevaLista) {
        productos.clear();
        productos.addAll(nuevaLista);
        notifyDataSetChanged();
    }


}
