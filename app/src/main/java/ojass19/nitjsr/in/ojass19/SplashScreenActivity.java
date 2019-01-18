package ojass19.nitjsr.in.ojass19;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        float density = getResources().getDisplayMetrics().density;
        double val = 0.0;
        if (density == 0.75)
            val = -225.0;
        else if (density <= 1.0)
            val = -220.0;
        else if (density <= 1.5)
            val = -410.0;
        else if (density <= 2.0)
            val = -600.0;
        else if (density <= 3.0)
            val = -1150.0;
        else if (density <= 4.0)
            val = -1320.0;
        //Toast.makeText(this,Float.toString(density),Toast.LENGTH_LONG).show();
        final ImageView circle1 = (ImageView) findViewById(R.id.circle1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circle2);
        final ObjectAnimator animation = ObjectAnimator.ofFloat(circle2, "translationY", (float) val);
        animation.setDuration(1000).start();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                SecondAnimation();
                Button button=(Button)findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SplashScreenActivity.this,WalkthroughActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //do nothing
            }
        });
    }

    private void SecondAnimation() {
        final ImageView circle1 = (ImageView) findViewById(R.id.circle1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circle2);
        final ImageView ojass = (ImageView) findViewById(R.id.ojass);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.below_layout);
        final LinearLayout modifiedLayout = (LinearLayout) findViewById(R.id.modified_layout);
        circle1.setVisibility(View.GONE);
        circle2.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        ojass.setAlpha(0f);
        ojass.setVisibility(View.VISIBLE);
        ojass.animate()
                .alpha(1f)
                .setDuration(2000)
                .setListener(null);
        circle2.animate()
                .alpha(0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        circle2.setVisibility(View.INVISIBLE);
                    }
                });
        modifiedLayout.setAlpha(0f);
        modifiedLayout.setVisibility(View.VISIBLE);
        modifiedLayout.animate()
                .alpha(1f)
                .setDuration(2000)
                .setListener(null);
        linearLayout.animate()
                .alpha(0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        linearLayout.setVisibility(View.INVISIBLE);
                    }
                });
    }
}