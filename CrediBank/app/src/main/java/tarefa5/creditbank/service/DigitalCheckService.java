package tarefa5.creditbank.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import tarefa5.creditbank.entities.CreditData;
import tarefa5.creditbank.entities.DigitalCheck;
import tarefa5.creditbank.utils.FromStringTpDate;
import org.json.JSONObject;


public class DigitalCheckService {
    
   // Metodo para criar o url completo. Este método é privado pois só faz sentido ser
   // chamado dentro desta classe
   public static URL createCompleteUrl(String url, CreditData creditData)
   {
        try
        {
            String urlComplete = String.format("%s%d/ammount/%f", url, creditData.getCreditAAccountId(), creditData.getAmmount());
           //System.out.println("URL Complet: " + urlComplete);

            URL urlForGet = new URL(urlComplete); 
            
            System.out.println("URL: " + urlForGet + "\n");
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

            if (responseCode != 200)
            {
                return null;
            }
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

                // Extrair os dados da mensagem e guarda-los em um objeto
                String jsonString = response.toString();
                JSONObject jsonObject = new JSONObject(jsonString);

                String date = jsonObject.getString("date");
                long checkID = jsonObject.getLong("checkID");
                digitalCheck.setCheckDate(FromStringTpDate.convertStringToDate(date));
                digitalCheck.setCheckId(checkID);
                
                return digitalCheck;
            }
            catch (Exception e)
            {
                System.err.println("Error to get the response: " + e.getMessage());
                return null;
            }

        
        }
        catch (Exception e)
        {
            System.out.println("Error to Connect to the URL " + e.getMessage());
            return null;
        }

   
   }

}
