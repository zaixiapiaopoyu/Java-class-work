package org.example.Articlepage;

import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;
import java.util.Scanner;

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
    public void managearticle(boolean isLoggedIn, Scanner scanner, String currentUser) {
        NewPage newPage = new NewPage();
        newPage.newpage();
        if (!isLoggedIn) {
            System.out.println("请先登录！");
            pause(1000);
        } else {
            manageArticles(scanner, currentUser);
        }
    }

    /**
     * 文章管理主界面。
     *
     * @param scanner    输入扫描器
     * @param currentUser 当前登录用户
     */
    public void manageArticles(Scanner scanner, String currentUser) {
        while (true) {
            displayManageMenu();
            String choice = scanner.nextLine();

            if(choice.equals("1")) {
                WriteArticle writeArticle = new WriteArticle();
                writeArticle.writeArticle(currentUser);
            }
            else if(choice.equals("2")) {
                handleArticleOperations(scanner, currentUser);
            }
            else if(choice.equals("3")) {
                break; // 返回主界面
            }
            else{
                System.out.println("无效选项！");
                pause(2000);
            }
        }
    }

    /**
     * 显示文章管理菜单。
     */
    private void displayManageMenu() {
        NewPage newPage = new NewPage();
        newPage.newpage();
        System.out.print("\n\n ---------");
        System.out.println("\n| 文章管理 |");
        System.out.println(" ---------\n");
        System.out.println("1. 发布文章");
        System.out.println("2. 查看文章列表");
        System.out.println("3. 返回主界面");
        System.out.println("\n请选择：");
    }

    /**
     * 处理文章操作，如查看、删除、编辑文章。
     *
     * @param scanner    输入扫描器
     * @param currentUser 当前登录用户
     */
    private void handleArticleOperations(Scanner scanner, String currentUser) {
        while (true) {
            load load = new load();
            List<Article> articles = load.loadArticles();
            ListAuthorArticle listAuthorArticle = new ListAuthorArticle();
            int len = listAuthorArticle.listauthorarticle(currentUser);

            if (len <= 0) {
                System.out.println("\n|-----暂无文章！-----|\n\n\n");
                pause(2000);
                return;
            }

            displayArticleOptions();
            String choices = scanner.nextLine().trim();

            if (choices.equals("0")) {
                break;
            }
            else {
                processArticleChoice(scanner, choices, articles, currentUser, len);
            }
        }
    }

    /**
     * 显示文章操作菜单。
     */
    private void displayArticleOptions() {
        System.out.println("\n1. 输入文章编号查看文章");
        System.out.println("2. 输入 del 文章编号删除文章");
        System.out.println("3. 输入 change 文章编号重新编辑文章");
        System.out.println("4. 输入 0 返回文章管理界面");
        System.out.println("\n请选择：");
    }

    /**
     * 处理用户选择的文章操作。
     *
     * @param scanner     输入扫描器
     * @param choices     用户输入的命令
     * @param articles    当前所有文章列表
     * @param currentUser 当前登录用户
     * @param len         当前用户文章数量
     */
    private void processArticleChoice(Scanner scanner, String choices, List<Article> articles, String currentUser, int len) {
        try {
            String[] parts = choices.split(" ");
            String action = parts[0];
            int id = (parts.length > 1) ? Integer.parseInt(parts[1]) : -1;

            if (action.equals("del")) {
                handleDeleteArticle(articles, currentUser, id);
            }
            else if (action.equals("change")) {
                handleChangeArticle(articles, currentUser, id);
            }
            else {
                handleViewArticle(scanner, articles, currentUser, Integer.parseInt(choices), len);
            }
        } catch (Exception e) {
            System.out.println("无效选项！");
            pause(2000);
        }
    }

    /**
     * 删除文章操作。
     */
    private void handleDeleteArticle(List<Article> articles, String currentUser, int id) {
        if (id > 0) {
            int i = 0;
            for (Article article : articles) {
                if (article.getAuthor().equals(currentUser)) {
                    i++;
                    if (i == id) {
                        DelArticle delArticle = new DelArticle();
                        delArticle.delArticle(article);
                        return;
                    }
                }
            }
        }
        System.out.println("请输入正确的文章序号！");
        pause(2000);
    }

    /**
     * 修改文章操作。
     */
    private void handleChangeArticle(List<Article> articles, String currentUser, int id) {
        if (id > 0) {
            int i = 0;
            for (Article article : articles) {
                if (article.getAuthor().equals(currentUser)) {
                    i++;
                    if (i == id) {
                        ChangeArticle changeArticle = new ChangeArticle();
                        changeArticle.changeArticle(article);
                        return;
                    }
                }
            }
        }
        System.out.println("请输入正确的文章序号！");
        pause(2000);
    }

    /**
     * 查看文章操作。
     */
    private void handleViewArticle(Scanner scanner, List<Article> articles, String currentUser, int id, int len) {
        if (id > 0 && id <= len) {
            int i = 0, j = 0;
            for (Article article : articles) {
                if (article.getAuthor().equals(currentUser)) {
                    i++;
                    if (i == id) {
                        ReadArticle readArticle = new ReadArticle();
                        readArticle.readArticle(scanner, articles.get(j), currentUser);
                        return;
                    }
                }
                j++;
            }
        }
        else {
            System.out.println("无效文章序号！");
            pause(2000);
        }
    }

    /**
     * 暂停指定时间。
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
