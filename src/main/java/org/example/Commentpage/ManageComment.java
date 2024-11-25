package org.example.Commentpage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.Times;
import org.example.AllCanUse.TimesTamp;
import org.example.Articlepage.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理评论的类，提供添加评论、回复评论以及加载评论等功能。
 */

public class ManageComment {

    private static final String BASE_FOLDER = "data/comments/";
    private static final String COMMENT_FILE_SUFFIX = "_comments.txt";

    /**
     * 添加评论到指定文章
     *
     * @param articleId 文章ID
     * @param content 评论内容
     * @param article 文章对象
     * @param username 评论用户名（如果没有则为匿名用户）
     */


    public static void addComment(int articleId, String content, Article article, String username) {
        List<Comment> comments = loadComments(article.getTimes().TimeMilliseconds() + "_" + article.getTitle());
        int len = 0;
        for(Comment comment : comments) {
            if(comment.getId() != 0){
                len++;
            }
        }
        int newId = len + 1;
        Times times = TimesTamp.timestamp();
        Comment newComment;
        if(username != null) {
            newComment = new Comment(newId, username + " : " + content, times);
        }
        else{
            newComment = new Comment(newId, "匿名用户 : " + content, times);
        }
        FileHandler.appendFile(BASE_FOLDER, article.getTimes().TimeMilliseconds() + "_" + article.getTitle(), COMMENT_FILE_SUFFIX, newComment.toFileString());
    }

    /**
     * 添加对某条评论的回复
     *
     * @param parentId 父评论ID
     * @param username 回复评论的用户名
     * @param content 回复内容
     * @param article 文章对象
     */
    public static void addreplyComment( int parentId, String username, String content, Article article) {
        List<String> lines = FileHandler.readFile(BASE_FOLDER, article.getTimes().TimeMilliseconds() + "_" + article.getTitle(), COMMENT_FILE_SUFFIX);
        List<String> updatedLines = new ArrayList<>();
        boolean replyAdded = false;

        int Linecount = 0;
        String[] threeline = new String[3];
        for (String line : lines) {
            threeline[Linecount] = line;
            Linecount++;
            if(Linecount == 3){
                Linecount = 0;
                updatedLines.add(threeline[0] + "\n" + threeline[1] + "\n" + threeline[2]);
                Comment comment = Comment.fromString(threeline[0] + "\n" + threeline[1] + "\n" + threeline[2]);
                if (!replyAdded && comment.getId() == parentId) {
                    int newId = 0;
                    String replyContent = username + " 回复: " + content;
                    Times times = TimesTamp.timestamp();
                    Comment reply = new Comment(newId, replyContent, times);
                    updatedLines.add(reply.toFileString());
                    replyAdded = true;
                }
            }
        }

        if (!replyAdded) {
            System.out.println("未找到父评论，回复失败！");
        }
        else {
            FileHandler.writecommentFile(BASE_FOLDER, article.getTimes().TimeMilliseconds() + "_" + article.getTitle(), COMMENT_FILE_SUFFIX, updatedLines);
        }
    }

    /**
     * 加载指定文章的评论列表
     *
     * @param title 文章标题
     * @return 评论列表
     */

    public static List<Comment> loadComments(String title) {
        List<Comment> comments = new ArrayList<>();
        List<String> lines = FileHandler.readFile(BASE_FOLDER, title, COMMENT_FILE_SUFFIX);
        int Linecount = 0;
        String[] threeline = new String[3];
        for (String line : lines) {
            threeline[Linecount] = line;
            Linecount++;
            if(Linecount == 3){
                Linecount = 0;
                Comment comment = Comment.fromString(threeline[0] + "\n" + threeline[1] + "\n" + threeline[2]);
                if (comment != null) {
                    comments.add(comment);
                }
            }
        }
        return comments;
    }
}
