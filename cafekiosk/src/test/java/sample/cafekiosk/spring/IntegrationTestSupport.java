package sample.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.client.mail.MailSendClient;

@SpringBootTest // 테스트 환경이 다르다면, Spring Boot 서버가 테스트를 실행할때마다 새로 실행된다.
@ActiveProfiles("test") // Profile이 다른 것도 테스트 환경이 다르다고 판단한다.
public abstract class IntegrationTestSupport {

    @MockBean
    protected MailSendClient mailSendClient;
}
