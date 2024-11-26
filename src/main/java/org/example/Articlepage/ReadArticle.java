package org.example.Articlepage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;
import org.example.Commentpage.Comment;
import org.example.Commentpage.ManageComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * ReadArticle 类用于显示文章内容，并允许用户进行点赞、评论及回复操作。
 */

public class ReadArticle {

    private final String ARTICLE_FOLDER = "data/articles/";
    private final String FILE_SUFFIX = "_article.txt";

    /**
     * 显示文章内容及相关操作。
     *
     * @param scanner    输入扫描器
     * @param article    当前显示的文章对象
     * @param currentUser 当前登录的用户
     */

    public void readArticle(Scanner scanner, Article article, String currentUser) {
        while (true) {
            displayArticleDetails(article);
            displayComments(article);

            System.out.println("\n1. 输入 'good' 点赞");
            System.out.println("2. 输入 'comments' 评论");
            System.out.println("3. 输入 'reply' + 序号 回复评论（需登录）");
            System.out.println("4. 输入 0 返回文章列表");
            System.out.println("\n 请选择：");

            String command = scanner.nextLine();
            if (command.equals("0")) {
                break;
            }
            else if (command.equals("good")) {
                handleLike(article);
            }
            else if (command.equals("comments")) {
                handleComment(scanner, article, currentUser);
            }
            else if (command.startsWith("reply")) {
                handleReply(scanner, command, article, currentUser);
            }
            else {
                System.out.println("无效命令！");
                pause(2000);
            }
        }
    }

    /**
     * 显示文章的详细信息，包括标题、作者、发布时间、内容和点赞量。
     *
     * @param article 当前显示的文章对象
     */

    private void displayArticleDetails(Article article) {
        NewPage newPage = new NewPage();
        newPage.newpage();

        load load = new load();
        List<Article> articles = load.loadArticles();
        Article newarticle = articles.get(article.getId() - 1);

        System.out.println("\n文章内容：");
        System.out.println("题目：" + newarticle.getTitle());
        System.out.println("作者：" + newarticle.getAuthor());
        System.out.println("发布时间：" + newarticle.getTimes().show());

        String formattedContent = formatContent(newarticle.getContent(), 50);
        System.out.println("内容：\n  " + formattedContent);
        System.out.println("点赞量：" + newarticle.getLikes());
    }

    /**
     * 格式化文章内容，每行显示固定长度。
     *
     * @param content    文章内容
     * @param lineLength 每行的字符长度
     * @return 格式化后的字符串
     */

    private String formatContent(String content, int lineLength) {
        StringBuilder formattedContent = new StringBuilder();
        for (int i = 0; i < content.length(); i += lineLength) {
            if (i + lineLength < content.length()) {
                formattedContent.append(content, i, i + lineLength);
            } else {
                formattedContent.append(content.substring(i));
            }
            formattedContent.append("\n");
        }
        return formattedContent.toString();
    }

    /**
     * 显示文章的所有评论。
     *
     * @param article 当前显示的文章对象
     */

    private void displayComments(Article article) {
        ManageComment manageComment = new ManageComment();
        List<Comment> comments = manageComment.LoadComments(article.getTimes().TimeMilliseconds() + "_" + article.getTitle());

        System.out.println("评论：");
        if (comments.isEmpty()) {
            System.out.println("暂无评论！");
        } else {
            for (Comment comment : comments) {
                if (comment.getId() != 0) {
                    System.out.println(comment.getId() + "、" + comment.getContent() + "\n        评论时间：" + comment.getTimes().show());
                } else {
                    System.out.println("    " + comment.getContent() + "\n        评论时间：" + comment.getTimes().show());
                }
            }
        }
    }

    /**
     * 点赞操作，增加文章的点赞数并保存。
     *
     * @param article 当前显示的文章对象
     */

    private void handleLike(Article article) {
        article.like();
        SaveArticle(article);
        System.out.println("点赞成功！");
        pause(1000);
    }

    /**
     * 添加评论操作。
     *
     * @param scanner     输入扫描器
     * @param article     当前显示的文章对象
     * @param currentUser 当前登录的用户
     */

    private void handleComment(Scanner scanner, Article article, String currentUser) {
        System.out.println("请输入评论内容：");
        String content = scanner.nextLine();
        ManageComment manageComment = new ManageComment();
        manageComment.AddComment(content, article, currentUser);
        System.out.println("评论成功！");
        pause(1000);
    }



    private void handleReply(Scanner scanner, String command, Article article, String currentUser) {
        if (currentUser == null) {
            System.out.println("请先登录！");
            pause(2000);
            return;
        }

        /**
         * 回复评论操作。
         *
         * @param scanner     输入扫描器
         * @param command     用户输入的命令
         * @param article     当前显示的文章对象
         * @param currentUser 当前登录的用户
         */

        String[] parts = command.split(" ");
        if (parts.length != 2) {
            System.out.println("命令格式错误！");
            pause(2000);
            return;
        }

        int commentIndex;
        try {
            commentIndex = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("无效评论序号！");
            pause(2000);
            return;
        }

        ManageComment manageComment = new ManageComment();
        List<Comment> comments = manageComment.LoadComments(article.getTimes().TimeMilliseconds() + "_" + article.getTitle());

        if (commentIndex > 0 && commentIndex <= comments.size() && comments.get(commentIndex - 1).getId() != 0) {
            System.out.println("请输入回复内容：");
            String reply = scanner.nextLine();
            manageComment.AddreplyComment(commentIndex, currentUser, reply, article);
            System.out.println("回复成功！");
            pause(1000);
        } else {
            System.out.println("无效评论序号！");
            pause(2000);
        }
    }

    /**
     * 暂停指定时间（毫秒）。
     *
     * @param milliseconds 暂停的时间
     */

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 保存文章内容到文件。
     *
     * @param article 需要保存的文章对象
     */

    public void SaveArticle(Article article) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + article.getTimes().print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

}