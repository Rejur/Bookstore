package com.ssm.document;

import com.ssm.jopo.BaseResponse;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2 //Loads the spring beans required by the framework
public class MySwaggerConfig {


    private ApiInfo apiInfo() {
        Contact contact = new Contact("Artribiatry", "https://github.com/ichenkaihua/ssm-easy-template", "799942558@qq.com");
        return new ApiInfoBuilder()
                .title("ssm_easy_template API接口")
                .description("课设练习")
                .contact(contact)

                .version("3.0")
                .build();
    }

    private Predicate<String> userPaths() {
        return or(
                regex("/users.*")
        );
    }

    @Bean
    public Docket userApi() {
        Set<String> set = new HashSet<String>();

        com.fasterxml.classmate.TypeResolver typeResolver = new com.fasterxml.classmate.TypeResolver();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .apiInfo(apiInfo())
                .select()
                .paths(userPaths())
                .build().useDefaultResponseMessages(false)
                .genericModelSubstitutes(BaseResponse.class)
                .forCodeGeneration(true)


                ;

        return docket;
    }

}