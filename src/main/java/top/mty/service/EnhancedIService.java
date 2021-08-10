package top.mty.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author mty
 * @Date 2021/8/5 11:29
 * @Description 增强版IService
 */
public interface EnhancedIService<T> extends IService<T> {
    boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
}
