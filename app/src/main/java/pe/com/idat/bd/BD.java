package pe.com.idat.bd;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import pe.com.idat.DataBase;
//import pe.com.idat.SyncService;

public class BD extends Application {
    public static DataBase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar Room
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "my-database").build();
 }

}
