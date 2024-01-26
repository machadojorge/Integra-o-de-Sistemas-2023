package aposta.register.euro.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import aposta.register.euro.entities.CreditData;
import aposta.register.euro.entities.DigitalCheck;
import aposta.register.euro.utils.FromStringTpDate;
import org.springframework.web.client.RestTemplate;


public class DigitalCheckService {
    
   // Metodo para criar o url completo. Este método é privado pois só faz sentido ser
   // chamado dentro desta classe
   private static URL createCompleteUrl(String url, CreditData creditData)
   {
        try
        {
            String urlComplete = String.format("%s%s/amount/%s", url, creditData.getCreditAAccountId(), creditData.getAmmount());
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
            // Criação de uma instância de RestTemplate
            RestTemplate restTemplate = new RestTemplate();

        // Fazendo uma requisição GET e recebendo a resposta
            Map<String,Object> dados = restTemplate.getForObject(urlConnection.toString(), Map.class);
           var result = dados.get("date");
           String date = result.toString();
           Long checkID = Long.parseLong(dados.get("checkID").toString());
           System.out.println(("Date: " + date));
           System.out.println("CheckID: " + checkID);
          
     
            try
            {
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
