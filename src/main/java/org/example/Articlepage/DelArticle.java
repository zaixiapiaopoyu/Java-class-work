package org.example.Articlepage;

import java.io.File;

public class DelArticle {

    private static final String ARTICLE_FOLDER = "data/articles/";
    private static final String FILE_SUFFIX = "_article.txt";

    public static void delArticle(Article article) {
        String title = article.getTimes().TimeMilliseconds()+ "_" + article.getTitle();
        String filePath = ARTICLE_FOLDER + title + FILE_SUFFIX;
        String filePath_comments = "data/comments/" + title + "_comments.txt ";

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
}
