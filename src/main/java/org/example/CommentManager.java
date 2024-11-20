package org.example;

import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private static final String BASE_FOLDER = "comments/";
    private static final String COMMENT_FILE_SUFFIX = "_comments.txt";

    // 添加评论
    public static void addComment(int articleId, String content,Article article) {
        List<Comment> comments = loadComments(articleId,article.getTimes().TimeMilliseconds()+"_"+article.getTitle());
        int newId = comments.size() + 1;
        Times times = TimesTamp.timestamp();
        Comment newComment = new Comment(newId, articleId, content,times);
        FileHandler.appendFile(BASE_FOLDER, article.getTimes().TimeMilliseconds()+"_"+article.getTitle(), COMMENT_FILE_SUFFIX, newComment.toFileString());
    }

    // 添加回复评论
    public static void addreplyComment(int articleId, int parentId, String username, String content,Article article) {
        List<String> lines = FileHandler.readFile(BASE_FOLDER, article.getTimes().TimeMilliseconds()+"_"+article.getTitle(), COMMENT_FILE_SUFFIX);
        List<String> updatedLines = new ArrayList<>();
        boolean replyAdded = false;

        for (String line : lines) {
            updatedLines.add(line);
            Comment comment = Comment.fromString(line);
            if (!replyAdded && comment.getId() == parentId) {
                int newId = 0;
                String replyContent = username + " 回复: " + content;
                Times times = TimesTamp.timestamp();
                Comment reply = new Comment(newId, articleId, replyContent,times);
                updatedLines.add(reply.toFileString());
                replyAdded = true;
            }
        }

        if (!replyAdded) {
            System.out.println("未找到父评论，回复失败！");
        }
        else {
            FileHandler.writecommentFile(BASE_FOLDER, article.getTimes().TimeMilliseconds()+"_"+article.getTitle(), COMMENT_FILE_SUFFIX, updatedLines);
        }
    }

    // 加载评论列表
    public static List<Comment> loadComments(int articleId,String title) {
        List<Comment> comments = new ArrayList<>();
        List<String> lines = FileHandler.readFile(BASE_FOLDER, title, COMMENT_FILE_SUFFIX);
        for (String line : lines) {
            Comment comment = Comment.fromString(line);
            if (comment != null) {
                comments.add(comment);
            }
        }
        return comments;
    }
}
