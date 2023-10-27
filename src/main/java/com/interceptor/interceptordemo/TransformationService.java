package com.interceptor.interceptordemo;

import org.springframework.stereotype.Service;

@Service
public class TransformationService {
    public String testMethod(){
        return """
                {
                    "name": "Slava",
                    "age: 42
                }
                """;
    }
}
