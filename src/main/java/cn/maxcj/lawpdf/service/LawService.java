package cn.maxcj.lawpdf.service;

import cn.maxcj.lawpdf.model.request.MoneyRequest;

import java.util.Map;

public interface LawService {

    void fillMoneyPdf(MoneyRequest request);
}
