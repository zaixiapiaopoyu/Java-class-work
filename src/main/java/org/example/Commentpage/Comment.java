package org.example.Commentpage;

import org.example.AllCanUse.Times;

/**
 * Comment 类用于表示评论对象，包含评论的基本信息，如 ID、内容和评论时间。
 */

public class Comment {
    private int id;           // 评论ID
    private int articleId;    // 所属文章ID
    private String content;   // 评论内容
    private Times times;

    /**
     * 构造方法，用于初始化 Comment 对象。
     *
     * @param id      评论ID
     * @param content 评论内容
     * @param times   评论时间
     */

    public Comment(int id,  String content, Times times) {
        this.id = id;
        this.content = content;
        this.times = times;
    }

    public Comment() {

    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Times getTimes() {
        return times;
    }

    /**
     * 将评论格式化为可以保存到文件中的字符串。
     *
     * @return 格式化后的字符串
     */

    public String toFileString() {
        return id + "\n"  + content + "\n" + times.print();
    }

    /**
     * 从字符串解析为 Comment 对象。
     *
     * @param line 从文件中读取的一行字符串
     * @return 解析得到的 Comment 对象
     */

    public Comment fromString(String line) {
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
