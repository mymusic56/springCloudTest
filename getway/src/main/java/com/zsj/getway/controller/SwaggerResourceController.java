package com.zsj.getway.controller;

import com.zsj.getway.config.CustomSwaggerResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;

@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    private SwaggerResourcesProvider swaggerResourcesProvider;

    @Autowired
    public SwaggerResourceController(CustomSwaggerResourceProvider swaggerResources) {
        this.swaggerResourcesProvider = swaggerResources;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }


    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerResourcesProvider.get(), HttpStatus.OK);
    }
}
