package pe.com.idat.response;

import com.google.gson.annotations.SerializedName;

public class User_Response {

    @SerializedName("nombres")//atributo del servicio
    private String nombres;

    @SerializedName("dni")// atributo
    private String dni;
    @SerializedName("celular")// atributo
    private String celular;
    @SerializedName("correo")// atributo
    private String correo;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public User_Response(String nombres, String dni, String celular, String correo) {
        super();
        this.nombres = nombres;
        this.dni = dni;
        this.celular = celular;
        this.correo = correo;
    }
}
