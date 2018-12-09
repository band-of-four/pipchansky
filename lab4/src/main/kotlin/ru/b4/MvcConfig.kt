package ru.b4

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.view.InternalResourceViewResolver


@EnableWebMvc
@Configuration
class MvcConfig : WebMvcConfigurer {

  override fun addViewControllers(registry: ViewControllerRegistry) {
    super.addViewControllers(registry)
    registry.apply {
      addViewController("/index").setViewName("index")
      addViewController("/").setViewName("index")
      addViewController("/graph").setViewName("graph")
      addViewController("/login").setViewName("login")
    }
  }

  @Bean
  fun viewResolver() = InternalResourceViewResolver().apply {
    setPrefix("/")
    setSuffix(".html")
  }

}
