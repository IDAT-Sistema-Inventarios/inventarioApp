package pe.com.idat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.DialogInterface;//para los dialogos
import androidx.appcompat.app.AlertDialog;//para los dialogos

import com.google.android.material.navigation.NavigationView;

import pe.com.idat.modmantenimiento.MMantenimiento;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables para referenciar vistas
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*-------------Hooks--------------*/

        // Obtener referencias a las vistas mediante sus identificadores
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*-------------Tool Bar--------------*/

        // Configurar la barra de herramientas como la barra de soporte
        setSupportActionBar(toolbar);

        /*-------------Navigation Drawer Menu--------------*/

        // Ocultar o mostrar elementos en el menú de navegación
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);

        // Hacer que el menú de navegación esté al frente de otras vistas
        navigationView.bringToFront();

        // Configurar el interruptor de la barra de acción para el menú de navegación
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configurar el escucha de selección de elementos en el menú de navegación
        navigationView.setNavigationItemSelectedListener(this);

        // Establecer el elemento de inicio como seleccionado por defecto
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {

        // Cerrar el menú de navegación si está abierto al presionar el botón de retroceso
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Manejar la selección de elementos en el menú de navegación
        int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_home) {
            // Manejar el elemento de inicio
        } else if (itemId == R.id.nav_mantenimiento) {
            // Mostrar un mensaje de tostada para el elemento de mantenimiento

            Intent intent= new Intent(this, MMantenimiento.class);
            startActivity(intent);
            Toast.makeText(this, "mantenimiento", Toast.LENGTH_SHORT).show();
        }

        // Cerrar el menú de navegación después de la selección
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
