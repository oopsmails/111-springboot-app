package com.oopsmails.springboot.mockbackend.multiplecalls;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class ApiCallController {
    private static int pageSize = 4; //6, 3
    @Autowired
    private ApiMultipleCallsService apiMultipleCallsService;

    @GetMapping("/call-apis")
    public List<String> callApis() throws InterruptedException, ExecutionException {
//        List<String> urls = List.of("https://jsonplaceholder.typicode.com/posts/1",
//                "https://jsonplaceholder.typicode.com/comments/1");

        List<String> urls = new ArrayList<>();
        String url = "http://localhost:8080/backendmock/employee-api/page?pageNumber={0}&pageSize={1}";
        for (int i = 1; i < 5; i++) {
//            MessageFormat.format("There's an incorrect value \"{0}\" in column # {1}", x, y);
            urls.add(MessageFormat.format(url, i, pageSize));

        }
        return apiMultipleCallsService.callApis(urls);
    }
}
