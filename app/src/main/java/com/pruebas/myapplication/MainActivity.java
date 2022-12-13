package com.pruebas.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pruebas.myapplication.POJO.User;
import com.pruebas.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final public String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Evento añadido desde el editor
        Button btnAdd = (Button)findViewById(R.id.btnAdd);

        Button btnCount = (Button) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), binding.tvValue.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fabEmail = (FloatingActionButton) findViewById(R.id.fabEmail);
        fabEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SecondActivity.class);
                User user = new User("Miguel", 42);
                i.putExtra("usuario", user);
                startActivity(i);
            }
        });
    }
    public void AddValue(View view){
        int value = Integer.parseInt(binding.tvValue.getText().toString());
        ++value;
        binding.tvValue.setText(String.valueOf(value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_menu_editar){
            ShowToast("Opciones");
        }
        else if(item.getItemId() == R.id.bar_menu_compartir){
            ShowToast("Compartir");
        }
        else if(item.getItemId() == R.id.bar_menu_servicios){
            ShowToast("Servicios");
        }
        else if(item.getItemId() == R.id.bar_menu_configuracion){
            ShowToast("Configuración");
        }
        return true;
    }

    public void ShowToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public void OpenDns(View view){
        Uri uriWeb = Uri.parse(binding.etDns.getText().toString());
        Intent iImplicito = new Intent(Intent.ACTION_VIEW, uriWeb);
        ComponentName name = iImplicito.resolveActivity(getPackageManager());

        if(iImplicito.resolveActivity(getPackageManager()) != null){
            startActivity(iImplicito);
        }
        else{
            ShowToast("Error al abrir el dns proporcionado");
        }
    }
    public void OpenUbicacion(View view){
        Uri uriLocation = Uri.parse("geo:0,0?q=" + binding.etUbicacion.getText().toString());
        Intent iImplicito = new Intent(Intent.ACTION_VIEW, uriLocation);

        if(iImplicito.resolveActivity(getPackageManager()) != null){
            startActivity(iImplicito);
        }
        else{
            ShowToast("Error al abrir la ubicacion proporcionada");
        }
    }
    public void OpenShare(View view){
        String txt = binding.etShare.getText().toString();
        String myme = "text/plain";

        ShareCompat.IntentBuilder builder = new ShareCompat.IntentBuilder(this);
        builder.setType(myme)
                .setChooserTitle("Titulo")
                .setText(txt)
                .startChooser();


    }
}