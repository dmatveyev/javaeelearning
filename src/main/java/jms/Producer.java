package jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class Producer {

    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:/jms/javaee/Topic")
    private Queue queue;

    public void sendMessage() {
        context.createProducer().send(queue, "Сообщение отправлено");
    }

}
