package app.cta4j.task;

import app.cta4j.service.MessageService;
import app.cta4j.service.StatusService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public final class BotTask {
    private final StatusService service;

    private final MessageService messageService;

    @Autowired
    public BotTask(StatusService service, MessageService messageService) {
        this.service = Objects.requireNonNull(service);

        this.messageService = Objects.requireNonNull(messageService);
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void runScheduledTask() {
        String status = this.service.getStatus();

        if (status == null) {
            return;
        }

        this.messageService.postStatus(status);
    }

    @PostConstruct
    public void runOnStartup() {
        this.runScheduledTask();
    }
}
