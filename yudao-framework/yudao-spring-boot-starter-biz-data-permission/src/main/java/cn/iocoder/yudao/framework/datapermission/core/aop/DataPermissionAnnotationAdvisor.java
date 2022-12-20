package cn.iocoder.yudao.framework.datapermission.core.aop;

import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * {@link cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission} 注解的 Advisor 实现类，参考：https://blog.csdn.net/universsky2015/article/details/115370405
 *
 * @author 芋道源码
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class DataPermissionAnnotationAdvisor extends AbstractPointcutAdvisor {

    private final Advice advice;

    private final Pointcut pointcut;

    public DataPermissionAnnotationAdvisor() {
        // 以下表示当类与方法都添加了 DataPermission 注解时，走 DataPermissionAnnotationInterceptor 拦截器
        this.advice = new DataPermissionAnnotationInterceptor();
        this.pointcut = this.buildPointcut();
    }

    protected Pointcut buildPointcut() {
        Pointcut classPointcut = new AnnotationMatchingPointcut(DataPermission.class, true);
        Pointcut methodPointcut = new AnnotationMatchingPointcut(null, DataPermission.class, true);
        return new ComposablePointcut(classPointcut).union(methodPointcut);
    }

}
