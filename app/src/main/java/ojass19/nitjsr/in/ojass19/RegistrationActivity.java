package ojass19.nitjsr.in.ojass19;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

        private Spinner tshirt_sizes_spinner;
        private Button registerButton, verifyButton;
        private TextView skipButton;
        private TextInputLayout name_wrapper,email_wrapper,mobile_wrapper,college_wrapper,collegeid_wrapper
                ,branch_wrapper;
        private EditText inputName,inputEmail,inputMobile,inputCollege,inputRegId,inputBranch;
        private String tshirtSize;
        private FirebaseUser mUser;
        private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide();   //To hide the app bar.
                setContentView(R.layout.activity_registration);
                init();
                startspinner();
                mUser= FirebaseAuth.getInstance().getCurrentUser();

                inputName.setText(mUser.getDisplayName());
                inputEmail.setText(mUser.getEmail());
                inputMobile.setText(mUser.getPhoneNumber());

                registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if(validate()) {

                                        registerUser();
                                }
                                else {
                                        Toast.makeText(RegistrationActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                skipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        finish();
                    }
                });
        }

        private void registerUser() {
                userRef = userRef.child(mUser.getUid());
                userRef.child("email").setValue(mUser.getEmail());
                userRef.child("name").setValue(mUser.getDisplayName());
                userRef.child("photo").setValue(mUser.getPhotoUrl().toString());
                userRef.child("mobile").setValue(inputMobile.getText().toString());
                userRef.child("college").setValue(inputCollege.getText().toString());
                userRef.child("college_id").setValue(inputRegId.getText().toString());
                userRef.child("branch").setValue(inputBranch.getText().toString());
                userRef.child("tshirt_size").setValue(tshirtSize);
                Toast.makeText(this, "Welcome to Ojass Space Voyage Dashboard!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                finish();
        }

        public boolean validate(){

                boolean valid=true;
                if(inputEmail.getText().toString().trim().isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString().trim()).matches())
                {
                        inputEmail.setError("Please Enter Valid Email Address");
                        valid=false;
                }
                if(inputMobile.getText().toString().trim().isEmpty()||!Patterns.PHONE.matcher(inputMobile.getText().toString().trim()).matches() )
                {
                        inputMobile.setError("Please Enter Valid Mobile Number");
                        valid=false;
                }

                if(inputName.getText().toString().trim().isEmpty() )
                {
                        inputName.setError("Please Enter Your Name");
                        valid=false;
                }

                if(inputCollege.getText().toString().trim().isEmpty() )
                {
                        inputCollege.setError("Please Enter Your College");
                        valid=false;
                }

                if(inputRegId.getText().toString().trim().isEmpty() )
                {
                        inputRegId.setError("Please Enter Your RegId");
                        valid=false;
                }

                if(inputBranch.getText().toString().trim().isEmpty() )
                {
                        inputBranch.setError("Please Enter Your Branch");
                        valid=false;
                }

                return valid;
        }

        public void init(){
                skipButton = findViewById(R.id.btn_skip_register);
                inputName = findViewById(R.id.input_name);
                inputEmail = findViewById(R.id.input_email);
                inputMobile = findViewById(R.id.input_mobile);
                inputCollege = findViewById(R.id.input_college);
                inputRegId = findViewById(R.id.input_regid);
                inputBranch = findViewById(R.id.input_branch);
                registerButton = findViewById(R.id.register_button);
                tshirt_sizes_spinner = (Spinner) findViewById(R.id.t_shirt_sizes);

//                initialising the text input layouts (wrappers)

//                name_wrapper=findViewById(R.id.login_name_wrapper);
//                email_wrapper=findViewById(R.id.login_email_wrapper);
//                mobile_wrapper=findViewById(R.id.login_mobile_wrapper);
//                college_wrapper=findViewById(R.id.login_college_wrapper);
//                collegeid_wrapper=findViewById(R.id.login_collegeid_wrapper);
//                branch_wrapper=findViewById(R.id.login_branch_wrapper);
        }
        private void startspinner(){
                //Spinner for the T-shirt sizes iniatiallized
                String sizes[] = {"XS (34)", "S (36)", "M (38)", "L (40)", "XL (42)", "XXL (44)"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        R.layout.tshirt_size_spinner_item, sizes);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                tshirt_sizes_spinner.setAdapter(adapter);

        }
}
