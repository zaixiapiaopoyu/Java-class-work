package org.example.Userpage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    private static final String BASE_FOLDER = "data/users_data/";
    private static final String USER_FILE = "users.txt";

    public static String login() {
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            NewPage.newpage();
            System.out.print("\n ---------");
            System.out.println("\n| 用户登录 |");
            System.out.println(" ---------\n");
            System.out.println("输入 # 退出登录界面\n");
            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if(username.equals("#")) {
                break;
            }
            System.out.println("请输入密码：");
            String password = scanner.nextLine();
            if(password.equals("#")) {
                break;
            }
            if (login(username, password)) {
                isLoggedIn = true;
                return username;
            }
        }
        return null;
    }

    public static boolean login(String username, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("登录成功！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        System.out.println("用户名或密码错误！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        int lineCount = 0;
        String[] threeLines = new String[3];
        List<String> lines = FileHandler.readFile(BASE_FOLDER, "", USER_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            threeLines[lineCount] = line;
            lineCount++;
            if (lineCount == 3) {
                lineCount = 0;
                String combinedLines = threeLines[0] + "\n" + threeLines[1] + "\n" + threeLines[2];

                User user = User.fromString(combinedLines);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return users;
    }
}