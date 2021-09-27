package com.example.project_studentmanagement_android.View.Login_ForgetPass;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_studentmanagement_android.MainActivity;
import com.example.project_studentmanagement_android.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class ForgetPassword extends AppCompatActivity {

    // nếu code gửi fail, sử dụng tính năng resend
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    // hàm để nhận biết code đã đc gửi hay chưa
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private String mVerificationId; // giữ OTP/ verify OTP

    // Kết nối với firebase gọi ra để dùng
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "MAIN_TAG";

    // progres dialog cái này để hiển thị cái màn hình chờ gửi code
    private ProgressDialog pd;
    Button btn;
    EditText phoneEt;
    String user;
    ImageButton imgreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        firebaseAuth = FirebaseAuth.getInstance();
        btn = (Button) findViewById(R.id.ForgetPass_btn);
        phoneEt = (EditText) findViewById(R.id.phoneEt);
        imgreturn = (ImageButton) findViewById((R.id.ImgGoBack));
        user = getIntent().getStringExtra("username");


        // trong progress dialog
        pd = new ProgressDialog(this);
        pd.setTitle("Please wait....");
        pd.setCanceledOnTouchOutside(false);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            // verify thành công
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }


            // verify cái code bị sai
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                pd.dismiss();
                Toast.makeText(ForgetPassword.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            // Cái này sau khi nó chạy xong cái màn hình dialog và gửi code tới đth
            // thì ẩn cái phoneLl đi hiện cái codeLl lên
            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);
                Log.d(TAG, "onCodeSent: " + verificationId);
                //mVerificationId = verificationId;
                forceResendingToken = token;
                pd.dismiss();

                Intent intent = new Intent(getApplicationContext(), VerifyCode.class);
                intent.putExtra("phone", phoneEt.getText().toString());
                intent.putExtra("verificationId", verificationId);
                intent.putExtra("username", user);
                startActivity(intent);
                finish();

                Toast.makeText(ForgetPassword.this, "Verification code sent.....", Toast.LENGTH_SHORT).show();
                //binding.codeSentDeCription.setText("Please type the verification code we sent... \n" + binding.phoneEt.getText().toString().trim());
            }

        };

        imgreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ForgetPassword.this, "Please enter phone number ...", Toast.LENGTH_SHORT).show();
                } else {
                    startPhoneNumberVerification(phone);
                }
            }
        });
    }


    // Hàm có tác dụng gửi mã xác thực
    private void startPhoneNumberVerification(String phone) {
        pd.setMessage("Verifying Phone Number");
        pd.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(ForgetPassword.this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    // Hàm verify cái code nhận được
    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        pd.setMessage("Verifying code");
        pd.show();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    // hàm này để kiểm tra cái code và login vào
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging in");

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Sucessfully
                        pd.dismiss();
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(ForgetPassword.this, "Logged In as", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // failure
                        pd.dismiss();
                        Toast.makeText(ForgetPassword.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}