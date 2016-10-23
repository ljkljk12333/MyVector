package com.nightmare.jli.myvector;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;

    CustumFAB custumFAB;


    boolean isOK=false;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(MainActivity.this);

        custumFAB = (CustumFAB)findViewById(R.id.btn_Add_Device);
        custumFAB.setImageResource(R.drawable.vector_searcher);
        custumFAB.setOnClickListener(MainActivity.this);

//        ((ImageView) findViewById(R.id.image_anim_vector)).setOnClickListener(MainActivity.this);
//        ((ImageView) findViewById(R.id.image_earth_moon_system)).setOnClickListener(MainActivity.this);
    }

    private void animate(ImageView tempImageView) {
        Drawable drawable = tempImageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void stopAnimate(ImageView tempImageView) {
        Drawable drawable = tempImageView.getDrawable();
        if (drawable instanceof Animatable) {
            if(((Animatable) drawable).isRunning()) {
                ((Animatable) drawable).stop();
            }
        }
    }
    @Override
    public void onClick(View v) {
        if(v instanceof CustumFAB) {
            stopAnimate((CustumFAB) v);
            if(!isOK){
                ((CustumFAB) v).setImageResource(R.drawable.vector_searcher);
            }else {
                ((CustumFAB) v).setImageResource(R.drawable.vector_searcher2);
            }
            animate((CustumFAB) v);
            isOK=!isOK;
        }else if(v instanceof ImageView){
            animate((ImageView) v);
        }
    }
}
