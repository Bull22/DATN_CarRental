package com.example.datncarrental;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datncarrental.Utils.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "ERROR";
    EditText reName;
    EditText reEmail;
    EditText reDadeofBirth;
    EditText rePhonenumber;
    EditText rePassword;
    EditText rePasswordcheck;
    Button btnsignup;
    FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        reName = findViewById(R.id.edt_user_name_signup);
        reEmail = findViewById(R.id.edt_email_signup);
        reDadeofBirth = findViewById(R.id.edt_birthday);
        rePhonenumber = findViewById(R.id.edt_phone_number);
        rePassword = findViewById(R.id.edt_password_signup);
        rePasswordcheck = findViewById(R.id.edt_password_signup_confirm);
        btnsignup = findViewById(R.id.btn_sign_up);
        reDadeofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                openCalendar();
            }
        });
//        btnsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDatabase = FirebaseDatabase.getInstance();
//                databaseReference = mDatabase.getReference("User");
//                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .setValue("Data");
//            }
//        });

        btnsignup.setOnClickListener(view -> {
            if (validateFields()) {
                String Name = reName.getText().toString();
                String Email = reEmail.getText().toString();
                String PhoneNumber = rePhonenumber.getText().toString();
                String Password = rePassword.getText().toString();
                String Dob = reDadeofBirth.getText().toString();
                User user = new User(Name, Email, PhoneNumber, Dob, Password);


                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    openactivity_signin(Email, Password);
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    databaseReference.push().setValue(user);
                                    Toast.makeText(getApplicationContext(), "????ng k?? th??nh c??ng!!", Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences("AutoLogin", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("UserName", Email);
                                    editor.putString("Password", Password);
                                    editor.apply();

                                } else {
//                                    Toast.makeText(getApplicationContext(), "Fail Registered...", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(SignUpActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    private void openactivity_signin(String email, String password) {
        startActivityForResult(new Intent(SignUpActivity.this, LoginActivity.class)
                .putExtra("email", email)
                .putExtra("pass", password)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK), Common.REQUEST_CODE_SIGN_UP);
    }

    private boolean validateFields() {
        return validateUsername() && validateEmail() && validateDateofbirth() && validatePhoneNumber() && validatePassword() && validatePasswordcheck();
    }

    private boolean validateUsername() {
        String val = reName.getText().toString().trim();
        String noWhiteSpace = "(?=\\S+$)";

        Pattern whitespace = Pattern.compile("\\s\\s");
        Matcher matcher = whitespace.matcher(val);

        if (val.isEmpty()) {
            reName.setError("Kh??ng ???????c ????? tr???ng");
            reName.requestFocus();
            return false;
        } else {
            reName.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        String Email = reEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (Email.isEmpty()) {
            reEmail.setError("C???n ??i???n Email");
            reEmail.requestFocus();
            return false;
        } else if (!Email.matches(emailPattern)) {
            reEmail.setError("Email kh??ng h???p l???");
            reEmail.requestFocus();
            return false;
        } else {
            reEmail.setError(null);
            return true;
        }

    }

    private boolean validatePhoneNumber() {
        String PhoneNo = rePhonenumber.getText().toString().trim();
        if (PhoneNo.isEmpty()) {
            rePhonenumber.setError("C???n ??i???n ????? th??ng tin s??? ??i???n tho???i");
            rePhonenumber.requestFocus();
            return false;
        } else if (PhoneNo.length() != 10) {
            rePhonenumber.setError("s??? ??i???n tho???i kh??ng h???p l???!!");
            rePhonenumber.requestFocus();
            return false;
        } else {
            rePhonenumber.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String Password = rePassword.getText().toString().trim();
        if (Password.isEmpty()) {
            rePassword.setError("b???n ch??a nh???p m???t kh???u");
            rePassword.requestFocus();
            return false;
        } else if (Password.length() < 6) {
            rePassword.setError("M???t kh???u ph???i h??n 6 k?? t???");
            rePassword.requestFocus();
            return false;
        } else {
            rePassword.setError(null);
            return true;
        }
    }

    private boolean validatePasswordcheck() {
        String Password = rePassword.getText().toString().trim();
        String PasswordCheck = rePasswordcheck.getText().toString().trim();
        if (PasswordCheck.isEmpty()) {
            rePasswordcheck.setError("b???n ch??a nh???p m???t kh???u");
            rePasswordcheck.requestFocus();
            return false;
        } else if (!PasswordCheck.equals(Password)) {
            rePasswordcheck.setError("X??c nh???n m???t kh???u kh??ng ????ng");
            rePasswordcheck.requestFocus();
            return false;
        } else {
            rePasswordcheck.setError(null);
            return true;
        }
    }

    private boolean validateDateofbirth() {
        String Dob = reDadeofBirth.getText().toString();
        if (Dob.isEmpty()) {
            reDadeofBirth.setError("Vui l??ng ch???n ng??y th??ng n??m sinh");
            reDadeofBirth.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void openCalendar() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                reDadeofBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, 2000, 1, 1);

        // Show
        datePickerDialog.show();
    }
}
