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
            String urlComplete = String.format("%s%s/ammount/%s", url, creditData.getCreditAAccountId(), creditData.getAmmount());
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
            //primeiro criamos a connecção 
            HttpURLConnection connect = (HttpURLConnection) urlConnection.openConnection();
            
            // Defenir o metodo da requesição, neste caso é o get
            connect.setRequestMethod("GET");
            System.out.println("get----");
            int responseCode = connect.getResponseCode();
            System.out.println("Response code: " + Integer.toString(responseCode));

              // Criação de uma instância de RestTemplate
            RestTemplate restTemplate = new RestTemplate();

        // Fazendo uma requisição GET e recebendo a resposta
            List<Map<String,Object>> dados = restTemplate.getForObject(urlConnection.toString(), List.class);
            System.out.println("LLISTA: " + dados.get(0));
           var result = dados.get(0).get("date");
           System.out.println("#............");
           System.out.println("result: " + result);
           String date = result.toString();
           Long checkID = Long.parseLong(dados.get(0).get("checkID").toString());
           System.out.println(("Date: " + date));
           System.out.println("CheckID: " + checkID);
          
        //     System.out.println(dados.getClass().getName());
        //     for (var jsonObject : dados) {
        //         System.out.println("------");
        //         System.out.println(jsonObject.toString());
           
        //     System.out.println("------");
        // }
        //     if (responseCode != 200)
        //     {
        //         return null;
        //     }
        //     StringBuffer response = new StringBuffer();
         
        //     System.out.println("Tenho o JSON");

            try
            {
        //         // // Ler a resposta do servidor
                // BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                // String inputLine;
                // System.out.println("klakjskjalsklsa");
                // while ((inputLine = in.readLine()) != null) {
                //     response.append(inputLine);
                // }
                // in.close();
                // System.out.println("aaaaaaaaa");
                // System.out.println(response);
                // System.out.println("Type: "+ response.getClass().getName());
                // String resp = response.toString();
                // // Extrair os dados da mensagem e guarda-los em um objeto
                // String jsonString = response.toString();
                // JSONObject jsonObject = new JSONObject(jsonString);
                // System.out.println("Vou extrair");
                // String date = jsonObject.getString("date");
                // long checkID = jsonObject.getLong("checkID");
                // digitalCheck.setCheckDate(FromStringTpDate.convertStringToDate(date));


                // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                // String dataHoraFormatada = dateFormat.format(new Date());
                // JSONObject obj = new JSONObject();
                // System.out.println("ooooooooooooooooooooooooooooooo");
                // obj.put("date",dataHoraFormatada);
                // obj.put("checkID", creditData.getCreditAAccountId());
                // List<JSONObject> list = new ArrayList<JSONObject>();
                // list.add(obj);
                // digitalCheck.setCheckDate(FromStringTpDate.convertStringToDate(list.get(0).get("date").toString()));
                // digitalCheck.setCheckId(Long.parseLong(list.get(0).get("checkID").toString()));
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
//    public static void main(String[] args) throws Exception
//    {
//     StringBuffer sb = new StringBuffer("[\"beginnersbook\":\"name\"]");
//     String a = sb.toString();
//     System.out.println(a);
//    }

}
