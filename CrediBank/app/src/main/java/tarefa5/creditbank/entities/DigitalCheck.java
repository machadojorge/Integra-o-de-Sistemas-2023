package tarefa5.creditbank.entities;

import tarefa5.creditbank.utils.FromStringTpDate;
import java.util.Date;

public class DigitalCheck {
    
    private Date checkDate;
    private Long checkId;

    public DigitalCheck()
    {}

    public DigitalCheck(Date checkDate, Long checkId)
    {
        this.checkDate = checkDate;
        this.checkId = checkId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    @Override
    public String toString() {
        String checkDataString = FromStringTpDate.convertDateToString(checkDate);
        return "Digital Check: --> Date: " + checkDataString  +" CheckId: " + checkId;
    }

    
}

