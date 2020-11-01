package com.mariotm.ddamq;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.mariotm.ddamq.clases.ConexionSQLite;
import com.mariotm.ddamq.fragments.AgregarCategoriaFragment;
import com.mariotm.ddamq.fragments.CategoriasFragment;
import com.mariotm.ddamq.fragments.ClientesFragment;
import com.mariotm.ddamq.fragments.DashboardFragment;
import com.mariotm.ddamq.fragments.ProductosFragment;
import com.mariotm.ddamq.fragments.UsuariosFragment;
import com.mariotm.ddamq.fragments.VentasFragment;
import com.mariotm.ddamq.interfaces.ComunicaFragments;
import com.mariotm.ddamq.clases.Utilidades;

public class MainActivity extends AppCompatActivity implements ComunicaFragments,
        DashboardFragment.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    Fragment fragmentDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utilidades.consultarListaCategorias(this);

        fragmentDashboard = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentDashboard).commit();

        ConexionSQLite conectar = new ConexionSQLite(this, Utilidades.BASE_DATOS,
                null, 1);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setupDrawerContent(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selecItemDrawer(MenuItem item) {
        Fragment miFragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.dashboard:
                fragmentClass = DashboardFragment.class;
                break;
            case R.id.usuarios:
                fragmentClass = UsuariosFragment.class;
                break;
            case R.id.categorias:
                fragmentClass = CategoriasFragment.class;
                break;
            case R.id.productos:
                fragmentClass = ProductosFragment.class;
                break;
            case R.id.clientes:
                fragmentClass = ClientesFragment.class;
                break;
            case R.id.ventas:
                fragmentClass = VentasFragment.class;
                break;
            default:
                fragmentClass = DashboardFragment.class;
                break;
        }

        try {
            miFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, miFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selecItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public void categorias() {
        //Toast.makeText(getApplicationContext(), "Agregar categorias", Toast.LENGTH_SHORT).show();
        Fragment fragmentAgregarCategoria = new AgregarCategoriaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentAgregarCategoria).commit();

    }

    @Override
    public void agregarCategorias() {

    }


}