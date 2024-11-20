//package no_use;
//
//import org.example.FileHandler;
//import org.example.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserManager {
//    private static final String BASE_FOLDER = "users_data/";
//    private static final String USER_FILE = "users.txt";
//
//
//
//
//    public static boolean register_name(String username){
//        List<User> users = loadUsers();
//        for (User user : users) {
//            if (user.getUsername().equals(username)) {
//                System.out.println("用户名已存在！");
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean register_password(String password){
//        boolean shuzi = false;
//        boolean daxie = false;
//        boolean xiaoxie = false;
//        shuzi = password.matches(".*[0-9].*");
//        daxie = password.matches(".*[A-Z].*");
//        xiaoxie = password.matches(".*[a-z].*");
//        if (shuzi && daxie && xiaoxie /*&& jiewei*/) {
//            return true;
//        }
//        else{
//            if (!shuzi){
//                System.out.println("密码未包含数字");
//            }
//            else if (!daxie){
//                System.out.println("密码未包含大写字母");
//            }
//            else if (!xiaoxie){
//                System.out.println("密码未包含小写字母");
//            }
//            return false;
//        }
//    }
//
//    public static boolean register_email(String email){
//        boolean len = false;
//        boolean tail = false;
//        len = email.matches(".{10,11}.*");
//        tail = email.matches(".*@qq.com");
//        if(len && tail){
//            return true;
//        }
//        else{
//            if(!len){
//                System.out.println("邮箱长度不正确");
//            }
//            else if(!tail){
//                System.out.println("邮箱后缀不正确");
//            }
//            return false;
//        }
//    }
//    // 用户注册
//    public static boolean register(String username, String password, String email) {
//        User newUser = new User(username, password, email);
//        FileHandler.appendFile(BASE_FOLDER, "", USER_FILE, newUser.toFileString());
//        System.out.println("注册成功！");
//        return true;
//    }
//
//    // 用户登录
//    public static boolean login(String username, String password) {
//        List<User> users = loadUsers();
//        for (User user : users) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                System.out.println("登录成功！");
//                return true;
//            }
//        }
//        System.out.println("用户名或密码错误！");
//        return false;
//    }
//
//    // 加载用户数据
//    private static List<User> loadUsers() {
//        List<User> users = new ArrayList<>();
//        List<String> lines = FileHandler.readFile(BASE_FOLDER, "", USER_FILE);
//        for (String line : lines) {
//            User user = User.fromString(line);
//            if (user != null) {
//                users.add(user);
//            }
//        }
//        return users;
//    }
//}
