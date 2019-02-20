package ojass19.nitjsr.in.ojass19;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {

        private ArrayList<FAQ> questions;
        private ListView listView;
        private FaqAdapter faqAdapter;
        private ImageView back;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide();
                setContentView(R.layout.activity_faq);

                questions = new ArrayList<>();
                initFAQs();

                listView = (ListView)findViewById(R.id.list);
                faqAdapter = new FaqAdapter(this, 0, questions);
                listView.setAdapter(faqAdapter);

                back = (ImageView) findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                //TODO: Move to the home page
                        }
                });
        }

        public void initFAQs(){
                //TODO: initiallize the arraylist questions from firebase
                //adding dummy data
                questions.add(new FAQ("What is Ojass?", "Ojass is annual Techno-Management fest of NIT Jamshedpur"));
                questions.add(new FAQ("Are there any online events?", "Yes! There are plenty of online events. It includes branch events, gaming events, formal events, fun events. etc"));
                questions.add(new FAQ("How can we participate?", "Get registered through our android app or you can get registered in our campus at the time of the fest"));
                questions.add(new FAQ("What is Ojass?", "Ojass is annual Techno-Management fest of NIT Jamshedpur"));
                questions.add(new FAQ("Are there any online events?", "Yes! There are plenty of online events. It includes branch events, gaming events, formal events, fun events. etc"));
                questions.add(new FAQ("How can we participate?", "Get registered through our android app or you can get registered in our campus at the time of the fest"));
        }

        public class FaqAdapter extends ArrayAdapter<FAQ>{

                public FaqAdapter(@NonNull Context context, int resource, ArrayList<FAQ> questions) {
                        super(context, resource, questions);
                }

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if(convertView == null){
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.faq_item,
                                        null, false);
                        }

                        ((TextView)convertView.findViewById(R.id.question)).setText(getItem(position).
                                getQuestion());
                        ((TextView)convertView.findViewById(R.id.answer)).setText(getItem(position).
                                getAnswer());

                        final View faq_view = convertView;
                        convertView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        TextView answer = (TextView)faq_view.findViewById(R.id.answer);
                                        LinearLayout faq = (LinearLayout)faq_view.findViewById(R.id.faq);
                                        if(answer.getVisibility() == View.GONE){
                                                faq.setLayoutTransition(new LayoutTransition());
                                                answer.setVisibility(View.VISIBLE);
                                                answer.setAlpha(0.0f);
                                                answer.animate().alpha(1.0f);
                                        }
                                        else {
                                                faq.setLayoutTransition(null);
                                                answer.setVisibility(View.GONE);
                                        }
                                }
                        });

                        return convertView;
                }
        }
}
