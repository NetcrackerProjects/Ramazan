public class TextMessage implements Message{

    private String text;

    private static String endMessage = ".";

    public String getTextMessage() {
        return text;
    }

    public void setMessage(String textMessage) {
        text = textMessage;
    }

    public boolean isEnd() {
        return text.equals(endMessage);
    }

    public static String getEndMessage(){
        return endMessage;
    }
}
