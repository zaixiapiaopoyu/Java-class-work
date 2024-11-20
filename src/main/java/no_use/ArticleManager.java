//package no_use;
//
//import org.example.Article;
//import org.example.Comment;
//import org.example.CommentManager;
//import org.example.FileHandler;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class ArticleManager {
//    private static final String ARTICLE_FOLDER = "articles/";
//    private static final String FILE_SUFFIX = "_article.txt";
//
//    public static void manageArticles(Scanner scanner, String currentUser) {
//        while (true) {
//            System.out.print("\n\n ---------");
//            System.out.println("\n| 文章管理 |");
//            System.out.println(" ---------\n");
//            System.out.println("1. 发布文章");
//            System.out.println("2. 查看文章列表");
//            System.out.println("3. 返回主界面");
//            System.out.println("\n请选择：");
//
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            if (choice == 1) {
//                System.out.print("\n\n ---------");
//                System.out.println("\n| 写入文章 |");
//                System.out.println(" ---------\n");
//                System.out.println("请输入文章标题：");
//                String title = scanner.nextLine();
//                System.out.println("请输入文章内容：");
//                String content = scanner.nextLine();
//                addArticle(title, content, currentUser);
//            }
//            else if (choice == 2) {
//                List<Article> articles = loadArticles();
//                while(true) {
//                    int len = viewArticles(currentUser);
//                    if(len < 0){
//                        break;
//                    }
//                    System.out.println("\n1. 输入文章编号查看文章");
//                    System.out.println("2. 输入 del 文章编号删除文章");
//                    System.out.println("3. 输入 0 返回文章管理界面");
//                    System.out.println("\n请选择：");
//                    String choices = scanner.nextLine().trim();
//                    if (choices.equals("0")) {
//                        break;
//                    }
//                    else{
//                        int i = 0;
//                        int j = 0;
//                        if(choices.startsWith("del")){
//                            String[] parts = choices.split(" ");
//                            int id = Integer.parseInt(parts[1]);
//                            for(Article article : articles) {
//                                if (article.getAuthor().equals(currentUser)) {
//                                    i++;
//                                    if(i == id){
//                                        delArticle(article.getTitle());
//                                    }
//                                }
//                                j++;
//                            }
//                        }
//                        else if (Integer.parseInt(choices)> 0 && Integer.parseInt(choices) <= len) {
//                            for(Article article : articles) {
//                                if (article.getAuthor().equals(currentUser)) {
//                                    i++;
//                                    if(i == Integer.parseInt(choices)){
//                                        readArticle(scanner, articles.get(j), currentUser);
//                                    }
//                                }
//                                j++;
//                            }
//                        }
//                        else {
//                            System.out.println("无效选项！");
//                        }
//                    }
//                }
//            }
//            else if (choice == 3) {
//                break;
//            }
//            else {
//                System.out.println("无效选项！");
//            }
//        }
//    }
//
//    public static void  selectArticles(Scanner scanner, String currentUser) {
//        List<Article> articles = loadArticles();
//        if (articles.isEmpty()) {
//            System.out.print("\n ---------");
//            System.out.println("\n| 选择文章 |");
//            System.out.println(" ---------\n");
//            System.out.println("暂无文章！");
//            return;
//        }
//
//        while (true) {
//            System.out.print("\n ---------");
//            System.out.println("\n| 选择文章 |");
//            System.out.println(" ---------\n");
//            System.out.println("1. 输入文章编号阅读文章");
//            System.out.println("2. 输入 0 返回主界面");
//
//            System.out.println("\n文章列表：");
//            for (int i = 0; i < articles.size(); i++) {
//                Article article = articles.get(i);
//                System.out.println((i + 1) + "、 " + article.getTitle());
//            }
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            if (choice == 0) {
//                break;
//            }
//            else if (choice > 0 && choice <= articles.size()) {
//                readArticle(scanner, articles.get(choice - 1), currentUser);
//            }
//            else {
//                System.out.println("无效选项！");
//            }
//        }
//    }
//
//    private static void readArticle(Scanner scanner, Article article, String currentUser) {
//        System.out.println("\n文章内容：");
//        System.out.println("题目：" + article.getTitle());
//        System.out.println("作者：" + article.getAuthor());
//        System.out.println("内容：" + article.getContent());
//        System.out.println("点赞量：" + article.getLikes());
//
//        System.out.println("评论：");
//        List<Comment> comments = CommentManager.loadComments(article.getId(),article.getTitle());
//        if (comments.isEmpty()) {
//            System.out.println("暂无评论！");
//        } else {
//            for (Comment comment : comments) {
//                if (comment.getId() != 0) {
//                    System.out.println(comment.getId() + "、" + comment.getContent());
//                }
//                else {
//                    System.out.println("    " + comment.getContent());
//                }
//            }
//        }
//
//        while (true) {
//            System.out.println("\n1. 输入 'good' 点赞");
//            System.out.println("2. 输入 'comments' 评论");
//            System.out.println("3. 输入 'reply' + 序号 回复评论（需登录）");
//            System.out.println("4. 输入 0 返回文章列表");
//            System.out.println("\n 请选择：");
//
//            String command = scanner.nextLine();
//            if (command.equals("0")) {
//                break;
//            }
//            else if (command.equals("good")) {
//                article.like();
//                saveArticle(article);
//                System.out.println("点赞成功！");
//            }
//            else if (command.equals("comments")) {
//                System.out.println("请输入评论内容：");
//                String content = scanner.nextLine();
//                CommentManager.addComment(article.getId(), content,article.getTitle() );
//                System.out.println("评论成功！");
//            }
//            else if (command.startsWith("reply")) {
//                String[] parts = command.split(" ");
//                if (currentUser != null) {
//                    if (parts.length == 2) {
//                        int commentIndex = Integer.parseInt(parts[1]) - 1;
//                        if (commentIndex >= 0 && commentIndex < comments.size()) {
//                            System.out.println("请输入回复内容：");
//                            String reply = scanner.nextLine();
//                            CommentManager.addreplyComment(article.getId(), comments.get(commentIndex).getId(), currentUser, reply,article.getTitle());
//                            System.out.println("回复成功！");
//                        }
//                        else {
//                            System.out.println("无效评论序号！");
//                        }
//                    }
//                    else {
//                        System.out.println("命令格式错误！");
//                    }
//                }
//                else{
//                    System.out.println("请先登录！");
//                }
//            }
//            else {
//                System.out.println("无效命令！");
//            }
//        }
//    }
//
//    private static void addArticle(String title, String content, String author) {
//        // 生成新的 ID，可以通过文件夹中文件的数量来确定
//        File folder = new File(ARTICLE_FOLDER);
//        int newId = folder.listFiles() != null ? folder.listFiles().length + 1 : 1;
//
//        Article newArticle = new Article(newId, title, content, author, 0); // 使用新的 ID
//        saveArticle(newArticle);
//        System.out.println("文章发布成功！");
//    }
//
//    private static int viewArticles(String currentUser) {
//        List<Article> articles = loadArticles();
//        int i = 0;
//        if (articles.isEmpty()) {
//            System.out.println("暂无文章！");
//            return 0;
//        }
//
//        System.out.println("文章列表：");
//        for (Article article : articles) {
//            if (article.getAuthor().equals(currentUser)) {
//                i++;
//                System.out.println(i + "、 " + article.getTitle());
//            }
//        }
//        return i;
//    }
//
//    private static List<Article> loadArticles() {
//        File folder = new File(ARTICLE_FOLDER);
//        List<Article> articles = new ArrayList<>();
//
//        if (folder.exists() && folder.isDirectory()) {
//            File[] files = folder.listFiles();
//            if (files != null) {
//                for (int i = 0; i < files.length; i++) {
//                    File file = files[i];
//                    if (file.getName().endsWith(FILE_SUFFIX)) {
//                        String title = file.getName().replace(FILE_SUFFIX, "");
//                        Article article = loadSingleArticle(title, i + 1);  // 分配唯一的 ID
//                        if (article != null) {
//                            articles.add(article);
//                        }
//                        else {
//                            System.out.println("忽略无法加载的文章文件：" + file.getName());
//                        }
//                    }
//                }
//            }
//        }
//        return articles;
//    }
//
//    private static Article loadSingleArticle(String title, int id) {
//        List<String> lines = FileHandler.readFile(ARTICLE_FOLDER, title, FILE_SUFFIX);
//
//        try {
//            if (lines.size() < 4) {
//                System.out.println("文件格式错误，缺少必要字段：" + title);
//                return null;
//            }
//
//            String articleTitle = lines.get(0).replace("题目：", "").trim();
//            String author = lines.get(1).replace("作者：", "").trim();
//            String content = lines.get(2).replace("内容：", "").trim();
//            int likes = Integer.parseInt(lines.get(3).replace("点赞量：", "").trim());
//
//            return new Article(id, articleTitle, content, author, likes); // 使用传入的 ID
//        } catch (Exception e) {
//            System.out.println("解析文章失败：" + title + "，错误：" + e.getMessage());
//            return null;
//        }
//    }
//
//    private static void saveArticle(Article article) {
//        List<String> lines = new ArrayList<>();
//        lines.add("题目：" + article.getTitle());
//        lines.add("作者：" + article.getAuthor());
//        lines.add("内容：" + article.getContent());
//        lines.add("点赞量：" + article.getLikes());
//        FileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines);
//    }
//
//    private static void delArticle(String title) {
//        String filePath = ARTICLE_FOLDER + title + FILE_SUFFIX;
//        String filePath_comments = "comments/" + title + FILE_SUFFIX;
//
//        // 创建 File 对象
//        File file = new File(filePath);
//        File file_comments = new File(filePath_comments);
//
//        // 检查文件是否存在并尝试删除
//        if (file.exists()) {
//            if (file.delete()) {
//                System.out.println("文件删除成功: " + filePath);
//            }
//            else {
//                System.out.println("文件删除失败: " + filePath);
//            }
//        }
//        else {
//            System.out.println("文件不存在: " + filePath);
//        }
//        if (file_comments.exists()) {
//            if (file_comments.delete()) {
//                System.out.println("文件删除成功: " + filePath_comments);
//            }
//            else {
//                System.out.println("文件删除失败: " + filePath_comments);
//            }
//        }
//        else {
//            System.out.println("文件不存在: " + filePath_comments);
//        }
//    }
//}
