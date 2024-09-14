package server.submissions.services;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.submissions.dao.SubmissionDAO;
import server.submissions.models.Submission;
import server.submissions.producers.ConfigureRabbitMq;


@Service
public class SubmissionMessageService {
    private final RabbitTemplate rabbitTemplate;

    public SubmissionMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String pushSubmission(String message) {
        rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME, "myRoutingKey.messages",
                message);
        return "Message(" + message + ")" + " has been produced.";
    }


}
