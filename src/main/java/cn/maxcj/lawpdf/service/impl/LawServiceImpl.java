package cn.maxcj.lawpdf.service.impl;

import cn.maxcj.lawpdf.common.Constants;
import cn.maxcj.lawpdf.config.EmailHandler;
import cn.maxcj.lawpdf.model.request.MoneyRequest;
import cn.maxcj.lawpdf.service.LawService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service("LawService")
public class LawServiceImpl implements LawService {

    @Autowired
    private EmailHandler emailHandler;

    @Override
    public void fillMoneyPdf(MoneyRequest request) {
        log.info("{}", JSON.toJSONString(request));
        if (Objects.isNull(request)) {
            return;
        }
        String templatePath = "pdf/moneyTemplate.pdf";
        PdfReader reader;
        ByteArrayOutputStream bos;
        try {
            String newFilePath = "pdf/temp";
            File f = new File(newFilePath,String.format("money-%s.pdf", UUID.randomUUID().toString()));
            if(!f.exists()){
                f.createNewFile();
            }
            OutputStream fos = new FileOutputStream(f);
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            // 使用中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<>();
            fontList.add(bf);
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            //fill data
            fillData(fields, request);
            //必须要调用这个，否则文档不会生成的
            ps.setFormFlattening(true);
            ps.close();
            //生成pdf路径存放的路径
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();
            emailHandler.sendEmail(Constants.MONEY_PDF_EMAIL_SUBJECT, Constants.MONEY_PDF_EMAIL_CONTENT, f.getPath(),
                    Boolean.FALSE, new String[]{request.getReceiverEmail()},new String[]{});
        } catch (DocumentException | IOException e) {
            log.error("{}", e.toString());
        }
    }

    public static void fillData(AcroFields fields, MoneyRequest request) {
        Map<String, String> data = JSON.parseObject(JSON.toJSONString(request), new TypeReference<Map<String, String>>() {
        });
        try {
            for (String key : data.keySet()) {
                String value = data.get(key);
                fields.setField(key, value);
            }
        } catch (Exception e) {
            log.error("{}", e.toString());
        }
    }
}
