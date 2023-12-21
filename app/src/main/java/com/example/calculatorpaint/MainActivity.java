package com.example.calculatorpaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    TextView Result;
    Button btn;
    RadioGroup group;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Находим элементы
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        Result = findViewById(R.id.result);
        btn = findViewById(R.id.button1);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton3.setChecked(true);

        btn.setOnClickListener(this);
    }


    public void onClick(View view) {
        try {
            // Проверка на пустые поля
            if (isEmpty(editText1) || isEmpty(editText2) || isEmpty(editText3) || isEmpty(editText4) || isEmpty(editText5)) {
                // Обработка ошибки: одно из полей пустое
                showError("Заполните все поля.");
                return;
            }

            double width = parseDouble(editText1);
            double height = parseDouble(editText2);
            double paint = parseDouble(editText3);
            double quantity = parseDouble(editText4);
            double jar = parseDouble(editText5);

            // Проверка на отрицательные числа и символы
            if (width < 0 || height < 0 || paint < 0 || quantity < 0 || jar <= 0) {
                // Обработка ошибки: отрицательное число или ноль
                showError("Только положительные значения.");
                return;
            }

            double reserve;

            if (radioButton1.isChecked()) {
                reserve = 0.1;
            } else if (radioButton2.isChecked()) {
                reserve = 0.15;
            } else {
                reserve = 0;
            }

            double s = width * height;
            double ConsumptionEnd = paint * quantity;
            double pretotal = (s * ConsumptionEnd) + (s * ConsumptionEnd * reserve);
            double result = pretotal / jar;

            Result.setText("Результат: \n" + "Нужно литров краски: " + pretotal + "\n" + "Нужно банок краски: " + Math.ceil(result));
        } catch (NumberFormatException e) {
            // Обработка ошибки: введено не число
            showError("Только числовые значения.");
        }
    }
    // Метод для проверки пустого поля
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    // Метод для безопасного преобразования строки в double
    private double parseDouble(EditText editText) {
        return Double.parseDouble(editText.getText().toString());
    }

    // Метод для отображения ошибки
    private void showError(String message) {
        Result.setText( message);
    }
}
