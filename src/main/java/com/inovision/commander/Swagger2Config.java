package com.inovision.commander;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket swaggerConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                //.paths(PathSelectors.ant("/boot-commander/*"))
                .apis(RequestHandlerSelectors.basePackage("com.inovision.commander"))
                .build();

    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Boot Commander API", "REST API for Boot Commander Application", "1.0", "Free to use",
                new springfox.documentation.service.Contact("Kunal Patil", "http://www.inovisionsoftware.com", "contact@inovisionsoftware.com"),
                "Apache License", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
    }

}
