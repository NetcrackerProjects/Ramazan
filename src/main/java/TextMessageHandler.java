public class TextMessageHandler implements MessageHandler {


    public Message getMessage(String textMessage) {
        Message message = new TextMessage();
        message.setMessage(textMessage);
        return message;
    }
}
