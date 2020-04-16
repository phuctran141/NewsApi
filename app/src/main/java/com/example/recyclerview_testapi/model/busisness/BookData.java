package com.example.recyclerview_testapi.model.busisness;

public class BookData {
    private String name;
    private String Imge;
    private String author;
    private String url;
    private String Description;
    private String Content;
    private String Title;
    private String PublisherAt;

    public BookData() {
    }

    public String getPublisherAt() {
        return PublisherAt;
    }

    public void setPublisherAt(String publisherAt) {
        PublisherAt = publisherAt;
    }

    public BookData(String name, String imge, String author, String url, String description, String content, String title, String publisherAt) {
        this.name = name;
        Imge = imge;
        this.author = author;
        this.url = url;
        Description = description;
        Content = content;
        Title = title;
        PublisherAt = publisherAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImge() {
        return Imge;
    }

    public void setImge(String imge) {
        Imge = imge;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
