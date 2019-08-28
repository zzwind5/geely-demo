package com.jie.demo.demodbmove;

import com.jie.demo.demodbmove.service.MoveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoDbMoveApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(DemoDbMoveApplication.class, args);

        MoveService bean = ioc.getBean(MoveService.class);
        bean.move();
    }

}
