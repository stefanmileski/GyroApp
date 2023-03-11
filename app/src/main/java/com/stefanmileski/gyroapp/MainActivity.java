package com.stefanmileski.gyroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.stefanmileski.gyroapp.models.Gramazha;
import com.stefanmileski.gyroapp.models.TipMeso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Gramazha> potrebni = new ArrayList<>();
    List<Gramazha> presecheni = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextPil = findViewById(R.id.nmbrBxPil);
        editTextPil.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                findViewById(R.id.button3).performClick();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextPil.getWindowToken(), 0);
                return true;
            }
            return false;
        });
        EditText editTextSv = findViewById(R.id.nmbrBxSv);
        editTextSv.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                findViewById(R.id.button3).performClick();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextSv.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    public void updateTextFields() {
        int sumaSvinsko = (potrebni.stream().filter(x -> x.tipMeso == TipMeso.SVINSKO).mapToInt(x -> x.grami).sum()) - (presecheni.stream().filter(x -> x.tipMeso == TipMeso.SVINSKO).mapToInt(x -> x.grami).sum());
        int sumaPileshko = (potrebni.stream().filter(x -> x.tipMeso == TipMeso.PILESHKO).mapToInt(x -> x.grami).sum()) - (presecheni.stream().filter(x -> x.tipMeso == TipMeso.PILESHKO).mapToInt(x -> x.grami).sum());
        ((TextView) findViewById(R.id.txtVwPil)).setText(String.format("Пилешко: %dg", sumaPileshko));
        ((TextView) findViewById(R.id.txtVwSv)).setText(String.format("Свинско: %dg", sumaSvinsko));
        ((EditText) findViewById(R.id.nmbrBxSv)).setText("");
        ((EditText) findViewById(R.id.nmbrBxPil)).setText("");
    }

    public void undoPresClick(View view) {
        presecheni.remove(presecheni.size() - 1);
        updateTextFields();
    }

    public void undoPotrClick(View view) {
        potrebni.remove(potrebni.size() - 1);
        if (potrebni.get(potrebni.size() - 1).grami == 55
                || potrebni.get(potrebni.size() - 1).grami == 40
                || potrebni.get(potrebni.size() - 1).grami == 75
                || potrebni.get(potrebni.size() - 1).grami == 85)
            potrebni.remove(potrebni.size() - 1);
        updateTextFields();
    }

    public void svLClick(View view) {
        potrebni.add(new Gramazha(110, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svSClick(View view) {
        potrebni.add(new Gramazha(80, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svPorClick(View view) {
        potrebni.add(new Gramazha(150, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svXXLClick(View view) {
        potrebni.add(new Gramazha(170, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void pilLClick(View view) {
        potrebni.add(new Gramazha(110, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilSClick(View view) {
        potrebni.add(new Gramazha(80, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilPorClick(View view) {
        potrebni.add(new Gramazha(150, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilXXLClick(View view) {
        potrebni.add(new Gramazha(170, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void mixLClick(View view) {
        potrebni.add(new Gramazha(55, TipMeso.PILESHKO));
        potrebni.add(new Gramazha(55, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void mixSClick(View view) {
        potrebni.add(new Gramazha(40, TipMeso.PILESHKO));
        potrebni.add(new Gramazha(40, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void mixPorClick(View view) {
        potrebni.add(new Gramazha(75, TipMeso.PILESHKO));
        potrebni.add(new Gramazha(75, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void mixXXLClick(View view) {
        potrebni.add(new Gramazha(85, TipMeso.PILESHKO));
        potrebni.add(new Gramazha(85, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void presechenoClick(View view) {
        int presechenoSv;
        if (String.valueOf(((EditText) findViewById(R.id.nmbrBxSv)).getText()).equals(""))
            presechenoSv = 0;
        else
            presechenoSv = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.nmbrBxSv)).getText()));
        int presechenoPil;
        if (String.valueOf(((EditText) findViewById(R.id.nmbrBxPil)).getText()).equals(""))
            presechenoPil = 0;
        else
            presechenoPil = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.nmbrBxPil)).getText()));
        if (presechenoSv != 0) {
            presecheni.add(new Gramazha(presechenoSv, TipMeso.SVINSKO));
        }
        if (presechenoPil != 0) {
            presecheni.add(new Gramazha(presechenoPil, TipMeso.PILESHKO));
        }
        updateTextFields();
    }
}