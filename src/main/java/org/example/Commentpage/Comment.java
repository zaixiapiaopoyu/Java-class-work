package org.example.Commentpage;

import org.example.AllCanUse.Times;

public class Comment {
    private int id;           // 评论ID
    private int articleId;    // 所属文章ID
    private String content;   // 评论内容
    private Times times;

    public Comment(int id,  String content, Times times) {
        this.id = id;
//        this.articleId = articleId;
        this.content = content;
        this.times = times;
    }

    public int getId() {
        return id;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }

    public Times getTimes() {
        return times;
    }

    // 格式化为文件保存的字符串
    public String toFileString() {
        return id + "\n"  + content + "\n" + times.print();
    }

    // 从字符串解析为 Comment 对象
    public static Comment fromString(String line) {
        try {
            String[] parts = line.split("\n", 3);
            int id = Integer.parseInt(parts[0].trim());
            String content = parts[1].trim();
            String time = parts[2].trim();
            String[] timeParts = time.split(":");
            int year = Integer.parseInt(timeParts[0].trim());
            int month = Integer.parseInt(timeParts[1].trim());
            int day = Integer.parseInt(timeParts[2].trim());
            int hour = Integer.parseInt(timeParts[3].trim());
            int minute = Integer.parseInt(timeParts[4].trim());
            int second = Integer.parseInt(timeParts[5].trim());

            Times times = new Times(year,month,day,hour, minute, second);
            return new Comment(id,  content,times);
        }
        catch (Exception e) {
            System.out.println("解析评论失败: " + line);
            return null;
        }
    }
}
