package com.hindu.dgniryat.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.integration.android.IntentIntegrator
import com.hindu.dgniryat.R
import java.io.IOException
import java.nio.file.spi.FileTypeDetector

class BarcodeScan : AppCompatActivity() {

    private lateinit var bardata:TextView
    private lateinit var barcodeDetector: BarcodeDetector
    private lateinit var cameraSource:CameraSource
    private lateinit var surfaceView:SurfaceView

    private lateinit var scanButton: Button
    var intentData = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scan)

        bardata = findViewById(R.id.bardata)
        surfaceView = findViewById(R.id.surfaceView)
        scanButton = findViewById(R.id.scanButton)



        iniBC()
        scanButton.setOnClickListener {
            val intent = Intent(this,AdminPortal::class.java)
            intent.putExtra("orderId", bardata.text.toString())
            startActivity(intent)
        }
    }

    private fun iniBC(){
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        cameraSource = CameraSource.Builder(this,barcodeDetector)
            .setRequestedPreviewSize(1920,1080)
            .setAutoFocusEnabled(true)
            .build()

        surfaceView.holder.addCallback(object :SurfaceHolder.Callback{
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(p0: SurfaceHolder) {
                try {
                    cameraSource.start(surfaceView.holder)

                }catch (e:IOException){
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                cameraSource.stop()
            }

        })
        barcodeDetector.setProcessor(object :Detector.Processor<Barcode>{
            override fun release() {
                Toast.makeText(applicationContext,"Scanner Stopped", Toast.LENGTH_SHORT).show()
            }

            override fun receiveDetections(p0: Detector.Detections<Barcode>) {
                val barcodes = p0.detectedItems
                if (barcodes.size() !=0){
                    bardata.post {
                        intentData = barcodes.valueAt(0).displayValue
                        bardata.text = intentData
                    }
                }
            }

        })
    }

}