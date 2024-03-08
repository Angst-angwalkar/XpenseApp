package io.evilsking.ExpenseService.Helpers;

import io.evilsking.ExpenseService.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class Helpers {


    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    public Date getCurrentDate(){
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

}
