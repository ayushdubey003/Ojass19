package ojass19.nitjsr.in.ojass19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView hello_text;
    FirebaseUser muser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout_button);
        hello_text=findViewById(R.id.hello_text);
        muser=FirebaseAuth.getInstance().getCurrentUser();
        hello_text.setText("Hello "+muser.getDisplayName()+" !");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,login_activity.class));
                finish();
            }
        });

        //startActivity(new Intent(this, WalkthroughActivity.class));
    }
}
