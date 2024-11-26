package org.example.AllCanUse;

import org.example.Articlepage.Article;
import org.example.Userpage.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * load 类用于从文件中加载用户和文章数据。
 * 它包含加载用户信息和加载文章信息的方法。
 */

public class load {
    private static final String BASE_FOLDER = "data/users_data/";
    private static final String USER_FILE = "users.txt";
    private static final String ARTICLE_FOLDER = "data/articles/";
    private static final String FILE_SUFFIX = "_article.txt";

    /**
     * 加载用户信息。
     * 从 BASE_FOLDER 文件夹中的 USER_FILE 文件中逐行读取用户信息，
     * 每三个连续的行组成一个用户信息。
     *
     * @return 用户列表
     */

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        int lineCount = 0;
        String[] threeLines = new String[3];
        FileHandler fileHandler = new FileHandler();
        List<String> lines =fileHandler.readFile(BASE_FOLDER, "", USER_FILE);
        for (String line : lines) {
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

    /**
     * 加载文章信息。
     * 从 ARTICLE_FOLDER 文件夹中读取所有文件，筛选出以 FILE_SUFFIX 结尾的文章文件，
     * 并为每篇文章分配一个唯一的 ID。
     *
     * @return 文章列表
     */

    public List<Article> loadArticles() {
        File folder = new File(ARTICLE_FOLDER);
        List<Article> articles = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.getName().endsWith(FILE_SUFFIX)) {
                        String title = file.getName().replace(FILE_SUFFIX, "");
                        Article article = loadSingleArticle(title, i + 1);  // 分配唯一的 ID
                        if (article != null) {
                            articles.add(article);
                        }
                        else {
                            System.out.println("忽略无法加载的文章文件：" + file.getName());
                        }
                    }
                }
            }
        }
        return articles;
    }

    /**
     * 加载单篇文章信息。
     * 从文件中读取文章的具体内容，并创建 Article 对象。
     *
     * @param title 文章标题
     * @param id    文章唯一 ID
     * @return 加载的文章对象
     */

    public Article loadSingleArticle(String title, int id) {
        FileHandler fileHandler = new FileHandler();
        List<String> lines = fileHandler.readFile(ARTICLE_FOLDER, title, FILE_SUFFIX);

        String articleTitle = lines.get(0).replace("题目：", "").trim();
        String author = lines.get(1).replace("作者：", "").trim();
        String time = lines.get(2).replace("时间：", "").trim();
        String content = lines.get(3).replace("内容：", "").trim();
        int likes = Integer.parseInt(lines.get(4).replace("点赞量：", "").trim());

        String[] timeParts = time.split(":");

        int year = Integer.parseInt(timeParts[0].trim());
        int month = Integer.parseInt(timeParts[1].trim());
        int day = Integer.parseInt(timeParts[2].trim());
        int hour = Integer.parseInt(timeParts[3].trim());
        int minute = Integer.parseInt(timeParts[4].trim());
        int second = Integer.parseInt(timeParts[5].trim());

        Times times = new Times(year,month,day,hour, minute, second);

        return new Article(id, articleTitle, content, author, likes,times); // 使用传入的 ID
    }

}