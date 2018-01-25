package ro.ubb.iStudentBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Cata on 12/6/2017.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IStudentBlog {
    public static void main(final String[] args) {
        SpringApplication.run(IStudentBlog.class, args);
    }
}
