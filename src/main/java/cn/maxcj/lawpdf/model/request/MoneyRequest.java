package cn.maxcj.lawpdf.model.request;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class MoneyRequest {

    private String outMoneyPersonName;
    private String outMoneyPersonIdCard;
    private String outMoneyPersonBankAccount;
    private String inMoneyPersonName;
    private String inMoneyPersonIdCard;
    private String inMoneyPersonBankAccount;
    private String court;
    private String paymentDay;
    private String money;
    private String term;
    private String termBeginDate;
    private String termEndDate;
    private String interestRateMonth;
    private String outTimeInterestRateMonth;

    private String receiverEmail;


    public String getTermEndDate() {
        return DateTime.parse(this.termBeginDate).plusMonths(Integer.parseInt(term)).toString("yyyy-MM-dd");
    }

}
