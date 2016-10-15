package com.tibaes.geolocalizacao;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtIp;
    private TextView lblPais, lblEstado, lblCidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //permite conexão com a Internet na Thread principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //carrega os controles da interface para objetos
        txtIp = (EditText)findViewById(R.id.et_ip);
        lblPais = (TextView)findViewById(R.id.tv_pais);
        lblEstado = (TextView)findViewById(R.id.tv_estado);
        lblCidade = (TextView)findViewById(R.id.tv_cidade);
    }

    public void carregarLocalizacao(View view){
        try {

            Localizacao localizacao = ClienteGeoIP.retornarLocalizacaoPorIp(txtIp.getText().toString());
          //  lblPais.setText(txtIp.getText());
            lblPais.setText("País: " + localizacao.getPais());
            lblEstado.setText("Estado: " + localizacao.getEstado());
            lblCidade.setText("Cidade: " + localizacao.getCidade());

        }
        catch(Exception ex){
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
