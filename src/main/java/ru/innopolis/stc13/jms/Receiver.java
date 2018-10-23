package ru.innopolis.stc13.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public Receiver() {
        factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.setClientID("receiver");
            session = connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("itemsDaoResponse");
            consumer = session.createConsumer(destination);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() throws JMSException {
        TextMessage message = (TextMessage) consumer.receive();
        return message.getText();
    }
}
