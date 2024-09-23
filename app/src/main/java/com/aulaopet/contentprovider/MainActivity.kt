package com.aulaopet.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    private lateinit var textview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    @SuppressLint("Range")
    fun clique(view: View) {
        var nomecontato : String
        var numerocontato : String

        textview = findViewById(R.id.textnome)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)

        }else{
            val contentResolver : ContentResolver = getContentResolver()
            val uri : Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            val cursor : Cursor = contentResolver.query(uri, null, null, null, null)!!
            Log.i("DEMO CONTENT PROVIDER", "NUMERO COUNT" + cursor.count.toString())

            if (cursor.count > 0)
            {
                while (cursor.moveToNext()){

                    nomecontato = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    numerocontato = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    //nomecontato = cursor.getString(25)

                    textview.text = "NOME CONTATO" + nomecontato + "NUMERO " + numerocontato

                    Log.i("DEMO CONTENT PROVIDER", "NOME CONTATO" + nomecontato + "NUMERO " + numerocontato)
                }
            }
        }

    }


}


