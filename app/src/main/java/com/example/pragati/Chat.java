package com.example.pragati;

public class Chat {
    public String text;
    public int user;
    public int image;

    public Chat(String text, int user, int image) {
        this.text = text;
        this.user = user;
        this.image = image;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
