package xyz.micrqwe.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by le on 2017/5/9.
 */
@Getter
@Setter
public class PageResultMapper<T> {
    private Integer pageSize;
    private Integer total;
    private Integer pageNo;
    private T data;
}
