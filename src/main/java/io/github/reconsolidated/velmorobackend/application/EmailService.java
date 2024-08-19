package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.menuItem.MenuItem;
import io.github.reconsolidated.velmorobackend.domain.order.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("Zespół Velmoro <kontakt@velmoro.pl>");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Ustawienie treści jako HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendOrderConfirmationEmail(Order order, String emailTo) throws MessagingException {
        String subject = "ZAMÓWIENIE POKÓJ " + order.getRoomNumber() + " VELMORO";

        // Formatowanie kwoty z dwoma miejscami po przecinku
        String formattedTotal = String.format("%.2f zł", order.getTotal());

        // Zbudowanie treści wiadomości
        String body = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif;'>" +
                        "<h1 style='color: #28a745;'>ZAMÓWIENIE POKÓJ %s VELMORO</h1>" +
                        "<p>Dzień dobry!</p>" +
                        "<p>W systemie VELMORO właśnie pojawiło się zamówienie od <strong>%s</strong> z pokoju <strong>%s</strong>.</p>" +
                        "<p style='font-weight: bold; font-size: 1.5em;'>DOPISZ <strong>%s</strong> DO RACHUNKU pokoju <strong>%s</strong></p>" +
                        "<h2>Podsumowanie zamówienia:</h2>" +
                        "<p>%s</p>" +
                        "<p>Pozdrawiamy,</p>" +
                        "<p>Zespół Velmoro</p>" +
                        "</body>" +
                        "</html>",
                order.getRoomNumber(), order.getName(), order.getRoomNumber(), formattedTotal, order.getRoomNumber(), order.prettyPrintOrderEntries()
        );

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        messageHelper.setFrom("kontakt@mamzadanie.pl");
        messageHelper.setTo(emailTo);
        messageHelper.setSubject(subject);
        messageHelper.setText(body, true); // true indicates that the content is HTML

        mailSender.send(mimeMessage);
    }

}