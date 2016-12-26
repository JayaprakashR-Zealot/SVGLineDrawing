package com.truedreamz.svglinedrawing;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.truedreamz.svglinedrawing.LineDrawing.SVGPathView;

import static com.truedreamz.svglinedrawing.R.id.pathView;

public class SVGDrawingActivity extends AppCompatActivity {
    SVGPathView pathView;
    TextView txtViewLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgdrawing);

        pathView= (SVGPathView)findViewById(R.id.pathView);
        txtViewLoading =(TextView) findViewById(R.id.txtLoadingStatus);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawSVGDrawing();
            }
        },2000);

        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewLoading.setText("Line drawing starts...");
                drawSVGDrawing();
            }
        });
    }

    private void drawSVGDrawing(){
        pathView.getSequentialPathAnimator().
                delay(100).
                duration(8000).
                interpolator(new AccelerateDecelerateInterpolator()).
                listenerEnd(new SVGPathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        txtViewLoading.setText("Line drawing completed.");
                        Toast.makeText(SVGDrawingActivity.this,"Line drawing is done.",Toast.LENGTH_LONG).show();
                    }
                }).start();
    }
}
