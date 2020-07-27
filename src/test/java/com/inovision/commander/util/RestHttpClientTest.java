package com.inovision.commander.util;

import com.inovision.commander.model.Host;
import com.inovision.commander.model.TestCase;
import com.inovision.commander.model.TestResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

public class RestHttpClientTest {

    @Test
    public void testInit() throws Exception {
        RestHttpClient restHttpClient = new TestRestHttpClient();
        assertNotNull(restHttpClient);

        TestCase testCase = new TestCase();
        testCase.setMethod(HttpMethod.GET);
        testCase.setRestUrl("/todos/1");
        Host host = new Host();
        host.setHostName("jsonplaceholder.typicode.com");
        host.setSecureHttp(true);
        TestResult result = restHttpClient.doHttpGet(testCase, host);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    private static class TestRestHttpClient extends RestHttpClient {

        public TestRestHttpClient() throws Exception {
            super.initialize();
        }
    }
}
