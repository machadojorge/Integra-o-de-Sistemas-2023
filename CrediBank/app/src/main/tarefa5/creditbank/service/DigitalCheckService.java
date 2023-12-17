package main.tarefa5.creditbank.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import main.tarefa5.creditbank.entities.CreditData;
import main.tarefa5.creditbank.entities.DigitalCheck;
import main.tarefa5.creditbank.utils.FromStringTpDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class DigitalCheckService {
    
   // Metodo para criar o url completo. Este método é privado pois só faz sentido ser
   // chamado dentro desta classe
   public static URL createCompleteUrl(String url, CreditData creditData)
   {
        try
        {
            String urlComplete = String.format("%s%d/ammount/%f", url, creditData.getCreditAAccountId(), creditData.getAmmount());
            System.out.println("URL Complet: " + urlComplete);

            URL urlForGet = new URL(urlComplete); 
            return urlForGet;
        }
        catch(Exception e)
        {
            System.out.println("Error to create Url: " + e.getMessage());
            return null;
        }
   }


   public static DigitalCheck getDigitalCheck(String url, CreditData creditData)
   {
        DigitalCheck digitalCheck = new DigitalCheck();
        URL urlConnection = createCompleteUrl(url, creditData);
        try
        {
            // primeiro criamos a connecção 
            HttpURLConnection connect = (HttpURLConnection) urlConnection.openConnection();
            
            // Defenir o metodo da requesição, neste caso é o get
            connect.setRequestMethod("GET");

            int responseCode = connect.getResponseCode();
            StringBuffer response = new StringBuffer();

            try
            {
                // Ler a resposta do servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i<jsonArray.lenght(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // Antes de atribuir a data ao objecto do tipo "Date", vamos converter a string para o Objecto Date chamando o metodo 
                    // da pasta utils "FromStringTpDate.convertStringToDate(String)"
                    digitalCheck.setCheckDate(new Date(FromStringTpDate.convertStringToDate(jsonObject.getLong("date"))));
                    digitalCheck.setCheckId(Long.parseLong(jsonObject.getLong("checkID")));
                    return digitalCheck;
                }
            }
            catch (Exception e)
            {
                System.err.println("Error to get the response: " + e.getMessage());
            }

        
        }
        catch (Exception e)
        {
            System.out.println("Error to Connect to the URL " + e.getMessage());
        }

    return digitalCheck;
   }

}
