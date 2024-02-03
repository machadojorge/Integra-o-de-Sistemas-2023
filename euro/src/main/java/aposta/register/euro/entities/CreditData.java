package aposta.register.euro.entities;

// Classe que vai armazenar os valores introduzidos da View inicial, o Credit_account_id e o ammount 
// tem algumas validações para a introdução dos dados, para respeitar o formato Pedido
public class CreditData {
    
    private Long creditAAccountId;
    private Integer ammount;

    public CreditData()
    {
        this.creditAAccountId = 0L;
        this.ammount = 0;
    }

    public CreditData(Long creditAAccountId, Integer ammount) 
    {
        this.setCreditAAccountId(creditAAccountId);
        this.setAmmount(ammount);
    }

    public Long getCreditAAccountId() {
       
        return creditAAccountId;
    }

    public void setCreditAAccountId(Long creditAAccountId) {
        if ((creditAAccountId == null) || (creditAAccountId.toString().length()!=8))
        {
            System.out.println("Inválid credit Account Id! The length of the credit Account must be 16");
        }
        else{
             this.creditAAccountId = creditAAccountId;
        }
       
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        if ((ammount == null) || (ammount<=0.0))
        {
             System.out.println("Inválid Amount! The amomount must be more than 0.0");
        }
        else
        {
            this.ammount = ammount;
        }
       
    }

    @Override
    public String toString() {
        return "CreditData: --> Crredit Account Id: " + creditAAccountId + ", Ammount: " + ammount;
    }

    
}
