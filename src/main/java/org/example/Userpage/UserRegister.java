package org.example.Userpage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;
import java.util.Scanner;

/**
 * UserRegister 类用于处理用户注册操作。
 * 用户通过输入用户名、密码和邮箱完成注册，系统会对输入的内容进行验证。
 * 注册成功后会将用户信息保存到文件中。
 */

public class UserRegister {
    private static final String BASE_FOLDER = "data/users_data/";
    private static final String USER_FILE = "users.txt";

    /**
     * 提供用户注册功能。
     * 用户输入用户名、密码和邮箱，并进行相应的验证。若输入无效，则会提示重新输入。
     * 注册成功后，用户信息会被存储在文件中。
     */

    public static void register() {
        NewPage.newpage();
        System.out.print("\n ---------");
        System.out.println("\n| 用户注册 |");
        System.out.println(" ---------\n");
        System.out.println("输入 # 退出注册界面\n");
        System.out.println("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        if(username.equals("#")) {
            return;
        }

        while (!register_name(username)) {
            System.out.println("请重新输入用户名：");
            username = scanner.nextLine();
            if(username.equals("#")) {
                return;
            }
        }

        System.out.println("请输入密码（至少8位，包含 数字、大写、小写）：");
        String password = scanner.nextLine();
        if(password.equals("#")) {
            return;
        }
        while (!register_password(password)) {
            System.out.println("请重新输入密码（至少8位，包含 数字、大写、小写）：");
            password = scanner.nextLine();
            if(password.equals("#")) {
                return;
            }
        }

        System.out.println("请输入邮箱：");
        String email = scanner.nextLine();
        if(email.equals("#")) {
            return;
        }

        while (!register_email(email)) {
            System.out.println("请重新输入邮箱：");
            email = scanner.nextLine();
            if(email.equals("#")) {
                return;
            }
        }

        User newUser = new User(username, password, email);
        FileHandler.appendFile(BASE_FOLDER, "", USER_FILE, newUser.toFileString());
        System.out.println("注册成功！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查用户名是否已存在。
     * 如果用户名已存在，则提示用户名已存在，返回 false；否则返回 true。
     *
     * @param username 用户名
     * @return 如果用户名不存在，返回 true；如果用户名已存在，返回 false
     */

    public static boolean register_name(String username){
        List<User> users = load.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("用户名已存在！");
                return false;
            }
        }
        return true;
    }

    /**
     * 验证密码是否符合要求。
     * 密码必须至少8位，且包含数字、大写字母和小写字母，不包含空格。
     *
     * @param password 密码
     * @return 如果密码符合要求，返回 true；否则返回 false
     */

    public static boolean register_password(String password) {

        if (password == null || password.length() < 8) {
            System.out.println("密码不能为空，且长度不能少于8个字符!");
            return false;
        }

        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");

        if (password.contains(" ")) {
            System.out.println("密码不能包含空格!");
            return false;
        }

        if (hasDigit && hasUppercase && hasLowercase) {
            return true;
        }
        else {
            if (!hasDigit) {
                System.out.println( "密码未包含数字!");
            }
            if (!hasUppercase) {
                System.out.println("密码未包含大写字母!");
            }
            if (!hasLowercase) {
                System.out.println("密码未包含小写字母!");
            }
            return false;
        }
    }

    /**
     * 验证邮箱格式是否符合要求。
     * 邮箱需要符合一定的长度要求，并且必须是一个有效的格式，且只支持指定的域名。
     *
     * @param email 邮箱
     * @return 如果邮箱符合要求，返回 true；否则返回 false
     */

    public static boolean register_email(String email){

        String[] allowedDomains = {"qq.com", "gmail.com", "outlook.com", "163.com", "126.com", "yahoo.com", "sina.com", "hotmail.com"};
        boolean len = false;
        boolean tail = false;
        len = email.matches(".{10,20}.*");

        if(!len){
            System.out.println("邮箱长度不正确");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9.-]+)\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            System.out.println("邮箱格式不正确，请输入有效的邮箱地址");
            return false;
        }

        String domain = email.substring(email.indexOf("@") + 1);
        boolean validDomain = false;
        for (String allowedDomain : allowedDomains) {
            if (domain.equalsIgnoreCase(allowedDomain)) {
                validDomain = true;
                break;
            }
        }

        if (!validDomain) {
            System.out.println("邮箱域名不支持，仅支持以下域名：" + String.join(", ", allowedDomains));
            return false;
        }
        return true;
    }
}
