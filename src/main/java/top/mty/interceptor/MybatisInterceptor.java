package top.mty.interceptor;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.mty.common.http.EsRequest;
import top.mty.utils.R;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * @Author mty
 * @Date 2021/8/10 15:06
 * @Description 拦截mybatis, 同步es
 */
@Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class MybatisInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Resource
    EsRequest esRequest;

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        try {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            String sqlId = mappedStatement.getId();
            logger.info("sqlId: {}", sqlId);
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            MapperMethod.ParamMap<?> parameter = (MapperMethod.ParamMap<?>) invocation.getArgs()[1];
            Object obj = parameter.get("param1");
            String[] classNameSplit = obj.getClass().getName().split("\\.");
            String indexName = classNameSplit[classNameSplit.length - 1];
            logger.info("sqlCommandType: {}", sqlCommandType);
            R r = esRequest.save(indexName, Collections.singletonList(obj));
            logger.info("拦截器拦截数据库更新操作, 更新ES结果: {}", r);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return invocation.proceed();
    }
}
