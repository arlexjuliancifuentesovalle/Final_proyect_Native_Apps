package co.edu.ue.finalproject.presentation.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.edu.ue.finalproject.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Al iniciar, cargar por defecto el fragmento de pagos
        if (savedInstanceState == null) {
            loadFragment(new PagosFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment fragment = null;

            if (itemId == R.id.menuPagos) {
                fragment = new PagosFragment();
            } else if (itemId == R.id.menuTurnos) {
                fragment = new TurnoFragment();
            } else if (itemId == R.id.menuClientes) {
                // fragment = new ClientesFragment();
                return true;
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
