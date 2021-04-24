package top.yangzefeng.integration.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 14:32
 */
public class SpringContextHelper implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
