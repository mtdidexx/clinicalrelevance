package net.katrinka.clinicalrelevance.controller;

/**
 * See https://cloud.google.com/pubsub/docs/push#receiving_push_messages
 */
public class Body {
    private Message message;

    public Body() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Body{" +
                "message=" + message +
                '}';
    }

    class Message {
        private String messageId;
        private String publishTime;
        private String data;

        public Message(String messageId, String publishTime, String data) {
            this.messageId = messageId;
            this.publishTime = publishTime;
            this.data = data;
        }

        public Message() {
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "messageId='" + messageId + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
