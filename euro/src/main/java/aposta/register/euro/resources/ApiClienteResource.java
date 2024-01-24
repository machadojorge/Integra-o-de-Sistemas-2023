package aposta.register.euro.resources;

import org.apache.el.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import aposta.register.euro.grpc.ClientEuromil;
import aposta.register.euro.utils.Valid;

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
        ClientEuromil client = new ClientEuromil();
        
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
        

        String result = " ";
        try{
            result = client.registerEuroMil(credit_account_id, ammount);
            System.out.println("Result: " + result);
        }catch (Exception e){
            System.out.println("Some error ocurred in the Server: " + e.getMessage());
            result = "Error to connect to the Server to get the Check";
        }
     
     
        model.addAttribute("mensagem", "Vou enviar o Resultado");
        
        return "resultado";
    }



    // @GetMapping("/hello")
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


