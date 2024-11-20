package org.example;

import java.util.List;
import java.util.Scanner;

public class UserRegister {
    private static final String BASE_FOLDER = "users_data/";
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

        System.out.println("请输入密码（包含 数字、大写、小写）：");
        String password = scanner.nextLine();
        if(password.equals("#")) {
            return;
        }
        while (!register_password(password)) {
            System.out.println("请重新输入密码（包含 数字、大写、小写）：");
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

    public static boolean register_password(String password){
        boolean shuzi = false;
        boolean daxie = false;
        boolean xiaoxie = false;
        shuzi = password.matches(".*[0-9].*");
        daxie = password.matches(".*[A-Z].*");
        xiaoxie = password.matches(".*[a-z].*");
        if (shuzi && daxie && xiaoxie /*&& jiewei*/) {
            return true;
        }
        else{
            if (!shuzi){
                System.out.println("密码未包含数字");
            }
            else if (!daxie){
                System.out.println("密码未包含大写字母");
            }
            else if (!xiaoxie){
                System.out.println("密码未包含小写字母");
            }
            return false;
        }
    }

    public static boolean register_email(String email){
        boolean len = false;
        boolean tail = false;
        len = email.matches(".{10,11}.*");
        tail = email.matches(".*@qq.com");
        if(len && tail){
            return true;
        }
        else{
            if(!len){
                System.out.println("邮箱长度不正确");
            }
            else if(!tail){
                System.out.println("邮箱后缀不正确");
            }
            return false;
        }
    }
}
