package aposta.register.euro.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Valid {

    // Este método verifica o inputo do Numero de Conta, verifica se tem 8 digitos e se todos eles são numeros
    // caso contrario devolve um false
    public static boolean checkCreditAccountID(String creditAccount)
    {   
        if (!(creditAccount.length() == 8))
        {
            return false;
        }

        try{
            long number = Long.parseLong(creditAccount);
        }
        catch(Exception e)
        {
            return false;
        }

        return true;

    }

    // Este método verifica se o montante introduzido no input respeita os requesitos, se é um numero e é o valor 10 (Requesito do Servidor API REST)
    // devolve um false se isto falhar
    public static boolean checkAmmount(String ammount)
    {
        if (ammount.length() ==0)
        {
            return false;
        }

        Double number = 0.0;
        try{
            number = Double.parseDouble(ammount);
        }
        catch (Exception e)
        {
            System.out.println("Error to decode the Ammount to a Double: " + e.getMessage());
            return false;
        }

        if (!(number == 10))
        {
            System.out.println("Error! The ammount needs be 10");
            return false;
        }

        return true;
    }

    // Este método verifica se não ha elementos repetidos na chave do Euromil!
    public static <T> boolean checkUniqueValues(List<T> lista) {
        Set<T> set = new HashSet<>();

        for (T elemento : lista) {
            if (!set.add(elemento)) {
                // Se não foi possível adicionar o elemento ao conjunto, significa que é duplicado
                return false;
            }
        }

        // Se chegou até aqui, todos os elementos são únicos
        return true;
    }

    // Este método verifica os valores introduzidos para a chave do Euromil. Recebe uma lista de valores
    // e percorre a lista a verificar se são numeros, se são repetidos e se estão entre o intervalo aceite 
    public static boolean euroNumber(List<String> numbers, Integer limit)
    {
        int nInt = 0;
        System.out.println(numbers);
        System.out.println(numbers.size());
        for (String n : numbers)
        {
            System.out.println("N: " + n);
            if (n == null)
            {
                System.out.println("Error to Parse the Number! The number must be between 0 - {}" + nInt);
                return false;
            }
            try{
                System.out.println(n);
                 nInt = Integer.parseInt(n);
                 System.out.println(nInt);
            }
            catch(Exception e){
                System.out.println("Error to Parse the number: " + e.getMessage());
                return false;
            }

            if (nInt > limit || nInt <= 0)
            {
                System.out.println("Error in the number Value: The Number must be between 1 -- {}" + nInt);
                return false;
            }
        }
        return true;    
    }


   // Este método recebe uma lista de valores e vai criar uma string, no formato correto, para a chave do Euromil.
   // devolve essa chave pronta a ser enviada no pedido gRPC
    public static String createString(List<String> list)
    {
        String number = "";
        for(String n : list)
        {
            if (n.length() < 2)
            {
                number = number + "0" + n + " "; 
            }
            else
            {
                number = number + n + " ";
            }
            
        }
        // Criar uma string de acordo ao pedido no gRPC
        number = number.substring(0, number.length() - 1); 
        return number;
    }
}

