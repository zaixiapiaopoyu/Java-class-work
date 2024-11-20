package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.ReadArticle.readArticle;

public class ArticleManagement {
    private static final String ARTICLE_FOLDER = "articles/";
    private static final String FILE_SUFFIX = "_article.txt";
    public static void ArticleManage(boolean isLoggedIn,Scanner scanner,String currentUser) {
        NewPage.newpage();
        if (!isLoggedIn) {
            System.out.println("请先登录！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            manageArticles(scanner, currentUser);
        }
    }

    public static void manageArticles(Scanner scanner, String currentUser) {
        while (true) {
            NewPage.newpage();
            System.out.print("\n\n ---------");
            System.out.println("\n| 文章管理 |");
            System.out.println(" ---------\n");
            System.out.println("1. 发布文章");
            System.out.println("2. 查看文章列表");
            System.out.println("3. 返回主界面");
            System.out.println("\n请选择：");


            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                NewPage.newpage();
                System.out.print("\n\n ---------");
                System.out.println("\n| 发布文章 |");
                System.out.println(" ---------\n");
                System.out.println("输入 # 取消发布文章\n");
                System.out.println("请输入文章标题：");
                String title = scanner.nextLine();
                if (title.equals("#")) {
                    continue;
                }
                System.out.println("请输入文章内容：");
                String content = scanner.nextLine();
                if (content.equals("#")) {
                    continue;
                }
                Times Times = TimesTamp.timestamp();
                addArticle(title, content, currentUser,Times);
                System.out.println("已成功发布文章！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (choice.equals("2")) {
                List<Article> articles = load.loadArticles();
                while(true) {
                    int len = viewArticles(currentUser);
                    if(len < 0){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    System.out.println("\n1. 输入文章编号查看文章");
                    System.out.println("2. 输入 del 文章编号删除文章");
                    System.out.println("3.输入 change 文章编号重新编辑文章");
                    System.out.println("4. 输入 0 返回文章管理界面");
                    System.out.println("\n请选择：");
                    String choices = scanner.nextLine().trim();
                    if (choices.equals("0")) {
                        break;
                    }
                    else{
                        int i = 0;
                        int j = 0;
                        if(choices.startsWith("del")){
                            String[] parts = choices.split(" ");
                            if(parts.length == 2){
                                int id = Integer.parseInt(parts[1]);
                                for(Article article : articles) {
                                    if (article.getAuthor().equals(currentUser)) {
                                        i++;
                                        if(i == id){
                                            delArticle(article);
                                        }
                                    }
                                    j++;
                                }
                            }
                            else {
                                System.out.println("请输入文章序号！");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        else if(choices.startsWith("change")){
                            String[] parts = choices.split(" ");
                            if(parts.length == 2){
                                int id = Integer.parseInt(parts[1]);
                                for(Article article : articles) {
                                    if (article.getAuthor().equals(currentUser)) {
                                        i++;
                                        if(i == id){
                                            changeArticle(article);
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("请输入文章序号！");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        else if (Integer.parseInt(choices)> 0 && Integer.parseInt(choices) <= len) {
                            for(Article article : articles) {
                                if (article.getAuthor().equals(currentUser)) {
                                    i++;
                                    if(i == Integer.parseInt(choices)){
                                        ReadArticle.readArticle(scanner, articles.get(j), currentUser);
                                        break;
                                    }
                                }
                                j++;
                            }
                        }
                        else {
                            System.out.println("无效选项！");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            else if (choice.equals("3")) {
                break;
            }
            else {
                System.out.println("无效选项！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void addArticle(String title, String content, String author,Times times) {
        // 生成新的 ID，可以通过文件夹中文件的数量来确定
        File folder = new File(ARTICLE_FOLDER);
        int newId = folder.listFiles() != null ? folder.listFiles().length + 1 : 1;

        Article newArticle = new Article(newId, title, content, author, 0,times); // 使用新的 ID
        saveAuthorArticle(newArticle,times);
    }
    private static int viewArticles(String currentUser) {
        NewPage.newpage();
        List<Article> articles = load.loadArticles();
        int i = 0;
        if (articles.isEmpty()) {
            System.out.println("暂无文章！");
            return 0;
        }

        System.out.println("文章列表：");
        for (Article article : articles) {
            if (article.getAuthor().equals(currentUser)) {
                i++;
                System.out.println(i + "、 " + article.getTitle());
            }
        }
        return i;
    }



    public static Article loadSingleArticle(String title, int id) {
        List<String> lines = FileHandler.readFile(ARTICLE_FOLDER, title, FILE_SUFFIX);

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

    public static void saveArticle(Article article) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + article.getTimes().print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

    public static void saveAuthorArticle(Article article,Times times) {
        List<String> lines = new ArrayList<>();
        lines.add("题目：" + article.getTitle());
        lines.add("作者：" + article.getAuthor());
        lines.add("时间：" + times.print());
        lines.add("内容：" + article.getContent());
        lines.add("点赞量：" + article.getLikes());
        FileHandler.writeFile(ARTICLE_FOLDER, article.getTitle(), FILE_SUFFIX, lines,article.getTimes().TimeMilliseconds());
    }

    private static void delArticle(Article article) {
        String title = article.getTimes().TimeMilliseconds()+ "_" + article.getTitle();
        String filePath = ARTICLE_FOLDER + title + FILE_SUFFIX;
        String filePath_comments = "comments/" + title + FILE_SUFFIX;

        // 创建 File 对象
        File file = new File(filePath);
        File file_comments = new File(filePath_comments);

        // 检查文件是否存在并尝试删除
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("成功删除文章: " + article.getTitle());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (file_comments.exists()) {
            if (file_comments.delete()) {
            }
        }
    }

    private static void changeArticle(Article article) {

        NewPage.newpage();
        System.out.print("\n\n ---------");
        System.out.println("\n| 修改文章 |");
        System.out.println(" ---------\n");
        System.out.println("输入 # 取消修改文章\n");
        System.out.println("请输入文章标题：");
        Scanner scanner = new Scanner(System.in);
        String newtitle = scanner.nextLine();
        if (newtitle.equals("#")) {
            return;
        }
        System.out.println("请输入文章内容：");
        String content = scanner.nextLine();
        if (content.equals("#")) {
            return;
        }
        Times Times = TimesTamp.timestamp();
        addArticle(newtitle, content, article.getAuthor(),Times);
        System.out.println("已成功修改文章！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String title = article.getTimes().TimeMilliseconds()+ "_" + article.getTitle();
        String filePath = ARTICLE_FOLDER + title + FILE_SUFFIX;
        String filePath_comments = "comments/" + title + FILE_SUFFIX;

        // 创建 File 对象
        File file = new File(filePath);
        File file_comments = new File(filePath_comments);

        // 检查文件是否存在并尝试删除
        if (file.exists()) {
            if (file.delete()) {
            }
        }
        if (file_comments.exists()) {
            if (file_comments.delete()) {
            }
        }
    }

}
