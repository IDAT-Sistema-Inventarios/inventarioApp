package pe.com.idat;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="user_id")
    private int user_id;

    @ColumnInfo(name="correo")
    private String correo;

    @ColumnInfo(name="password")
    private String password;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
