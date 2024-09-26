package com.example.actividad21;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    private ImageView imageViewReceived;
    private TextView tvNameReceived;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        imageViewReceived = findViewById(R.id.imageViewReceived);
        tvNameReceived = findViewById(R.id.tvNameReceived);
        btnShow = findViewById(R.id.btnShow);

        // Inicialmente ocultar el nombre y la imagen
        imageViewReceived.setVisibility(ImageView.INVISIBLE);
        tvNameReceived.setVisibility(TextView.INVISIBLE);

        // Recuperar los datos enviados desde el MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        byte[] byteArray = intent.getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Mostrar los datos cuando el botón "Tomar Foto" sea presionado
        btnShow.setOnClickListener(v -> {
            imageViewReceived.setImageBitmap(bmp);
            tvNameReceived.setText(name);
            imageViewReceived.setVisibility(ImageView.VISIBLE);
            tvNameReceived.setVisibility(TextView.VISIBLE);
        });
    }
}
