//介绍   需求分析     （要做什么，有什么用）  系统设计，分哪几个模块（组合聚合）（有哪些类，类和类的关系）    测试和演示    总结
//    想好演示脚本  2分内
package org.example.Mainpage;

import org.example.AllCanUse.NewPage;
import org.example.Articlepage.ManageArticle;
import org.example.Articlepage.SelectArticles;
import org.example.Userpage.UserLogin;
import org.example.Userpage.UserRegister;

import java.util.Scanner;

// 主界面程序入口，负责展示菜单和处理用户的选择操作

public class Main {
    public static void main(String[] args) {
        NewPage newPage = new NewPage();
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String currentUser = null;
        newPage.newpage();
        System.out.println("------------------------------------------------------------");
        System.out.println("--------------------欢迎使用Java简易博客系统--------------------");
        System.out.println("------------------------------------------------------------\n\n\n\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            newPage.newpage();
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

            //登录
            if (choice.equals("1")) {
                if(!isLoggedIn) {
                    UserLogin userLogin = new UserLogin();
                    currentUser = userLogin.login();
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

            //注册
            else if (choice.equals("2")) {
                UserRegister register = new UserRegister();
                register.register() ;
            }

            //退出登录
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

            //文章管理
            else if (choice.equals("4")) {
                ManageArticle manageArticle = new ManageArticle();
                manageArticle.managearticle(isLoggedIn,scanner,currentUser);
            }

            //文章浏览
            else if (choice.equals("5")) {
                SelectArticles selectArticles = new SelectArticles();
                selectArticles.selectArticles(scanner,currentUser);
            }

            //退出应用
            else if (choice.equals("6")) {
                newPage.newpage();
                System.out.print("\n -------");
                System.out.println("\n| 退出中 |");
                System.out.println(" -------\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("感谢使用博客系统，再见！");
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
