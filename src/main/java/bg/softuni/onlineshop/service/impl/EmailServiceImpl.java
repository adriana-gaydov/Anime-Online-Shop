package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.view.ProductViewModel;
import bg.softuni.onlineshop.service.ProductService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final ProductService productService;

    public EmailServiceImpl(TemplateEngine templateEngine,
                            JavaMailSender javaMailSender, ProductService productService) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.productService = productService;
    }

    public void sendRegistrationEmail(
            String userEmail,
            String userName
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("rcoon_service@rcoon.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Welcome to Rcoon!");
            mimeMessageHelper.setText(generateMessageContentRegistration(userName), generateMessageContentRegistration(userName));

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMessageContentRegistration(String userName) {
        Context ctx = new Context();
        ctx.setVariable("userName", userName);
        return templateEngine.process("email/registration", ctx);
    }

    public void sendDealEmail(
            String userEmail
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("rcoon_service@rcoon.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Newest deals from Rcoon!");

            List<ProductViewModel> deals = this.productService.getNewestDeals();
            mimeMessageHelper.setText(generateMessageContentDeal(deals), generateMessageContentDeal(deals));

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContactMail(String userEmail, String name, String phone, String enquiry) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userEmail);
            mimeMessageHelper.setTo("rcoon_service@rcoon.com");
            mimeMessageHelper.setSubject("Enquiry");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Name: " + name + "\n")
                            .append("Phone: " + phone + "\n")
                                    .append("Message: " + enquiry);

            mimeMessageHelper.setText(stringBuilder.toString());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMessageContentDeal(List<ProductViewModel> deals) {
        Context ctx = new Context();
        ctx.setVariable("deals", deals);
        return templateEngine.process("email/deal", ctx);
    }

}