package com.example.msoperation;

import com.example.msoperation.dao.OperationRepository;
import com.example.msoperation.service.OperationService;
import com.example.msoperation.entities.Account;
import com.example.msoperation.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class msOperationApplication implements CommandLineRunner {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private OperationService operationService;

    public static void main(String[] args) {
        SpringApplication.run(msOperationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Operation operation1 = operationService.deposit( (long) 1, 5200.0);
        Operation operation2 = operationService.withdraw( (long) 2, 100.0);
        Operation operation3 = operationService.transfer((long) 1, (long)2, 500.0);
        operation1 = operationRepository.save(operation1);
        operation2 = operationRepository.save(operation2);
        operation3 = operationRepository.save(operation3);
    }
}
