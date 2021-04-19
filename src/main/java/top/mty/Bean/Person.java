package top.mty.Bean;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * person
 * </p>
 *
 * @author mty
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 住址
     */
    private String addr;

    /**
     * 电话
     */
    private String mobile;

    /**
     * create
     */
    private LocalDateTime createDate;

    /**
     * update
     */
    private LocalDateTime updateDate;


}
