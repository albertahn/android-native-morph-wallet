package com.neman.morph.morphwallet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.neman.morph.morphwallet.R;
import com.neman.morph.morphwallet.Utils.barcode.BarcodeCaptureActivity;

/**
 * Created by albert on 3/8/18.
 */

public class SuccessPayment  extends AppCompatActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_success_payment);


            Button backhome = findViewById(R.id.back_home);

            backhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    finish();

                }//clik
            });

        }




    }
