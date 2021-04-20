package top.mty.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mty.entity.SearchParamConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * es查询参数配置 Mapper 接口
 * </p>
 *
 * @author mty
 * @since 2021-04-20
 */
@Mapper
public interface SearchParamConfigMapper extends BaseMapper<SearchParamConfig> {

}
