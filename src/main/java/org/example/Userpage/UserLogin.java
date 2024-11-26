package org.example.Userpage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * UserLogin 类负责处理用户登录操作。
 * 包含用户输入用户名和密码进行登录的逻辑，并验证登录信息是否正确。
 * 登录成功后返回用户名，否则返回 null。
 */

public class UserLogin {

    private static final String BASE_FOLDER = "data/users_data/";
    private static final String USER_FILE = "users.txt";

    /**
     * 提供用户登录功能。
     * 用户输入用户名和密码进行登录，若登录成功返回用户名，失败则继续请求输入。
     *
     * @return 登录成功返回用户名，失败则返回 null
     */

    public String login() {
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            NewPage newPage = new NewPage();
            newPage.newpage();
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
            if (islogin(username, password)) {
                isLoggedIn = true;
                return username;
            }
        }
        return null;
    }

    /**
     * 验证用户名和密码是否正确。
     * 遍历存储的所有用户信息，检查是否有匹配的用户名和密码。
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回 true，失败则返回 false
     */

    public boolean islogin(String username, String password) {
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

    /**
     * 从文件中加载所有的用户数据。
     * 文件中每个用户占三行（用户名、密码、邮箱），将其解析为 User 对象并添加到用户列表中。
     *
     * @return 所有用户的列表
     */

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        int lineCount = 0;
        String[] threeLines = new String[3];
        FileHandler fileHandler = new FileHandler();
        List<String> lines = fileHandler.readFile(BASE_FOLDER, "", USER_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            threeLines[lineCount] = line;
            lineCount++;
            if (lineCount == 3) {
                lineCount = 0;
                String combinedLines = threeLines[0] + "\n" + threeLines[1] + "\n" + threeLines[2];

                User user = new User();
                user = user.fromString(combinedLines);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return users;
    }
}