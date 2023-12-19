package pe.com.idat.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import pe.com.idat.entities.Producto;
@Dao
public interface ProductoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarProducto(Producto producto);

    @Query("SELECT * FROM Producto WHERE producto_id = :productId")
    Producto getUserById(int productId);

    @Query("SELECT * FROM Producto")
    List<Producto> obtenerProductos();

    @Query("DELETE FROM Producto")
    void eliminarProducto();

}
