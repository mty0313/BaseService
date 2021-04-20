package top.mty.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * es查询参数配置
 * </p>
 *
 * @author mty
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchParamConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 接口
     */
    private String url;

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
