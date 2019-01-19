package ojass19.nitjsr.in.ojass19;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

public class WalkthroughActivity extends AppCompatActivity {

        // a constant variable to store the number of walkthrough pages
        final int PAGES = 3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide(); //To hide the app bar
                setContentView(R.layout.activity_walkthrough);

                final ViewPager viewPager = (ViewPager) findViewById(R.id.tutorial_pager);
                final Button button = (Button) findViewById(R.id.button);

                //fragment adapter created
                MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

                viewPager.setAdapter(adapter);
                CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);
                //the circular indicator is setup with the view pager
                indicator.setViewPager(viewPager);

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {
                                //DO Nothing
                        }

                        @Override
                        public void onPageSelected(int i) {
                                if(i == PAGES - 1)
                                        button.setText("Sign Up");
                                else
                                        button.setText("Next");
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {
                                //Do Nothing
                        }
                });

                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                int current_page = viewPager.getCurrentItem();
                                if(current_page < PAGES - 1)
                                        viewPager.setCurrentItem(current_page + 1, true);
                                else{
                                        //TODO: Make the user sign up through Google or Facebook here
                                        Intent intent = new Intent(WalkthroughActivity.this,login_activity.class);
                                        startActivity(intent);
                                        finish();
                                }
                        }
                });

        }

        public class MyFragmentAdapter extends FragmentPagerAdapter{

                public MyFragmentAdapter(FragmentManager fm) {
                        super(fm);
                }

                @Override
                public Fragment getItem(int i) {
                        if(i == 0)
                                return First_Walkthrough_Fragment.newInstance();
                        else if(i == 1)
                                return Second_Walkthrough_Fragment.newInstance();
                        else
                                return Third_Walkthrough_Fragment.newInstance();
                }

                @Override
                public int getCount() {
                        return PAGES;
                }
        }
}
