package xyz.micrqwe.model;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author d
 * @description (ShardingTest1)实体类
 * @date 2021-11-03 14:15:26
 * @since 1.0
 */
//@Entity
//@Table(name = "sharding_test")
@Getter
@Setter
@TableName("sharding_test")
public class ShardingTest implements Serializable {
// TODO private static final long serialVersionUID = -1L;


    @TableId(value = "id", type = IdType.AUTO)
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Integer id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "age")
    private Integer age;

    /**
     * property name constant for {@link ShardingTest#id}
     */
    public static final String PROPERTY_NAME_ID = "id";
    /**
     * property name constant for {@link ShardingTest#name}
     */
    public static final String PROPERTY_NAME_NAME = "name";
    /**
     * property name constant for {@link ShardingTest#age}
     */
    public static final String PROPERTY_NAME_AGE = "age";

}
