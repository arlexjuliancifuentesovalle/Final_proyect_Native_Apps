package co.edu.ue.finalproject.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.ue.finalproject.R;

public class NoRedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_red);

        Button btnReintentar = findViewById(R.id.btnReintentar);

        btnReintentar.setOnClickListener(v -> {

            if (hayInternet()) {

                // Si ya hay internet volvemos a la pantalla principal
                Intent intent = new Intent(NoRedActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(NoRedActivity.this,
                        "Aún no hay conexión a internet",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    private boolean hayInternet() {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}