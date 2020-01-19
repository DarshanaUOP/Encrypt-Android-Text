package com.example.acer.encrypt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class MainActivity extends ActionBarActivity {

    EditText plaintxt,enctxt,dectxt;
    TextView button,massage;

//    public AESHelper aESHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        aESHelper = new AESHelper();

        plaintxt = (EditText)findViewById(R.id.plaintxt);
        enctxt =(EditText)findViewById(R.id.encTxt);
        dectxt = (EditText)findViewById(R.id.decTxt);
        button = (TextView)findViewById(R.id.button);
        massage = (TextView)findViewById(R.id.massageArea);

        plaintxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                change();
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

    }

    public void change(){
        String myTxt = String.valueOf(plaintxt.getText());
        //using method two
        String encMsg = encryption(myTxt);
        String decMsg = decryption(encMsg);
        String mssg ="Enc : "+encMsg+"\n\nDec txt : "+ decMsg;

        enctxt.setText(encMsg);
        dectxt.setText(decMsg);

        massage.setText(mssg);
    }
    ///METOD TWO
    public String encryption(String strNormalText){
        String normalTextEnc="";
        try {
            normalTextEnc = AESHelper.encrypt(strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }
    public String decryption(String strEncryptedText){
        String strDecryptedText="";
        try {
            strDecryptedText = AESHelper.decrypt(strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
