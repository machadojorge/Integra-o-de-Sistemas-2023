package aposta.register.euro.resources;

import java.net.URL;

import org.apache.el.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import aposta.register.euro.entities.CreditData;
import aposta.register.euro.entities.DigitalCheck;
import aposta.register.euro.grpc.ClientEuromil;
import aposta.register.euro.service.DigitalCheckService;
import aposta.register.euro.utils.FromStringTpDate;
import aposta.register.euro.utils.Valid;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ApiClienteResource {
    

    @GetMapping("/formulario")
    public String exibirFormulario() {
        return "formulario";
    }

    @GetMapping("/submeter")
    public String submeterFormulario(@RequestParam String credit_account_id,
                                     @RequestParam String ammount,
                                     Model model) {
        
        System.out.println("credit_account_id: " + credit_account_id + " --> Ammount:  "+ ammount) ;
        // Verificar se os valores introduzidos respeitam as condiçoes para os valores
        // credita_account_id --> 16 digitos decimais
        boolean checkAccount = Valid.checkCreditAccountID(credit_account_id);
        if (checkAccount == false)
        {
            // caso não Esteja correto o credit_account_id, mostra uma mensagem de informação!
            model.addAttribute("mensagem_credit_account", "Account ID must be 16 digits!");
            return "formulario";
        }


        // Verificar se o ammount é um numero, se é superior a 0 e se é igual a 10;
        // Pois na definição é dito que é para criar um cheque de 10 creditos 
        boolean checkAmmount = Valid.checkAmmount(ammount);
        if (checkAmmount == false)
        {
              // caso não Esteja correto o amount mostra uma mensagem de informação!
              model.addAttribute("mensagem_ammount", "The Ammount must be 10 credits");
              return "formulario";
        }
        
        // Fazer uma Requesição à API
        // 1º Criar um objeto com os dados de entrada
        CreditData creditData = new CreditData(Long.parseLong(credit_account_id), Float.parseFloat(ammount));

        // 2º Defenir a String com o URL
        String url = "http://localhost:8080/check/";

        // 3º Chamar o Service para fazer o pedido com o URL obtido e guardar a resposta em um objeto DigitalCheck
        DigitalCheck digitalCheck = DigitalCheckService.getDigitalCheck(url, creditData);

        // Verificar se o digitalCheck é null;
        if (digitalCheck!= null)
        {
            String data = FromStringTpDate.convertDateToString(digitalCheck.getCheckDate());
            String checkID = Long.toString(digitalCheck.getCheckId());
        
            model.addAttribute("checkmessage", "CheckID Complete with success!");
            model.addAttribute("data", data);
            model.addAttribute("checkid", checkID);
            return "submitEuro";
        }

        // String result = " ";
        // try{
        //     result = client.registerEuroMil(credit_account_id, ammount);
        //     System.out.println("Result: " + result);
        // }catch (Exception e){
        //     System.out.println("Some error ocurred in the Server: " + e.getMessage());
        //     result = "Error to connect to the Server to get the Check";
        // }
     
        model.addAttribute("mensagem_ammount", "It is not possible generate the Check. Please try again!");
        return "formulario";
    }



    // Vai mostrar um HTML com as janelas para introduzir os numeros do euro milhoes 
    @GetMapping("/euromil_register")
    public String paginaB(@RequestParam String parametro, Model model) {
      
     
       // model.addAttribute("mensagem_ammount", "It is not possible generate the Check. Please try again!");
        return "euroRegister";
        
    }

    // quando fizer o submit, vem para este post com os valores todos para juntar 
    // na Key e enviar por grpc!
    @PostMapping("/euromil_register")
    public String postMethodName(@RequestBody String checkID,
                                    @RequestParam String val1,
                                    @RequestParam String val2,
                                    @RequestParam String val3,
                                    @RequestParam String val4,
                                    @RequestParam String val5,
                                    @RequestParam String star1,
                                    @RequestParam String star2,
                                    Model model)  
    {
        String Key = " ";
        ClientEuromil client = new ClientEuromil();
        String result = " ";
        try{
            result = client.registerEuroMil(Key, checkID);
            System.out.println("Result: " + result);
        }catch (Exception e){
            System.out.println("Some error ocurred in the Server: " + e.getMessage());
            result = "Error to connect to the Server to get the Check";
        }
        
       return "result";
    }

    // ("/euromil_register")
    // public String paginaB(@RequestParam String parametro) {
    //     ClientEuromil client = new ClientEuromil();
    //     String result = " ";
    //     try{
    //         result = client.registerEuroMil(credit_account_id, ammount);
    //         System.out.println("Result: " + result);
    //     }catch (Exception e){
    //         System.out.println("Some error ocurred in the Server: " + e.getMessage());
    //         result = "Error to connect to the Server to get the Check";
    //     }
     
    //     model.addAttribute("mensagem_ammount", "It is not possible generate the Check. Please try again!");
    //     return "formulario";
    //     return "paginaB"; // Página associada ao endpoint B
    // }
    // // @GetMapping("/hello")
    // public String Register(@RequestParam(value = "check") String check, @RequestParam(value = "ammount")String ammount) {
    
    //     System.out.println("CheckID: " + check + " --> Ammount:  "+ ammount) ;
    //     String result = " ";
    //     try{
    //         result = client.registerEuroMil(check, ammount);
    //         System.out.println("Result: " + result);
    //     }catch (Exception e){
    //     }
     
    //     return "Hello  --->" + result;
    // }
}


