package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.mail.MailSendHistoryRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // @Mock 사용시 클래스 상단에 작성해주어야 정상 동작한다.
class MailServiceTest {

//    @Spy // 실제 객체 기반으로 Stubbing이 된다.
    @Mock // @Mock 사용시 a(), b(), c() 호출이 전부 무시된다.
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks // 생성자를 보고 @Mock 으로 생성된 객체들을 주입해준다(DI와 비슷하다)
    private MailService mailService;

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMail() {
        // given
        BDDMockito.given(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
                        .willReturn(true);

//        Mockito.when(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(true);

//        @Spy 사용시 실제 객체를 기반으로 하기 때문에 특정 메서드만 Stubbing 가능하다.
//        doReturn(true)
//                .when(mailSendClient)
//                .sendMail(anyString(), anyString(), anyString(), anyString());

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        verify(mailSendClient, times(1)).sendMail(anyString(), anyString(), anyString(), anyString());

        assertThat(result).isTrue();
    }

}