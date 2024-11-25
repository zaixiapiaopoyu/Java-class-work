package org.example.Articlepage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;
import org.example.Commentpage.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Commentpage.ManageComment.*;

/**
 * ReadArticle 类用于显示文章内容，并允许用户进行点赞、评论及回复操作。
 */

public class ReadArticle {

    private static final String ARTICLE_FOLDER = "data/articles/";
    private static final String FILE_SUFFIX = "_article.txt";

    /**
     * 显示文章内容及相关操作。
     *
     * @param scanner    输入扫描器
     * @param article    当前显示的文章对象
     * @param currentUser 当前登录的用户
     */

    public static void readArticle(Scanner scanner, Article article, String currentUser) {
        while (true) {
            NewPage.newpage();
            List<Article> articles = load.loadArticles();
            Article newarticle = articles.get(article.getId()-1);

            System.out.println("\n文章内容：");
            System.out.println("题目：" + newarticle.getTitle());
            System.out.println("作者：" + newarticle.getAuthor());
            System.out.println("发布时间：" + newarticle.getTimes().show());
            System.out.println("内容：" + newarticle.getContent());
            System.out.println("点赞量：" + newarticle.getLikes());

            System.out.println("评论：");
            List<Comment> comments = loadComments(newarticle.getTimes().TimeMilliseconds()+"_" + newarticle.getTitle());
            if (comments.isEmpty()) {
                System.out.println("暂无评论！");
            }
            else {
                for (Comment comment : comments) {
                    if (comment.getId() != 0) {
                        System.out.println(comment.getId() + "、" + comment.getContent() + "\n        评论时间：" + comment.getTimes().show());
                    }
                    else {
                        System.out.println("    " + comment.getContent()+ "\n        评论时间：" + comment.getTimes().show());
                    }
                }
            }

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
                article.like();
                saveArticle(article);
                System.out.println("点赞成功！");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (command.equals("comments")) {
                System.out.println("请输入评论内容：");
                String content = scanner.nextLine();
                addComment(newarticle.getId(), content,newarticle,currentUser);
                System.out.println("评论成功！");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (command.startsWith("reply")) {
                String[] parts = command.split(" ");
                if (currentUser != null) {
                    if (parts.length == 2) {
                        int commentIndex = Integer.parseInt(parts[1]);
                        int len = 0;
                        for(Comment comment : comments) {
                            if(comment.getId() != 0){
                                len++;
                            }
                        }
                        if (commentIndex > 0 && commentIndex <= len) {
                            System.out.println("请输入回复内容：");
                            String reply = scanner.nextLine();
                            addreplyComment(commentIndex, currentUser, reply,newarticle);
                            System.out.println("回复成功！");
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            System.out.println("无效评论序号！");
                            try {
                                Thread.sleep(2000);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else {
                        System.out.println("命令格式错误！");
                        try {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    System.out.println("请先登录！");
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                System.out.println("无效命令！");
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 保存文章内容到文件。
     *
     * @param article 需要保存的文章对象
     */

    public static void saveArticle(Article article) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + article.getTimes().print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

}