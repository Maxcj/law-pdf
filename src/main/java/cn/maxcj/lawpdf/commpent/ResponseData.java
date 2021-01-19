package cn.maxcj.lawpdf.commpent;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "000000";
    /**
     * Error messages
     */
    private String msg = "";
    /**
     * Result data
     */
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page page;

    public ResponseData(String code) {
        this.code = code;
    }

    public ResponseData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(T data) {
        this.data = data;
    }

}
