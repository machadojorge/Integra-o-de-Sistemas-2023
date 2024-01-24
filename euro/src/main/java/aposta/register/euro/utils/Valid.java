package aposta.register.euro.utils;

public class Valid {
    public static boolean checkCreditAccountID(String creditAccount)
    {   
        if (!(creditAccount.length() == 16))
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
}
