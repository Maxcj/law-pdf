package cn.maxcj.lawpdf.controller;

import cn.maxcj.lawpdf.commpent.ResponseData;
import cn.maxcj.lawpdf.commpent.ResponseUtil;
import cn.maxcj.lawpdf.model.request.MoneyRequest;
import cn.maxcj.lawpdf.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/")
public class Controller {

    @Autowired
    private LawService lawService;


    @PostMapping("/money")
    @CrossOrigin
    public ResponseData<String> test(@RequestBody MoneyRequest request) {
        lawService.fillMoneyPdf(request);
        return ResponseUtil.success("123");
    }

}
