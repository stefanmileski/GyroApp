package com.stefanmileski.gyroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }

    public void updateTextFields() {
        int sumaSvinsko = (potrebni.stream().filter(x-> x.tipMeso==TipMeso.SVINSKO).mapToInt(x-> x.grami).sum())-(presecheni.stream().filter(x-> x.tipMeso==TipMeso.SVINSKO).mapToInt(x-> x.grami).sum());
        int sumaPileshko = (potrebni.stream().filter(x-> x.tipMeso==TipMeso.PILESHKO).mapToInt(x-> x.grami).sum())-(presecheni.stream().filter(x-> x.tipMeso==TipMeso.PILESHKO).mapToInt(x-> x.grami).sum());
        ((TextView) findViewById(R.id.txtVwPil)).setText(sumaPileshko);
        ((TextView) findViewById(R.id.txtVwSv)).setText(sumaSvinsko);
    }

    public void svLClick() {
        potrebni.add(new Gramazha(110, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svSClick() {
        potrebni.add(new Gramazha(80, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svPorClick() {
        potrebni.add(new Gramazha(150, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void svXXLClick() {
        potrebni.add(new Gramazha(170, TipMeso.SVINSKO));
        updateTextFields();
    }

    public void pilLClick() {
        potrebni.add(new Gramazha(110, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilSClick() {
        potrebni.add(new Gramazha(80, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilPorClick() {
        potrebni.add(new Gramazha(150, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void pilXXLClick() {
        potrebni.add(new Gramazha(170, TipMeso.PILESHKO));
        updateTextFields();
    }

    public void presechenoClick() {
        int presechenoSv = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.nmbrBxSv)).getText()));
        int presechenoPil = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.nmbrBxPil)).getText()));
        if (presechenoSv != 0) {
            presecheni.add(new Gramazha(presechenoSv, TipMeso.SVINSKO));
        }
        if (presechenoPil != 0) {
            presecheni.add(new Gramazha(presechenoPil, TipMeso.PILESHKO));
        }
        updateTextFields();
    }
}