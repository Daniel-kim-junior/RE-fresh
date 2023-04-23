package refresh.ManageSystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Daniel kim
 *
 * SwaggerConfig : Swagger 등록 클래스 basePackage에 있는 모든 api들을 등록 .any()
 * URL 또한 모든 URL을 등록
 *
 * 2023-04-12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())//Swagger API 문서로 만들기 원하는 basePackage 경로
                .paths(PathSelectors.ant("/*/**"))    //URL 경로를 지정하여 해당 URL에 해당하는 요청만 SWAGGER로 만듦
                .build();
    }
}
