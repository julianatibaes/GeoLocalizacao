package com.tibaes.geolocalizacao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Juliana on 02/10/2016.
 */

public class ClienteGeoIP {

    public static Localizacao retornarLocalizacaoPorIp(String ip) throws IOException, JSONException {

        HttpURLConnection c = null;
        int timeout = 300;
        String pais = "";
        String estado = "";
        String cidade = "";
        try {
            URL u = new URL("http://freegeoip.net/json/" + ip);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    JSONObject obj = new JSONObject(sb.toString());
                    pais = obj.get("country_name").toString();
                    estado = obj.get("region_name").toString();
                    cidade = obj.get("city").toString();
            }

        } catch (MalformedURLException ex) {
          System.out.println(ex.getMessage());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return new Localizacao(cidade, estado, pais);
    }
}
