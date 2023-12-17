package main.tarefa5.creditbank.entities;
public class CreditData {
    
    private Long creditAAccountId;
    private Float ammount;

    public CreditData()
    {
        this.creditAAccountId = " ";
        this.ammount = 0.0f;
    }

    public CreditData(Long creditAAccountId, Float ammount) 
    {
        this.setCreditAAccountId(creditAAccountId);
        this.setAmmount(ammount);
    }

    public Long getCreditAAccountId() {
       
        return creditAAccountId;
    }

    public void setCreditAAccountId(Long creditAAccountId) {
        if ((creditAAccountId == null) || (creditAAccountId.toString().length()!=16))
        {
            System.out.println("Inválid credit Account Id! The length of the credit Account must be 16");
        }
        else{
             this.creditAAccountId = creditAAccountId;
        }
       
    }

    public Float getAmmount() {
        return ammount;
    }

    public void setAmmount(Float ammount) {
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
        return "CreditData [creditAAccountId=" + creditAAccountId + ", ammount=" + ammount + "]";
    }

    
}
