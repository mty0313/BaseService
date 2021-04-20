package top.mty.service;

import top.mty.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import top.mty.utils.R;
import java.util.Map;

/**
 * <p>
 * person 服务类
 * </p>
 *
 * @author mty
 * @since 2021-04-19
 */
public interface IPersonService extends IService<Person> {
    /**
     * es数据saveOrUpdate
     * @param params
     * @return
     */
    R saveOrUpdateEs(Map<String, Object> params);
}
