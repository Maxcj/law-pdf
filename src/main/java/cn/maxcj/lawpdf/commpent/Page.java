package cn.maxcj.lawpdf.commpent;

import lombok.Data;

@Data
public class Page {
    Integer page = 1;
    Integer pageSize = 20;
    Long total = 0L;
    Integer totalPage = 0;
}
