/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of test-app-service.
 *
 * test-app-service project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class that is responsible to start the service and exposes the functionality over the specified
 * port.
 *
 * @author Mahalingam Iyer
 */
@SpringBootApplication
public class TestAppServiceApplication {
    /**
     * Entry point method.
     *
     * @param args
     *         Arguments to the main program.
     */
    public static void main(String[] args) {
        SpringApplication.run(TestAppServiceApplication.class);
    }
}
