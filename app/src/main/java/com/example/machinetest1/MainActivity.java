package com.example.machinetest1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtConvert;
    Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews() {
        edtConvert = findViewById(R.id.edtConvert);
        btnConvert = findViewById(R.id.btnConvert);
    }

    private void initListeners() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edtConvert.getText().toString();
                if (!inputText.isEmpty()) {
                    double amount = Double.parseDouble(inputText);
                    showCustomDialog(amount);
                } else {
                    // Handle empty input
                    edtConvert.setError("Please enter a value");
                }
            }
        });
    }

    private void showCustomDialog(double amount) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.convertcurrencydialog);

        TextView tvResult = dialog.findViewById(R.id.tvResult);
        RadioButton radioCan = dialog.findViewById(R.id.radioCan);
        RadioButton radioUsa = dialog.findViewById(R.id.radioUsa);
        RadioButton radioUk = dialog.findViewById(R.id.radioUk);
        RadioButton radioChi = dialog.findViewById(R.id.radioChi);
        Button closeDialogBtn = dialog.findViewById(R.id.closeDialogBtn);

        View.OnClickListener updateCurrency = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = amount;

                if (radioCan.isChecked()) {
                    result = result * 0.016;
                } else if (radioUsa.isChecked()) {
                    result = result * 0.012;
                } else if (radioUk.isChecked()) {
                    result = result *0.0095;
                } else if (radioChi.isChecked()) {
                    result = result *0.088;
                }
                tvResult.setText(String.format("%.2f", result));
            }
        };

        radioCan.setOnClickListener(updateCurrency);
        radioUsa.setOnClickListener(updateCurrency);
        radioUk.setOnClickListener(updateCurrency);
        radioChi.setOnClickListener(updateCurrency);

        //cancel button
        closeDialogBtn.setOnClickListener(v -> dialog.dismiss()); // Dismiss dialog

        dialog.show();
    }
}
