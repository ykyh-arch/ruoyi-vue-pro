package cn.iocoder.yudao.framework.operatelog.config;

import cn.iocoder.yudao.framework.operatelog.core.aop.OperateLogAspect;
import cn.iocoder.yudao.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.iocoder.yudao.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import cn.iocoder.yudao.module.system.api.logger.OperateLogApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志更详细的记录可参考：https://github.com/mouzt/mzt-biz-log
 *
 */
@Configuration
public class YudaoOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
