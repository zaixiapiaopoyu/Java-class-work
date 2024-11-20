package org.example;

public class Article {
    private int id;            //文章 id
    private String title;      // 文章标题
    private String content;    // 评论
    private String author;     //作者
    private int likes;         // 点赞量
    private Times times;

    public Article(int id, String title, String content, String author, int likes, Times times) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.times = times;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public Times getTimes() {
        return times;
    }

    public void like() {
        likes++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + "," + title + "," + content + "," + author + "," + likes;
    }

//    public static Article fromString(String line) {
//        String[] parts = line.split(",");
//        return new Article(
//                Integer.parseInt(parts[0]),
//                parts[1],
//                parts[2],
//                parts[3],
//                Integer.parseInt(parts[4])
//        );
//    }
}

