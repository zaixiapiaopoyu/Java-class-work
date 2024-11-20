package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.example.ArticleManagement.loadSingleArticle;

public class load {
    private static final String BASE_FOLDER = "users_data/";
    private static final String USER_FILE = "users.txt";
    private static final String ARTICLE_FOLDER = "articles/";
    private static final String FILE_SUFFIX = "_article.txt";
    static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        List<String> lines = FileHandler.readFile(BASE_FOLDER, "", USER_FILE);
        for (String line : lines) {
            User user = User.fromString(line);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    public static List<Article> loadArticles() {
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
}
