package com.zantung.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    ImageButton ibtnPlay;
    int soDiem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        DisablSeekBar();
        txtDiem.setText(soDiem + "");
        final CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                sbOne.setProgress(sbOne.getProgress() +one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);
                // kiểm tra ai win
                if(sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "BLUE WIN", Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if (cbOne.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                        soDiem -= 5;
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                if (sbTwo.getProgress() >= sbTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "YELLOW WIN", Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if (cbTwo.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                        soDiem -= 5;
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                if (sbThree.getProgress() >= sbThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "RED WIN", Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if (cbThree.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                        soDiem -= 5;
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng chọn trước khi chơi!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //ràng buộc checkBox
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });
    }
    private void EnableCheckBox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void DisableCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }
    private void DisablSeekBar(){
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);
    }
    private void AnhXa(){
        txtDiem = findViewById(R.id.text);
        cbOne = findViewById(R.id.checkbox1);
        cbTwo = findViewById(R.id.checkbox2);
        cbThree = findViewById(R.id.checkbox3);
        sbOne = findViewById(R.id.seekbar1);
        sbTwo = findViewById(R.id.seekbar2);
        sbThree = findViewById(R.id.seekbar3);
        ibtnPlay = findViewById(R.id.btnplay);
    }
}