package com.example.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaProduto extends AppCompatActivity {
    static Produto p;
    EditText nome, quantidade, preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.rgb(76, 175, 80));
        nome = findViewById(R.id.nomeProduto);
        quantidade = findViewById(R.id.quantidadeProduto);
        preco = findViewById(R.id.precoProduto);

        nome.setText(p.getNome());
        quantidade.setText(p.getQuantidade());
        preco.setText(p.getPreco()+"");
    }

    public void atualiza(View v){
        deletar();
        String n = nome.getText().toString();
        int q = Integer.parseInt(quantidade.getText().toString());
        float p = Float.parseFloat(preco.getText().toString());
        Produto produto = new Produto(n, q, p);
        produto.salvar();
        super.onBackPressed();
    }

    public void exclui(View v){
        deletar();
        super.onBackPressed();
    }

    public void deletar(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").child(p.getNome()).removeValue();
    }
}