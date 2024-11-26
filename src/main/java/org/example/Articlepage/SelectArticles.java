package org.example.Articlepage;

import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;
import java.util.Scanner;

/**
 * SelectArticles 类用于选择并阅读文章。
 */

public class SelectArticles {

    /**
     * 选择文章并进行阅读操作。
     *
     * @param scanner    输入扫描器
     * @param currentUser 当前登录的用户
     */

    public void  selectArticles(Scanner scanner, String currentUser) {
        load load = new load();
        List<Article> articles = load.loadArticles();
        if (articles.isEmpty()) {
            NewPage newPage = new NewPage();
            newPage.newpage();
            System.out.print("\n ---------");
            System.out.println("\n| 选择文章 |");
            System.out.println(" ---------\n");
            System.out.println("暂无文章！");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        while (true) {
            NewPage newPage = new NewPage();
            newPage.newpage();
            System.out.print("\n ---------");
            System.out.println("\n| 选择文章 |");
            System.out.println(" ---------\n");
            System.out.println("文章列表：");
            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                System.out.println((i + 1) + "、 " + article.getTitle());
            }
            System.out.println("\n1. 输入文章编号阅读文章");
            System.out.println("2. 输入 0 返回主界面");

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                break;
            }
            else if (choice.matches("\\d+")) { // 使用正则表达式检查是否为正整数
                int choiceInt = Integer.parseInt(choice); // 转换为整数
                if (choiceInt > 0 && choiceInt <= articles.size()) {
                    ReadArticle readArticle = new ReadArticle();
                    readArticle.readArticle(scanner, articles.get(choiceInt - 1), currentUser);
                }
                else {
                    System.out.println("无效选项！");
                    try {
                        Thread.sleep(1000); // 延时 1 秒
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                System.out.println("输入无效，请输入有效的数字！");
                try {
                    Thread.sleep(2000); // 延时 3 秒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}