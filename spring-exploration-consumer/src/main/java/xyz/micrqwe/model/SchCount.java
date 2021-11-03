package xyz.micrqwe.model;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author d
 * @description (SchCount)实体类
 * @date 2021-11-03 17:27:07
 * @since 1.0
 */
@Getter
@Setter
@TableName("sch_count")
public class SchCount implements Serializable {
// TODO private static final long serialVersionUID = -1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer schid;

    private Integer counts;

    private String memo;

    /**
     * property name constant for {@link SchCount#schid}
     */
    public static final String PROPERTY_NAME_SCHID = "schid";
    /**
     * property name constant for {@link SchCount#counts}
     */
    public static final String PROPERTY_NAME_COUNTS = "counts";
    /**
     * property name constant for {@link SchCount#memo}
     */
    public static final String PROPERTY_NAME_MEMO = "memo";

}
