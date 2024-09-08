package vn.edu.usth.outlook;
public class Email_Sender {
    String sender;
    String subject;
    String content;
    String receiver;

    public Email_Sender(String sender, String subject, String content,String receiver) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.receiver = receiver;

    }


    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {return receiver;}



}
