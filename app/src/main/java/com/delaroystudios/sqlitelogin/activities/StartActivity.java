package com.delaroystudios.sqlitelogin.activities;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.delaroystudios.sqlitelogin.R;
import com.delaroystudios.sqlitelogin.fragments.DebtFragment;
import com.delaroystudios.sqlitelogin.fragments.ProductsFragment;
import com.delaroystudios.sqlitelogin.fragments.ShopFragment;
import com.delaroystudios.sqlitelogin.fragments.UsersFragment;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hview = navigationView.getHeaderView(0);
        TextView name = (TextView) hview.findViewById(R.id.textViewNombre);
        TextView correo = (TextView) hview.findViewById(R.id.textViewCorreo);

        String nombre = getIntent().getStringExtra("NAME");
        String email = getIntent().getStringExtra("EMAIL");

        name.setText(nombre);
        correo.setText( email);

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        loadFragment(new ShopFragment());
        getSupportActionBar().setTitle("Venta");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int id = item.getItemId();

        if (id == R.id.nav_ventas) {
            loadFragment(new ShopFragment());
            getSupportActionBar().setTitle("Venta");
        } else if (id == R.id.nav_inventario) {
            loadFragment(new ProductsFragment());
            getSupportActionBar().setTitle("Inventario");
        } else if (id == R.id.nav_usuarios) {
            loadFragment(new UsersFragment());
            getSupportActionBar().setTitle("Usuarios");
        } else if (id == R.id.nav_debt) {
            loadFragment(new DebtFragment());
            getSupportActionBar().setTitle("Debedores");
        } else if (id == R.id.nav_salir) {
            //codigo de cerrar APP y borrado de txt
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        return true;
    }

    private void loadFragment(Fragment frag)
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, frag).commit();
    }
}
