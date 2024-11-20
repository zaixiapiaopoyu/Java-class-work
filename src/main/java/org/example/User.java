package org.example;

public class User {
    private String username; // 用户名
    private String password; // 密码
    private String email;    // 邮箱

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // 格式化为文件保存的字符串
    public String toFileString() {
        return username + "、" + password + "、" + email;
    }

    // 从字符串解析为 User 对象
    public static User fromString(String line) {
        try {
            String[] parts = line.split("、", 3);
            String username = parts[0].trim();
            String password = parts[1].trim();
            String email = parts[2].trim();
            return new User(username, password, email);
        }
        catch (Exception e) {
            return null;
        }
    }
}
