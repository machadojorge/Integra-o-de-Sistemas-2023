package tarefa5.creditbank.utils;

public class CheckLengthCheckId
{
    private static int LENCHECK = 16;
    public static boolean checkLength(Long checkId)
    {
        boolean result = false;

        int lenCheckId = String.valueOf(checkId).length();
        if (lenCheckId == LENCHECK)
        {
            
            return true;
        }

        return result;
    }


// public static void main(String[] args)
// {
//     long a = 1231231231231238L;
//     if (CheckLengthCheckId.checkLength(a))
//     {
//         System.out.println("É o tamanho : ");
//     }
//     else
//     {
//         System.out.println("Não é o tamaanho");
//     }
// }
}