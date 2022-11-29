package bcsoft.it.glam.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        return templateEngine;
    };

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine().process("mailTemplate", context);
    }
}
