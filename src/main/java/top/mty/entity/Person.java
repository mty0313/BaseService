package top.mty.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import top.mty.annotation.DefineAnnotation;

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

    @Id
    private Integer id;

    /**
     * 姓名
     */
    @DefineAnnotation(isRequire = true)
    private String name;

    /**
     * 年龄
     */
    @DefineAnnotation(regular = "^[0-9]{10}$", defaultValue = "-1")
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
