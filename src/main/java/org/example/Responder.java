package org.example;

import java.util.Objects;

public class Responder {
    private Long chatId;
    private String userName;
    private int uses;

    public Responder(Long chatId,String userName){
        this.chatId  = chatId;
        this.userName = userName;
        this.uses++;
    }

    public void updateUses(){
        this.uses++;
    }


    public long getChatId() {
        return chatId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUses() {
        return uses;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Responder responder = (Responder) obj;
        return chatId == responder.chatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }

    @Override
    public String toString() {
        return "Responder{" +
                "chatId=" + chatId +
                ", userName='" + userName + '\'' +
                ", uses=" + uses +
                '}';
    }
}
