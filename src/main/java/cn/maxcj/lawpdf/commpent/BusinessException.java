package cn.maxcj.lawpdf.commpent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 4204180803074033058L;
    private String code;
    private String errorMsg;
    private String data;

    public BusinessException(String message) {
        super(message);
        this.code = "";
        this.errorMsg = message;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.errorMsg = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = "";
        this.errorMsg = message;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.errorMsg = message;
    }

    public BusinessException(String code, String message, String data) {
        super(message);
        this.code = code;
        this.errorMsg = message;
        this.data = data;
    }
}

