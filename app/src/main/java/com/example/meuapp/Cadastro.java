package com.example.meuapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.Touch;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cadastro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cadastro extends Fragment {
    EditText nome, quantidade, preco;
    Button cadastrar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cadastro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cadastro.
     */
    // TODO: Rename and change types and number of parameters
    public static Cadastro newInstance(String param1, String param2) {
        Cadastro fragment = new Cadastro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cadastro, container, false);

        nome = v.findViewById(R.id.nome);
        quantidade = v.findViewById(R.id.quantidade);
        preco = v.findViewById(R.id.preco);
        cadastrar = v.findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(click -> {
            novoProduto();
        });

        return v;
    }

    public void efetivaCadastro() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").child(nome.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(getContext(), "Produto já existe!", Toast.LENGTH_LONG).show();
                } else {
                    String novoNome = nome.getText().toString();
                    int novaQuantidade = Integer.parseInt(quantidade.getText().toString());
                    float novoPreco = Float.parseFloat(preco.getText().toString());

                    Produto novoProduto = new Produto(novoNome, novaQuantidade, novoPreco);
                    novoProduto.salvar();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void novoProduto() {
        if (verificaCampos()) {
            efetivaCadastro();
        } else {
            Toast.makeText(getContext(), "Preencha todos os campos corretamente!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean verificaCampos() {
        if (nome.getText().toString().isEmpty() || quantidade.getText().toString().isEmpty() || preco.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
}