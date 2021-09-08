package xyz.micrqwe.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author fyt
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String fileName;

    private Integer rowNum;

    private Integer columnNum;

    private String columnName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
