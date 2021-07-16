package top.mty.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * <p>
 * es查询参数配置
 * </p>
 *
 * @author mty
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchParamConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 接口
     */
    private String url;

    /**
     * fieldName 别名
     */
    private String fieldNameAlias;

    /**
     * field_name
     */
    private String fieldName;

    /**
     * query_type
     */
    private String queryType;

    /**
     * 是否为必须的参数
     */
    private Boolean isRequired;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
