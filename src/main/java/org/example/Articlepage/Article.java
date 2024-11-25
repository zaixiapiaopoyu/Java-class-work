package org.example.Articlepage;

import org.example.AllCanUse.Times;

/**
 * Article 类表示一篇文章的实体，包括文章的 ID、标题、内容、作者、点赞量以及发表时间。
 * 提供对文章属性的访问和操作方法，例如获取信息和点赞功能。
 */

public class Article {
    private int id;            //文章 id
    private String title;      // 文章标题
    private String content;    // 评论
    private String author;     //作者
    private int likes;         // 点赞量
    private Times times;

    /**
     * 构造函数，用于初始化 Article 对象。
     *
     * @param id      文章 ID
     * @param title   文章标题
     * @param content 文章内容
     * @param author  作者名称
     * @param likes   点赞量
     * @param times   发表时间（Times 对象）
     */

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

}


