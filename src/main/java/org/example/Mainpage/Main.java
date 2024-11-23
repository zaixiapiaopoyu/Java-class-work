package org.example.Mainpage;

import org.example.AllCanUse.NewPage;
import org.example.Articlepage.ManageArticle;
import org.example.Articlepage.SelectArticles;
import org.example.Userpage.UserLogin;
import org.example.Userpage.UserRegister;

import java.util.Scanner;

import static org.example.Articlepage.ManageArticle.managearticle;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String currentUser = null;

        while (true) {
            NewPage.newpage();
            System.out.print("\n -------");
            System.out.println("\n| 主界面 |");
            System.out.println(" -------\n");
            System.out.println("1. 用户登录");
            System.out.println("2. 用户注册");
            System.out.println("3. 退出登录");
            System.out.println("4. 文章管理（需登录）");
            System.out.println("5. 选择文章");
            System.out.println("6. 退出系统\n");
            System.out.println("\n请选择：");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                if(!isLoggedIn) {
                    currentUser = UserLogin.login();
                }
                else{
                    System.out.println("用户已登录，无法重复登录！");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (currentUser != null) {
                    isLoggedIn = true;
                }
            }
            else if (choice.equals("2")) {
                UserRegister.register();
            }
            else if (choice.equals("3")) {
                currentUser = null;
                isLoggedIn = false;
                System.out.println("已退出！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (choice.equals("4")) {
                ManageArticle.managearticle(isLoggedIn,scanner,currentUser);
            }
            else if (choice.equals("5")) {
                SelectArticles.selectArticles(scanner,currentUser);
            }
            else if (choice.equals("6")) {
                NewPage.newpage();
                System.out.print("\n -------");
                System.out.println("\n| 退出中 |");
                System.out.println(" -------\n");
                System.out.println("感谢使用博客系统，再见！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return;
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
