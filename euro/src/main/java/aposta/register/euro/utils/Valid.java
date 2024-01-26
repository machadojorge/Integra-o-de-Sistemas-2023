package aposta.register.euro.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Valid {
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
                System.out.println("Error to Parse the Number! The number must be between 0 - 50");
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

            if (nInt > limit && nInt <= 0)
            {
                System.out.println("Error in the number Value: The Number must be between 1 -- 50");
                return false;
            }
        }
        return true;    
    }


   
    public static String createString(List<String> list)
    {
        String number = "";
        for(String n : list)
        {
            number = number + n;
        }
        return number;
    }
}

