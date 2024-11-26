package org.example.Userpage;

/**
 * User 类用于表示系统中的用户。
 * 包含用户名、密码、邮箱信息，并提供相关方法用于获取用户信息、格式化为文件存储、以及从文件字符串中解析出用户对象。
 */

public class User {
    private String username; // 用户名
    private String password; // 密码
    private String email;    // 邮箱

    /**
     * 构造方法，初始化 User 对象。
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     */

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // 默认构造方法，允许创建一个空的 User 对象
    public User() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    /**
     * 将用户信息格式化为文件保存所需的字符串形式。
     * 格式为：用户名、密码、邮箱，每个字段换行分隔。
     *
     * @return 格式化后的字符串
     */

    public String toFileString() {
        return username + "\n" + password + "\n" + email;
    }

    /**
     * 从字符串解析为 User 对象。
     * 字符串应符合由用户名、密码和邮箱三部分组成，每部分通过换行符分隔。
     *
     * @param line 存储用户信息的字符串
     * @return 解析得到的 User 对象
     */

    public User fromString(String line) {
        try {
            String[] parts = line.split("\n", 3);
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
