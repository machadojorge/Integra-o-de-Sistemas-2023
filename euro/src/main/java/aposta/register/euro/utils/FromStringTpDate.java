package aposta.register.euro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromStringTpDate {
    
    // Este método traduz uma String para um Objecto Date de acordo com o respetivo formato Date desejado
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
           // System.out.println("Data e Hora: " + dateFormat.format(dateConverted));
            return dateConverted;
        }
        catch(Exception e)
        {
            System.out.println("Error to convert the String to Date");
        }
        return dateConverted;
    }

    // Este método traduz um objeto Date para uma String
    public static String convertDateToString(Date date)
    {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

        String dataConvertedString = " ";

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            // Fazer a conversão da string para o objeto Date
            dataConvertedString = dateFormat.format(date);

            // Imprimir o objeto Date
           // System.out.println("Data e Hora em String: " + dataConvertedString);
            return dataConvertedString;
        }
        catch(Exception e)
        {
            System.out.println("Error to convert the String to Date");
        }
        return dataConvertedString;
    }

}
