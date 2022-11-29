package bcsoft.it.glam.service;

import bcsoft.it.glam.exception.EmailException;
import bcsoft.it.glam.model.EmailDiNotifica;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    void sendMail(EmailDiNotifica emailDiNotifica) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("glamCiao@gmail.com");
            mimeMessageHelper.setTo(emailDiNotifica.getRecipiente());
            mimeMessageHelper.setSubject(emailDiNotifica.getSoggetto());
            mimeMessageHelper.setText(emailDiNotifica.getCorpo());

        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Email è inviato");
        } catch (MailException e) {
            log.error("Email non è inviato", e);
            throw new EmailException("Email non è inviato a " + emailDiNotifica.getRecipiente(), e);
        }
    }
}

