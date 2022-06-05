package org.zjut.spydocuploadbrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SPyDocUploadBrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SPyDocUploadBrowserApplication.class, args);
    }

}
