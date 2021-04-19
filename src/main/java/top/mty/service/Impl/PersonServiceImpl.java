package top.mty.service.Impl;

import top.mty.bean.Person;
import top.mty.mapper.PersonMapper;
import top.mty.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
