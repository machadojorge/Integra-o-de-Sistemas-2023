package tarefa5.creditbank;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import tarefa5.creditbank.entities.CreditData;
import tarefa5.creditbank.entities.DigitalCheck;
import tarefa5.creditbank.service.DigitalCheckService;
import tarefa5.creditbank.utils.CheckLengthCheckId;



public class Main {

    public static void main(String[] args) {
       
        // URL principal
        String urlString = "https://credibank.intsis.utad.pt:8080/check/";
        
        // URL de teste para API privada
         //String urlString = "http://localhost:8080/check/";
         
         // A linha de codigo seguinte é para se poder introduzir os dados (Credit account id e ammount)
        // atraves de inputs pelo teclado
         CreditData creditData = getCreditInformation();
        // CreditData creditData = new CreditData(1234567899874563L, 100.0f);
        // System.out.println(creditData);

        // DigitalCheck digitalCheck = DigitalCheckService.getDigitalCheck(urlString, creditData);
        // if (digitalCheck != null)
        // {
        //     System.out.println(digitalCheck);
             
        // }
        // else
        // {
        //      System.out.println("Error to get the Response from Server!");

        // }
      
        
    }


    public static CreditData getCreditInformation()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Credit Information");
        System.out.print("Credit Account ID: ");
        while (!sc.hasNextLong()) {
            System.out.print("Inválid Entry. Please insert a valid Credit Account ID with 16 digits: ");
            sc.nextLine(); // Limpa a entrada inválida
        }

        
        long creditAAccountId = sc.nextLong();
        while(CheckLengthCheckId.checkLength(creditAAccountId)!= true)
        {
            System.out.print("Inválid Entry. -> Please insert a valid Credit Account ID with 16 digits: ");
            if (sc.hasNextLong())
            {
                creditAAccountId = sc.nextLong();
               
            }
            System.out.println("Ler alguma coisa");;
            sc.nextLine(); // Limpa a entrada inválida
            System.out.println("já li");
        }

        System.out.print("Ammount: ");
        while (!sc.hasNextFloat()) {
            System.out.print("Inválid Entry. Please insert a valid Ammount with the ',' if the case: ");
            sc.next(); // Limpa a entrada inválida
        }
        float ammount = sc.nextFloat();

        return new CreditData(creditAAccountId, ammount);
    }
    
}
