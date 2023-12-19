package pe.com.idat;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pe.com.idat.response.User_Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiRest {

    @POST("/ModConfig/empleado")
    Call<User_Response> insertUser(@Body User_Response user);

    @GET("/ModConfig/verPersonal")
    Call<List<User_Response>> listarUsuarios();
}
