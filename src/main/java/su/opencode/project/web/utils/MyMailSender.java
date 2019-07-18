package su.opencode.project.web.utils;

public interface MyMailSender {
    void send(String to, String subject, String text);
}
