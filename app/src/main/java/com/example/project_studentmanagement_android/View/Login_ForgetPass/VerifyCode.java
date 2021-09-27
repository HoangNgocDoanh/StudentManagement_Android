package com.example.project_studentmanagement_android.View.Login_ForgetPass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.MainActivity;
import com.example.project_studentmanagement_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyCode extends AppCompatActivity {

    // nếu code gửi fail, sử dụng tính năng resend
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    // hàm để nhận biết code đã đc gửi hay chưa
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private String mVerificationId; // giữ OTP/ verify OTP

    // Kết nối với firebase gọi ra để dùng
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "MAIN_TAG";

    private ProgressDialog pd;
    Button btnVerifyOTP;
    EditText code1;
    EditText code2;
    EditText code3;
    EditText code4;
    EditText code5;
    EditText code6;
    ImageButton imgreturn;
    String user;
    TextView tvResend;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        Anhxa();

        // trong progress dialog
        pd = new ProgressDialog(this);
        pd.setTitle("Please wait....");
        pd.setCanceledOnTouchOutside(false);

        //Resend
        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = getIntent().getStringExtra("phone");
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(VerifyCode.this,"Please enter phone number ...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    resendPhoneNumberVerification(phone, forceResendingToken);
                }
            }

        });

        imgreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);
                finish();
            }
        });

        //verifyCode
        btnVerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no1 = code1.getText().toString().trim();
                String no2 = code2.getText().toString().trim();
                String no3 = code3.getText().toString().trim();
                String no4 = code4.getText().toString().trim();
                String no5 = code5.getText().toString().trim();
                String no6 = code6.getText().toString().trim();

                String code = code1.getText().toString()+ code2.getText().toString()+ code3.getText().toString() + code4.getText().toString()
                        + code5.getText().toString() + code6.getText().toString();

                if(TextUtils.isEmpty(code)){
                    Toast.makeText(VerifyCode.this,"Please enter verification code ...", Toast.LENGTH_SHORT).show();
                }
                else
                {

                   // verifyPhoneNumberWithCode(mVerificationId, code);
                    if (mVerificationId != null)
                    {
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,code);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(),ChangePass.class);
                                            intent.putExtra("username",user);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(VerifyCode.this, "Fal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }
            }
        });




        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



    private void Anhxa() {
        btnVerifyOTP = (Button) findViewById(R.id.btnVerifyOTP);
        code1 = (EditText) findViewById(R.id.inputCode1);
        code2 = (EditText) findViewById(R.id.inputCode2);
        code3 = (EditText) findViewById(R.id.inputCode3);
        code4 = (EditText) findViewById(R.id.inputCode4);
        code5 = (EditText) findViewById(R.id.inputCode5);
        code6 = (EditText) findViewById(R.id.inputCode6);
        imgreturn = (ImageButton) findViewById(R.id.ImgGoBack);

        mVerificationId = getIntent().getStringExtra("verificationId");
        user = getIntent().getStringExtra("username");
        tvResend = (TextView) findViewById(R.id.tvResend);
    }


    // hàm resend code khi chưa nhận được
    private void resendPhoneNumberVerification(String phone, PhoneAuthProvider.ForceResendingToken token) {
        pd.setMessage("Resending code");
        pd.show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(VerifyCode.this)
                        .setCallbacks(mCallbacks)
                        .setForceResendingToken(token)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // Hàm verify cái code nhận được
//    private void verifyPhoneNumberWithCode(String verificationId, String code) {
//        pd.setMessage("Verifying code");
//        pd.show();
//
//        //verificationId = getIntent().getStringExtra("verificationId");
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//        signInWithPhoneAuthCredential(credential);
//    }

    // hàm này để kiểm tra cái code và login vào
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        pd.setMessage("Logging in");
//
//        firebaseAuth.signInWithCredential(credential)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        //Sucessfully
//                        pd.dismiss();
//                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
//                        Toast.makeText(VerifyCode.this, "Logged In as", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // failure
//                        pd.dismiss();
//                        Toast.makeText(VerifyCode.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}