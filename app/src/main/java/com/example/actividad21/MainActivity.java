package com.example.actividad21;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText etName;
    private Button btnSelectImage, btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        etName = findViewById(R.id.etName);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnAccept = findViewById(R.id.btnAccept);

        // Evento para seleccionar una imagen (usando la cámara como ejemplo)
        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        });

        // Botón para aceptar y enviar la imagen y el nombre al segundo frame
        btnAccept.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Inicio.class);

            // Convertir la imagen a byte array para pasarla al otro Activity
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Pasar datos (nombre e imagen)
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("image", byteArray);

            startActivity(intent);
        });
    }

    // Recibir la imagen de la cámara y mostrarla en el ImageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
