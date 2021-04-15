package com.ruhul.qrattendencesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class Scan_id_Qrcode extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_id__qrcode);
        //fayej create this class-------------

         scannerView= findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);




        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {


                        mCodeScanner.setDecodeCallback(new DecodeCallback() {
                            @Override
                            public void onDecoded(@NonNull final Result result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Scan_id_Qrcode.this, result.getText(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response)
                    {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }

                }).check();


        if (ContextCompat.checkSelfPermission(Scan_id_Qrcode.this,Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCodeScanner.startPreview();
                }
            });

        }





    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(Scan_id_Qrcode.this,Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            mCodeScanner.startPreview();

        }
        else {
            Toast.makeText(this, "permission need,allow permission", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}