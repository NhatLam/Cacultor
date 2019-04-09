package com.example.calcuclator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final DecimalFormat df = new DecimalFormat("###.#######");
    String display="";
    TextView tvPhuongthuc, tvKetqua;
    public ArrayList<String> arrOperation;
    public ArrayList<Double> arrNumber;
    private ResultViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] idBtn = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_bang, R.id.btn_cong,
                R.id.btn_tru, R.id.btn_nhan, R.id.btn_chia, R.id.btn_C, R.id.btn_cham};
        tvPhuongthuc = findViewById(R.id.tv_bieuthuc);
        tvKetqua = findViewById(R.id.tv_ketqua);
        for (int id : idBtn) {
            View v = findViewById(id);
            v.setOnClickListener(this);
        }

        viewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        viewModel.getResult().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                tvKetqua.setText(df.format(aDouble) + "");

            }


        });
        viewModel.getPhuongthuc().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String aString) {
                tvPhuongthuc.setText(aString);

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                tvPhuongthuc.append("0");
                display+="0";
                break;
            case R.id.btn_1:
                tvPhuongthuc.append("1");
                display+="1";

                break;
            case R.id.btn_2:
                tvPhuongthuc.append("2");
                display+="2";

                break;
            case R.id.btn_3:
                tvPhuongthuc.append("3");
                display+="3";

                break;
            case R.id.btn_4:
                tvPhuongthuc.append("4");
                display+="4";

                break;
            case R.id.btn_5:
                tvPhuongthuc.append("5");
                display+="5";

                break;
            case R.id.btn_6:
                tvPhuongthuc.append("6");
                display+="6";

                break;
            case R.id.btn_7:
                tvPhuongthuc.append("7");
                display+="7";

                break;
            case R.id.btn_8:
                tvPhuongthuc.append("8");
                display+="8";

                break;
            case R.id.btn_9:
                tvPhuongthuc.append("9");
                display+="9";

                break;
            case R.id.btn_cong:
                tvPhuongthuc.append("+");
                display+="+";

                break;
            case R.id.btn_tru:
                tvPhuongthuc.append("-");
                display+="-";

                break;
            case R.id.btn_nhan:
                tvPhuongthuc.append("x");
                display+="x";

                break;
            case R.id.btn_chia:
                tvPhuongthuc.append("/");
                display+="/";

                break;
            case R.id.btn_C:
                tvPhuongthuc.setText("");
                tvKetqua.setText("");
                break;
            case R.id.btn_cham:
                tvPhuongthuc.append(".");
                display+=".";


            case R.id.btn_bang:
                double result = 0;
                addOperation(tvPhuongthuc.getText().toString());
                addNumber(tvPhuongthuc.getText().toString());

                for (int i = 0; i < (arrNumber.size() - 1); i++) {
                    switch (arrOperation.get(i)) {
                        case "+":
                            if (i == 0) {
                                result = arrNumber.get(i) + arrNumber.get(i + 1);
                            } else {
                                result = result + arrNumber.get(i + 1);
                            }
                            break;
                        case "-":
                            if (i == 0) {
                                result = arrNumber.get(i) - arrNumber.get(i + 1);
                            } else {
                                result = result - arrNumber.get(i + 1);
                            }
                            break;
                        case "x":
                            if (i == 0) {
                                result = arrNumber.get(i) * arrNumber.get(i + 1);
                            } else {
                                result = result * arrNumber.get(i + 1);
                            }
                            break;
                        case "/":
                            if (i == 0) {
                                result = arrNumber.get(i) / arrNumber.get(i + 1);
                            } else {
                                result = result / arrNumber.get(i + 1);
                            }
                            break;
                        default:
                            break;
                    }
                }
                // tvKetqua.setText(df.format(result) + "");
                // final double finalResult = result;
                viewModel.giatriresult(result);
                viewModel.phuongthuc(display);

            default:
                break;

        }
    }
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }


}