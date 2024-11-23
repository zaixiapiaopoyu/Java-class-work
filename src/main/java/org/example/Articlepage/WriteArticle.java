package org.example.Articlepage;

import org.example.AllCanUse.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteArticle {

    private static final String ARTICLE_FOLDER = "data/articles/";
    private static final String FILE_SUFFIX = "_article.txt";

    public static void writeArticle(String currentUser ) {
        NewPage.newpage();
        System.out.print("\n\n ---------");
        System.out.println("\n| 发布文章 |");
        System.out.println(" ---------\n");
        System.out.println("输入 # 取消发布文章\n");
        System.out.println("请输入文章标题：");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        if (title.equals("#")) {
            return;
        }
        System.out.println("请输入文章内容：");
        String content = scanner.nextLine();
        if (content.equals("#")) {
            return;
        }
        Times Times = TimesTamp.timestamp();
        addArticle(title, content, currentUser,Times);
        System.out.println("已成功发布文章！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addArticle(String title, String content, String author,Times times) {
        // 生成新的 ID，可以通过文件夹中文件的数量来确定
        File folder = new File(ARTICLE_FOLDER);
        int newId = folder.listFiles() != null ? folder.listFiles().length + 1 : 1;

        Article newArticle = new Article(newId, title, content, author, 0,times); // 使用新的 ID
        saveAuthorArticle(newArticle,times);
    }

    public static void saveAuthorArticle(Article article,Times times) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + times.print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

}
