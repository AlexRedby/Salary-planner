package su.opencode.project.web.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ConsoleMailSenderImpl implements MyMailSender {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public void send(String to, String subject, String text) {
        System.out.println(ANSI_GREEN + "==================================================");
        System.out.println("Message to: " + to + "\nSubject: " + subject + "\n" + text);
        System.out.println("==================================================" + ANSI_RESET);
    }
}
