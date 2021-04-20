package top.mty.service.impl;

import top.mty.entity.SearchParamConfig;
import top.mty.mapper.SearchParamConfigMapper;
import top.mty.service.ISearchParamConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * es查询参数配置 服务实现类
 * </p>
 *
 * @author mty
 * @since 2021-04-20
 */
@Service
public class SearchParamConfigServiceImpl extends ServiceImpl<SearchParamConfigMapper, SearchParamConfig> implements ISearchParamConfigService {

}
