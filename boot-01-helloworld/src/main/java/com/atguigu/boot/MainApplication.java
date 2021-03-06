package com.atguigu.boot;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类，主配置类
 *
 * @SpringBootApplication这是一个SpringBoot应用
 */
@SpringBootApplication(scanBasePackages = "com.atguigu")
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.atguigu.boot")
public class MainApplication {
    public static void main(String[] args) {
        //1、返回我们的IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //3、从容器中获取组件

        Pet tom01 = run.getBean("tom", Pet.class);

        Pet tom02 = run.getBean("tom", Pet.class);

        System.out.println("组件: " + (tom01 == tom02));//true

        String[] beanNamesForType1 = run.getBeanNamesForType(WebMvcProperties.class);
        System.out.println("=========" + beanNamesForType1.length);

        //4、com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$51f1e1ca@7ea4d397
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中
        //保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);//true


        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);

        System.out.println("用户的宠物" + (user01.getPet() == tom));

        //5、获取组件
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("=================");
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        DBHelper bean1 = run.getBean(DBHelper.class);
        System.out.println(bean1);

        boolean tom1 = run.containsBean("Tom");
        System.out.println("容器中Tom组件: " + tom1);
    }
}
