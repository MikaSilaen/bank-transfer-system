package id.co.task.banktransfersystem.config;

import id.co.task.banktransfersystem.config.variable.AppConstant;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */

@Configuration
public class OpenAPIConfig {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONF)

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("bank-transfer-system")
                .version("1")
            )
            .externalDocs(new ExternalDocumentation()
                .description("Confluence - Service Documentation"));
                //.url("https://www.atlassian.com/"));
    }
}
