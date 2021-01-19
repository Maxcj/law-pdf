package cn.maxcj.lawpdf.commpent;

import com.github.pagehelper.Page;

public class ResponseUtil {

    public static final String SUCCESS_CODE = "000000";
    public static final String SUCCESS_MSG = "success";
    public static final String ERROR_CODE = "100000";
    public static final String BUSINESS_ERROR_CODE = "200000";
    public static final String CHECKR_ERROR_CODE = "200000";

    /**
     * Generate a response data of success
     *
     * @param data response data
     * @return
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * Generate a response data of success
     *
     * @param data response data
     * @return
     */
    public static <T> ResponseData<T> success(String msg, T data) {
        return new ResponseData<T>(SUCCESS_CODE, msg, data);
    }

    /**
     * Generate a response data of success
     *
     * @param page response data
     * @return
     */
    public static <E> ResponseData<Page<E>> successPage(Page<E> page) {
        ResponseData responseData = new ResponseData(SUCCESS_CODE, SUCCESS_MSG, page.getResult());
        cn.maxcj.lawpdf.commpent.Page janusPage = new cn.maxcj.lawpdf.commpent.Page();
        janusPage.setPage(page.getPageNum());
        janusPage.setPageSize(page.getPageSize());
        janusPage.setTotal(page.getTotal());
        janusPage.setTotalPage(page.getPages());
        responseData.setPage(janusPage);
        return responseData;
    }

    /**
     * Generate a response data of success with no data
     *
     * @return
     */
    public static ResponseData<Void> successVoid() {
        return success(null);
    }

    public static ResponseData<String> success() {
        return success(null);
    }

    /**
     * Generate a response data of fail
     *
     * @param code error code
     * @param msg  error msg
     * @return
     */
    public static ResponseData<Void> fail(String code, String msg) {
        return new ResponseData<>(code, msg);
    }

    public static <T> ResponseData<T> fail(String code, String msg, T data) {
        return new ResponseData<T>(code, msg, data);
    }

}
