package shuaicj.example.rest.i18n;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Get localed messages.
 *
 * @author shuaicj 2017/08/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetMessageTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void test() {
        check(null, "China");
        check("zh-CN", "中国");
        check("fr-FR", "China");
    }

    private void check(String locale, String message) {
        HttpHeaders headers = new HttpHeaders();
        if (locale != null) {
            headers.set(HttpHeaders.ACCEPT_LANGUAGE, locale);
        }
        ResponseEntity<String> e = rest.exchange("/my-country", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        assertThat(e.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(e.getBody()).isEqualTo(message);
    }
}