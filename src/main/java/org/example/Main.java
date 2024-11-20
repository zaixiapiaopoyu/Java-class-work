package org.example;

import java.util.Scanner;

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
            System.out.println("3. 文章管理（需登录）");
            System.out.println("4. 选择文章");
            System.out.println("5. 退出系统\n");
            System.out.println("\n请选择：");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                currentUser = UserLogin.login();
                if (currentUser != null) {
                    isLoggedIn = true;
                }
            }
            else if (choice.equals("2")) {
                UserRegister.register();
            }
            else if (choice.equals("3")) {
                ArticleManagement.ArticleManage(isLoggedIn,scanner,currentUser);
            }
            else if (choice.equals("4")) {
                SelectArticles.selectArticles(scanner,currentUser);
            }
            else if (choice.equals("5")) {
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