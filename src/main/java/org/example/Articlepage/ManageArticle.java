package org.example.Articlepage;

import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;
import java.util.Scanner;

import static org.example.Articlepage.ChangeArticle.changeArticle;
import static org.example.Articlepage.DelArticle.delArticle;
import static org.example.Articlepage.ListAuthorArticle.listauthorarticle;

/**
 * ManageArticle 类用于提供文章管理的功能，包括发布、查看、修改和删除文章。
 */

public class ManageArticle {

    /**
     * 检查用户登录状态并进入文章管理主界面。
     *
     * @param isLoggedIn 用户是否已登录
     * @param scanner    输入扫描器
     * @param currentUser 当前登录用户
     */

    public static void managearticle(boolean isLoggedIn,Scanner scanner,String currentUser) {
        NewPage.newpage();
        if (!isLoggedIn) {
            System.out.println("请先登录！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            manageArticles(scanner, currentUser);
        }
    }

    /**
     * 文章管理主界面。
     *
     * @param scanner    输入扫描器
     * @param currentUser 当前登录用户
     */

    public static void manageArticles(Scanner scanner, String currentUser) {
        while (true) {
            NewPage.newpage();
            System.out.print("\n\n ---------");
            System.out.println("\n| 文章管理 |");
            System.out.println(" ---------\n");
            System.out.println("1. 发布文章");
            System.out.println("2. 查看文章列表");
            System.out.println("3. 返回主界面");
            System.out.println("\n请选择：");


            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                WriteArticle.writeArticle(currentUser);
            }
            else if (choice.equals("2")) {
                while(true) {
                    List<Article> articles = load.loadArticles();
                    int len = listauthorarticle(currentUser);
                    if(len <= 0){
                        System.out.println("\n|-----暂无文章！-----|\n\n\n");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    System.out.println("\n1. 输入文章编号查看文章");
                    System.out.println("2. 输入 del 文章编号删除文章");
                    System.out.println("3.输入 change 文章编号重新编辑文章");
                    System.out.println("4. 输入 0 返回文章管理界面");
                    System.out.println("\n请选择：");
                    String choices = scanner.nextLine().trim();
                    try {
                        if (choices.equals("0")) {
                            break;
                        }
                        else {
                            int i = 0;
                            int j = 0;
                            if (choices.startsWith("del")) {
                                String[] parts = choices.split(" ");
                                if (parts.length == 2) {
                                    int id = Integer.parseInt(parts[1]);
                                    for (Article article : articles) {
                                        if (article.getAuthor().equals(currentUser)) {
                                            i++;
                                            if (i == id) {
                                                delArticle(article);
                                                break;
                                            }
                                        }
                                        j++;
                                    }
                                }
                                else {
                                    System.out.println("请输入文章序号！");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            else if (choices.startsWith("change")) {
                                String[] parts = choices.split(" ");
                                if (parts.length == 2) {
                                    int id = Integer.parseInt(parts[1]);
                                    for (Article article : articles) {
                                        if (article.getAuthor().equals(currentUser)) {
                                            i++;
                                            if (i == id) {
                                                changeArticle(article);
                                                break;
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println("请输入文章序号！");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            else if (Integer.parseInt(choices) > 0 && Integer.parseInt(choices) <= len) {
                                for (Article article : articles) {
                                    if (article.getAuthor().equals(currentUser)) {
                                        i++;
                                        if (i == Integer.parseInt(choices)) {
                                            ReadArticle.readArticle(scanner, articles.get(j), currentUser);
                                            break;
                                        }
                                    }
                                    j++;
                                }
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println("无效选项！");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            else if (choice.equals("3")) {
                break;
            }
            else {
                System.out.println("无效选项！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
