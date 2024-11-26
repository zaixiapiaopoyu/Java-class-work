package org.example.Articlepage;

import org.example.AllCanUse.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * WriteArticle 类用于发布新的文章。
 */

public class WriteArticle {

    private final String ARTICLE_FOLDER = "data/articles/";
    private final String FILE_SUFFIX = "_article.txt";

    /**
     * 发布文章方法，用户输入标题和内容来创建一篇新的文章。
     *
     * @param currentUser 当前登录用户
     */

    public void writeArticle(String currentUser ) {
        NewPage newPage = new NewPage();
        newPage.newpage();
        System.out.print("\n\n ---------");
        System.out.println("\n| 发布文章 |");
        System.out.println(" ---------\n");
        System.out.println("输入 # 取消发布文章\n");
        System.out.println("请输入文章标题：");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        while(title.equals("")) {
            System.out.println("文章标题不能为空！");
            System.out.println("请重新输入文章标题：");
            title = scanner.nextLine();
        }
        if (title.equals("#")) {
            return;
        }
        System.out.println("请输入文章内容：");
        String content = scanner.nextLine();
        while(content.equals("")) {
            System.out.println("文章内容不能为空！");
            System.out.println("请重新输入文章内容：");
            content = scanner.nextLine();
        }
        if (content.equals("#")) {
            return;
        }
        TimesTamp timesTamp = new TimesTamp();
        Times Times = timesTamp.timestamp();
        addArticle(title, content, currentUser,Times);
        System.out.println("已成功发布文章！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加新文章的方法
     *
     * @param title   文章标题
     * @param content 文章内容
     * @param author  文章作者
     * @param times   文章发布时间
     */

    private void addArticle(String title, String content, String author,Times times) {
        // 生成新的 ID，可以通过文件夹中文件的数量来确定
        File folder = new File(ARTICLE_FOLDER);
        int newId = folder.listFiles() != null ? folder.listFiles().length + 1 : 1;

        Article newArticle = new Article(newId, title, content, author, 0,times); // 使用新的 ID
        saveAuthorArticle(newArticle,times);
    }

    /**
     * 保存文章到文件的方法
     *
     * @param article  文章对象
     * @param times    文章发布时间
     */

    public void saveAuthorArticle(Article article,Times times) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + times.print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

}
