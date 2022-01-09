package com.atguigu.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Car;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 1、配置类里面使用Bean标注在方法上给容器注册组件,默认也是单实例的
 * <p>
 * 2、配置类本身也是组件
 * <p>
 * 3、proxyBeanMethods: 代理bean的方法（2.0最大的不同）
 * Full(proxyBeanMethods = true)
 * Lite(proxyBeanMethods = true)
 * 组件依赖
 * lite模式不检查资源是否存在，启动快，都是独立的资源，没有依赖
 * <p>
 * 4、@Import({User.class, DBHelper.class})
 * 给容器中自动创建出这两个类型的组件,默认组件的名字就是 全类名
 * <p>
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置，加载之前的配置文件的方式
 */

@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类，相当于一个配置文件

//@ImportResource("classpath:beans.xml")

@EnableConfigurationProperties({Car.class})
//1、开启Car配置绑定功能
//2、把这个Car这个组件自动注册到容器中

public class MyConfig {

    /**
     * 外部无论对配置类中的这个组作注册方法调用多少次获取的都是之前注册容器中的单实例对象
     *
     * @return
     */
    //@ConditionalOnBean(name = "tom")
    @Bean //给容器添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器的实例
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom") //自定义组件名
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }

//    @Bean
//    //自己配一个字符编码解析，原来的就不生效
//    public CharacterEncodingFilter filter() {
//        return null;
//    }
}
