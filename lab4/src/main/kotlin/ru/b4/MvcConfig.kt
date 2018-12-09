package ru.b4

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView


@EnableWebMvc
@Configuration
@ComponentScan("ru.b4")
class MvcConfig : WebMvcConfigurer {

  override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/")
  }

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
    setViewClass(JstlView::class.java)
    setPrefix("/static/")
    setSuffix(".html")
  }

}
