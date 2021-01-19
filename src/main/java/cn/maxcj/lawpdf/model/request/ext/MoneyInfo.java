package cn.maxcj.lawpdf.model.request.ext;

import lombok.Data;

import java.util.List;

@Data
public class MoneyInfo {
    List<String> date;
    String court;
    String paymentDay;
    String money;
    String interestRateMonth;
    String outTimeInterestRateMonth;
}
