package com.bbd.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(
      name = "Siya Nkuna",
      email = "sudosprogrammer@gmail.com"
    ),
    description = "OpenAi documentation for BeansWeChat",
    title = "BeansWeChat",
    version = "1.0.0",
    license = @License(
      name = "Bean Chat Co."
    ),
    termsOfService = "Terms of Service"
  ),
  servers = {
    @Server(
      description = "PROD ENV",
      url = "http://wechat-beans-app.eu-west-1.elasticbeanstalk.com"
    ),
    @Server(
      description = "LOCAL ENV",
      url = "http://localhost:8080"
    )
  }
)

public class OpenApiConfig {

}
