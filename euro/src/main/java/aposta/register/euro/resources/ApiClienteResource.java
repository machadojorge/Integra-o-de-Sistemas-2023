package aposta.register.euro.resources;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.el.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    private String checkID;


    @GetMapping("/formulario")
    public String exibirFormulario() {
        return "formulario";
    }

    @GetMapping("/submeter")
    public String submeterFormulario(@RequestParam String credit_account_id,
                                     @RequestParam String ammount,
                                     Model model) {
        
                                        // so para testes
                                        this.checkID = credit_account_id;

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
        CreditData creditData = new CreditData(Long.parseLong(credit_account_id), Integer.parseInt(ammount));

        // 2º Defenir a String com o URL
        String url = "http://localhost:9090/check/";

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
    public String paginaB( @RequestParam("checkid") String valor, Model model) {
      
        model.addAttribute("checkid", checkID);
       // model.addAttribute("mensagem_ammount", "It is not possible generate the Check. Please try again!");
        return "euromil_register";
        
    }

    // quando fizer o submit, vem para este post com os valores todos para juntar 
    // na Key e enviar por grpc!
    @PostMapping("/euromil_register")
    public String postMethodName(//@RequestParam("checkID") String checkID,
                                    @RequestParam("val1") String val1,
                                    @RequestParam("val2") String val2,
                                    @RequestParam("val3") String val3,
                                    @RequestParam("val4") String val4,
                                    @RequestParam("val5") String val5,
                                    @RequestParam("star1") String star1,
                                    @RequestParam("star2") String star2,
                                    Model model)  
    {
        System.out.println("Adicionados à lista!");
        System.out.println(checkID);
        System.out.println(val1);
    
        
        List<String> list = new ArrayList<String>();
        list.add(val1);
        list.add(val2);
        list.add(val3);
        list.add(val4);
        list.add(val5);
        
        List<String> nstr = new ArrayList<String>();
        nstr.add(star1);
        nstr.add(star2);
     
        boolean result = Valid.euroNumber(list, 50);
        if (result == false)
        {
            model.addAttribute("message_number", "Some invalid number! The number need to be between 1 - 50");
             return "euromil_register";
        }

        result = Valid.euroNumber(nstr, 9);
        if (result == false)
        {
            model.addAttribute("message_number", "Some invalid number! The Stars need to be between 1 - 5");
             return "euromil_register";
        }

        list.add(nstr.get(0));
        list.add(nstr.get(1));
        String Key = Valid.createString(list);
        ClientEuromil client = new ClientEuromil();
        String resultMessage = " ";
        try{
            resultMessage = client.registerEuroMil(Key, this.checkID);
            System.out.println("Result: " + result);
        }catch (Exception e){
            System.out.println("Some error ocurred in the Server: " + e.getMessage());
            resultMessage = "Error to connect to the Server to get the Check";
        }
        model.addAttribute("mensagem", resultMessage);
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


