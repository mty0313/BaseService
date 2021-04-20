package top.mty.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mty.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * person Mapper 接口
 * </p>
 *
 * @author mty
 * @since 2021-04-19
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
