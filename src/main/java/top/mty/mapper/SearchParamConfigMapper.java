package top.mty.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.mty.entity.SearchParamConfig;

/**
 * <p>
 * es查询参数配置 Mapper 接口
 * </p>
 *
 * @author mty
 * @since 2021-04-21
 */
@Mapper
public interface SearchParamConfigMapper extends BaseMapper<SearchParamConfig> {

}
