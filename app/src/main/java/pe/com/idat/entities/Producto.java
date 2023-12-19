package pe.com.idat.entities;

import androidx.room.ColumnInfo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Producto")
public class Producto {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "producto_id")
    private int producto_id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "codigoBarra")
    private String codigoBarra;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "cantidad")
    private int cantidad;

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto(@NotNull int producto_id, String nombre, String codigoBarra, String descripcion, int cantidad) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.codigoBarra = codigoBarra;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
}
