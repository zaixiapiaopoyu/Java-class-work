package org.example.Articlepage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.Times;
import org.example.AllCanUse.TimesTamp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ChangeArticle 类提供了修改文章的功能，包括修改标题、内容，并保存修改后的文章。
 * 修改后旧文章文件和对应评论文件将被删除。
 */

public class ChangeArticle {

    private final String ARTICLE_FOLDER = "data/articles/";
    private final String FILE_SUFFIX = "_article.txt";

    /**
     * 修改文章的方法。
     *
     * @param article 要修改的文章对象
     */

    public void changeArticle(Article article) {
        NewPage newPage = new NewPage();
        newPage.newpage();
        System.out.print("\n\n ---------");
        System.out.println("\n| 修改文章 |");
        System.out.println(" ---------\n");
        System.out.println("输入 # 取消修改文章\n");
        System.out.println("请输入文章标题：");
        Scanner scanner = new Scanner(System.in);
        String newtitle = scanner.nextLine();
        if (newtitle.equals("#")) {
            return;
        }
        System.out.println("请输入文章内容：");
        String content = scanner.nextLine();
        if (content.equals("#")) {
            return;
        }
        TimesTamp timesTamp = new TimesTamp();
        Times Times = timesTamp.timestamp();
        addArticle(newtitle, content, article.getAuthor(),Times);
        System.out.println("已成功修改文章！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String title = article.getTimes().TimeMilliseconds()+ "_" + article.getTitle();
        String filePath = ARTICLE_FOLDER + title + FILE_SUFFIX;
        String filePath_comments = "data/comments/" + title + "_comments.txt ";

        // 创建 File 对象
        File file = new File(filePath);
        File file_comments = new File(filePath_comments);

        // 检查文件是否存在并尝试删除
        if (file.exists()) {
            if (file.delete()) {
            }
        }
        if (file_comments.exists()) {
            if (file_comments.delete()) {
            }
        }
    }

    /**
     * 添加新的文章到存储系统。
     *
     * @param title   新文章的标题
     * @param content 新文章的内容
     * @param author  新文章的作者
     * @param times   新文章的时间戳
     */

    private void addArticle(String title, String content, String author,Times times) {
        // 生成新的 ID，可以通过文件夹中文件的数量来确定
        File folder = new File(ARTICLE_FOLDER);
        int newId = folder.listFiles() != null ? folder.listFiles().length + 1 : 1;

        Article newArticle = new Article(newId, title, content, author, 0,times); // 使用新的 ID
        saveAuthorArticle(newArticle,times);
    }

    /**
     * 保存文章到文件系统。
     *
     * @param article 要保存的文章对象
     * @param times   文章的时间戳
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
