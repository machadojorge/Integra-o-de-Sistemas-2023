package main.tarefa5.creditbank;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import main.tarefa5.creditbank.entities.CreditData;
import main.tarefa5.creditbank.entities.DigitalCheck;
import main.tarefa5.creditbank.service.DigitalCheckService;

public class Main {

    public static void main(String[] args) {
       
        String urlString = "https://credibank.intsis.utad.pt:8080/check/";
        // A linha de codigo seguinte é para se poder introduzir os dados (Credit account id e ammount)
        // atraves de inputs pelo teclado
        //CreditData creditData = getCreditInformation();
        CreditData creditData = new CreditData(1234567899874563L, 100.0f);
        System.out.println(creditData);
       
        URL url = DigitalCheckService.createCompleteUrl(urlString, creditData);
        System.out.println("URL: " + url);

        DigitalCheck digitalCheck = DigitalCheckService.getDigitalCheck(urlString, creditData);
        System.out.println(digitalCheck);
    }


    public static CreditData getCreditInformation()
    {
         Scanner sc = new Scanner(System.in);
        System.out.println("Credit Information");
        System.out.print("Credit Account ID: ");
        while (!sc.hasNextLong()) {
            System.out.print("Inválid Entry. Please insert a valid Credit Account ID: ");
            sc.next(); // Limpa a entrada inválida
        }
        long creditAAccountId = sc.nextLong();

        System.out.print("Ammount: ");
        while (!sc.hasNextFloat()) {
            System.out.print("Inválid Entry. Please insert a valid Ammount: ");
            sc.next(); // Limpa a entrada inválida
        }
        float ammount = sc.nextFloat();

        return new CreditData(creditAAccountId, ammount);
    }
    
}
