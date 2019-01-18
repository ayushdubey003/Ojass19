package ojass19.nitjsr.in.ojass19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class login_activity extends AppCompatActivity {
    ImageView google_login,facebook_login;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "LoginActivity";
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        google_login=findViewById(R.id.google_login);
        facebook_login=findViewById(R.id.facebook_login);
        Picasso.with(this).load(R.drawable.login_signinbutton).fit().into(google_login);
        Picasso.with(this).load(R.drawable.facebook_sign_in_button).fit().into(facebook_login);
        setdialog();

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("332223774851-38kjfhq2jpamde5hqsdrg8arn86cjca5.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    private void setdialog(){
        pd=new ProgressDialog(this);
        pd.setTitle("Hang On");
        pd.setMessage("Connecting You To Us");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            pd.show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
                if(pd.isShowing()) pd.dismiss();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            isRegisteredUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                            if(pd.isShowing()) pd.dismiss();
                            Toast.makeText(login_activity.this,"login failed !",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();}
    }


    private void moveToMainActivity() {
        startActivity(new Intent(login_activity.this, MainActivity.class));
        finish();
    }

    private void moveToRegisterActivity() {
        Intent intent = new Intent(login_activity.this,RegistrationActivity.class);
        startActivity(intent);
    }

    private void isRegisteredUser() {
        final String fName = mAuth.getCurrentUser().getDisplayName().split(" ")[0];
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()){
                        moveToMainActivity();
                        Toast.makeText(login_activity.this, "Welcome to Ojass Space Voyage Dashboard! "+fName, Toast.LENGTH_LONG).show();
                    } else {
                        moveToRegisterActivity();
                        Toast.makeText(login_activity.this, "Hey "+fName+"! Let us know you better.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){

                }
                //if (pd.isShowing()) pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(pd.isShowing()) pd.dismiss();
    }
}
