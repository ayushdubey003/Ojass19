package ojass19.nitjsr.in.ojass19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide();   //To hide the app bar.
                setContentView(R.layout.activity_registration);



                //EditText handle for the Name field.Use .getText().toString() to get the user input for name
                EditText name = (EditText)findViewById(R.id.name_field);

                //EditText handle for the Email field.Use .getText().toString() to get the user input for email
                EditText email = (EditText)findViewById(R.id.email_field);

                //EditText handle for the phone field.Use .getText().toString() to get the user input for phone
                EditText phone = (EditText)findViewById(R.id.phone_field);

                //EditText handle for the College Name field.Use .getText().toString() to get the user input for college name
                EditText college_name = (EditText)findViewById(R.id.college_field);

                //EditText handle for the College Id field.Use .getText().toString() to get the user input for college id
                EditText college_id = (EditText)findViewById(R.id.college_id);

                //EditText handle for the Branch field.Use .getText().toString() to get the user input for branch
                EditText branch = (EditText)findViewById(R.id.branch_field);

                //Spinner handle for the t-shirt size field.Use .getSelectedItem().toString() to get the user input for t-shirt size
                Spinner tshirt_sizes_spinner = (Spinner) findViewById(R.id.t_shirt_sizes);

                //Button handle for the Register action.
                Button register = (Button)findViewById(R.id.register_action);

                //TextView handle for the Register action.
                TextView skip = (TextView) findViewById(R.id.skip_action);



                //Spinner for the T-shirt sizes iniatiallized
                String sizes[] = {"XS (34)", "S (36)", "M (38)", "L (40)", "XL (42)", "XXL (44)"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        R.layout.tshirt_size_spinner_item, sizes);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                tshirt_sizes_spinner.setAdapter(adapter);



                register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                //TODO: add the a node to the Firebase database for the newly registered user.
                        }
                });

                skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                //TODO: skip the registration process
                        }
                });

        }
}
