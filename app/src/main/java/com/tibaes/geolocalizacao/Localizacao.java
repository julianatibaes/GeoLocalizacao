package com.tibaes.geolocalizacao;

/**
 * Created by Juliana on 02/10/2016.
 */

public class Localizacao {
    private String cidade;
    private String estado;
    private String pais;

    public Localizacao(String cidade, String estado, String pais) {
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }
}
