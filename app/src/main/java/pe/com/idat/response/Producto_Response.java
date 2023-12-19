package pe.com.idat.response;

import com.google.gson.annotations.SerializedName;

public class Producto_Response {


    @SerializedName("nombre")//atributo del servicio
    private String nombre;

    @SerializedName("codigoBarra")// atributo
    private String codigoBarra;

    @SerializedName("descripcion")// atributo
    private String descripcion;

    @SerializedName("cantidad")// atributo
    private int cantidad;


}
