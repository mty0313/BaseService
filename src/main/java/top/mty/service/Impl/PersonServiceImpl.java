package top.mty.service.Impl;

import top.mty.bean.Person;
import top.mty.common.http.EsRequest;
import top.mty.mapper.PersonMapper;
import top.mty.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mty.utils.R;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * person 服务实现类
 * </p>
 *
 * @author mty
 * @since 2021-04-19
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    @Resource
    EsRequest esRequest;

    @Override
    public R saveOrUpdateEs(Map<String, Object> params) {
        try {
            List<Person> dataList = list();
            return esRequest.save("Person", dataList);
        } catch (Exception e) {
            return R.error();
        }
    }
}
