package pe.com.idat;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pe.com.idat.dao.ProductoDAO;
import pe.com.idat.entities.Producto;

@Database(
        entities ={Producto.class},version =1
)
public abstract class DataBase extends RoomDatabase {

    public abstract ProductoDAO daoProducto();


}
