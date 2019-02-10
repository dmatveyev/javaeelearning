package jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(mappedName = "jms/javaee/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/javaee/Topic")
})
public class Consumer implements MessageListener {

    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:/jms/javaee/Topic")
    private Queue queue;

    @Override
    public void onMessage(Message message) {
        System.out.println("Сообщение получено...");
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        context.createProducer().send(queue, "Сообщение отправлено");
    }
}
