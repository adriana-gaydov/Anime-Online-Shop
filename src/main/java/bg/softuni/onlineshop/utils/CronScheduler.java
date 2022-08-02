package bg.softuni.onlineshop.utils;

import bg.softuni.onlineshop.service.NewsletterService;
import bg.softuni.onlineshop.service.OrderService;
import bg.softuni.onlineshop.service.IpStatsService;
import bg.softuni.onlineshop.service.impl.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CronScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);
    private final IpStatsService ipStatsService;
    private final OrderService orderService;
    private final EmailServiceImpl emailService;
    private final NewsletterService newsletterService;

    public CronScheduler(IpStatsService ipStatsService, OrderService orderService, EmailServiceImpl emailService, NewsletterService newsletterService) {
        this.ipStatsService = ipStatsService;
        this.orderService = orderService;
        this.emailService = emailService;
        this.newsletterService = newsletterService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void dropVisitations() {
        this.ipStatsService.dropTable();
        LOGGER.info("The statistic database was refreshed successfully!");
    }

    @Scheduled(cron = "@monthly")
    public void dropOrders() {
        this.orderService.dropTable();
        LOGGER.info("The orders database was refreshed successfully!");
    }


//    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "@monthly")
    public void sendNewestDeals() {

        LOGGER.info("Sending deal mails!");
        List<String> allEmails = newsletterService.getAllEmails();

        if (allEmails.isEmpty()) {
            return;
        }

        allEmails.forEach(this.emailService::sendDealEmail);
    }
}
