package pe.com.idat.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pe.com.idat.databinding.ItemUsuarioBinding;
import pe.com.idat.response.User_Response;

public class UsuarioAdapterApi extends RecyclerView.Adapter<UsuarioAdapterApi.ViewHolder> {

    private List<User_Response> usuarios;

    public UsuarioAdapterApi(List<User_Response> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioAdapterApi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUsuarioBinding binding = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UsuarioAdapterApi.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapterApi.ViewHolder holder, int position) {
        if (usuarios != null) {
            User_Response usuario = usuarios.get(position);
            holder.binding.txtNombre.setText(usuario.getNombres());
            holder.binding.txtDNI.setText(usuario.getDni());
            holder.binding.txtCelular.setText(usuario.getCelular());
            holder.binding.txtCorreo.setText(usuario.getCorreo()); // Convertir a String
        }
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemUsuarioBinding binding;

        public ViewHolder(@NotNull ItemUsuarioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    // Método para actualizar la lista de productos y notificar al RecyclerView
    public void actualizarListaUsuarios(List<User_Response> nuevaLista) {
        usuarios = nuevaLista;
        notifyDataSetChanged();
    }
}
