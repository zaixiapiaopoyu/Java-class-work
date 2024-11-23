package org.example.Userpage;

import org.example.AllCanUse.FileHandler;
import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;
import java.util.Scanner;

public class UserRegister {
    private static final String BASE_FOLDER = "data/users_data/";
    private static final String USER_FILE = "users.txt";
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

        System.out.println("请输入密码（包含 数字、大写、小写、特殊字符）：");
        String password = scanner.nextLine();
        if(password.equals("#")) {
            return;
        }
        while (!register_password(password)) {
            System.out.println("请重新输入密码（包含 数字、大写、小写、特殊字符）：");
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

    public static boolean register_password(String password) {

        if (password == null || password.length() < 8) {
            System.out.println("密码不能为空，且长度不能少于8个字符!");
            return false;
        }

        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"); // 常见特殊字符

        if (password.contains(" ")) {
            System.out.println("密码不能包含空格!");
            return false;
        }

        if (hasDigit && hasUppercase && hasLowercase && hasSpecialChar) {
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
            if (!hasSpecialChar) {
                System.out.println("密码未包含特殊字符!");
            }
            return false;
        }
    }

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
