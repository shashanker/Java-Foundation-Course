package com.epam.course.springboot.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Profile("release")
@Component
public class MyErrorPageRegistrar implements ErrorPageRegistrar {

  @Override
  public void registerErrorPages(ErrorPageRegistry registry) {
      registry.addErrorPages(
              createNotFoundErrorPage()
             /* ,createRuntimeExceptionPage(),
              createOtherErrorPage()*/
              );
  }

  private ErrorPage createNotFoundErrorPage() {
      return new ErrorPage(HttpStatus.NOT_FOUND, "/404");
  }

 
}