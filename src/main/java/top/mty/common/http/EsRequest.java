package top.mty.common.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import top.mty.utils.R;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author mty
 * @Date 2021/4/19
 * @Description es的request封装
 */
@Component
public class EsRequest {

    private static final Logger logger = LoggerFactory.getLogger(EsRequest.class);

    @Resource
    RestTemplate restTemplate;

    @Value("${esUrl}")
    String esUrl;

    public R request(Map<String, Object> params) {
        try {
            String queryUrl = params.get("queryUrl").toString();
            if (StringUtils.isEmpty(queryUrl)) {
                logger.error("request没有收到queryUrl");
                return R.error();
            }
        } catch (Exception e) {
            logger.error("request没有收到queryUrl");
            return R.error();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String url = esUrl + "/api/base/pagingQueryV2";
        logger.info("es calling: url: " + url);
        try {
            ResponseEntity<JSONObject> response = restTemplate
                    .postForEntity(url, params, JSONObject.class, httpHeaders);
            logger.info(String.valueOf(response));
            return R.ok(response.getBody());
        } catch (Exception e) {
            logger.error("es error");
            return R.error();
        }
    }

    public R save(String modelName, List<?> dataList) {
        if (dataList.size() == 0) {
            return R.error();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = esUrl + "/api/base/batchSave/" + modelName;
        logger.info("batch save to es: " + url);
        try {
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, dataList, JSONObject.class, headers);
            return R.ok(response);
        } catch (Exception e) {
            logger.error("es save failure");
            return R.error();
        }
    }

}
