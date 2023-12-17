package main.tarefa5.creditbank.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromStringTpDate {
    
    public static Date convertStringToDate(String date)
    {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

        Date dateConverted = new Date();

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            // Fazer a conversão da string para o objeto Date
            dateConverted = dateFormat.parse(date);

            // Imprimir o objeto Date
            System.out.println("Data e Hora: " + dateFormat.format(dateConverted));
            return dateConverted;
        }
        catch(Exception e)
        {
            System.out.println("Error to convert the String to Date");
        }
        return dateConverted;
    }

    public static String convertDateToString(Date date)
    {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

        String dataConvertedString = " ";
        System.out.println("String");

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            // Fazer a conversão da string para o objeto Date
            dataConvertedString = dateFormat.format(date);

            // Imprimir o objeto Date
            System.out.println("Data e Hora em String: " + dataConvertedString);
            return dataConvertedString;
        }
        catch(Exception e)
        {
            System.out.println("Error to convert the String to Date");
        }
        return dataConvertedString;
    }

    // public static void main(String[] args)
    // {
    //     String Data = "2018-01-01T00:00:00";

    //     Date a = FromStringTpDate.convertStringToDate(Data);
    //     System.err.println(a.getClass());

    //     String b = FromStringTpDate.convertDateToString(a);

    //     System.out.println("String Data: " + b);
    // }
}
