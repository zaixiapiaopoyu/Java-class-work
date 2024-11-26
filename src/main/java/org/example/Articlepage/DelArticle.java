package org.example.Articlepage;

import java.io.File;

/**
 * DelArticle 类用于删除指定的文章文件以及其对应的评论文件。
 */

public class DelArticle {

    private final String ARTICLE_FOLDER = "data/articles/";
    private final String FILE_SUFFIX = "_article.txt";

    /**
     * 删除文章的方法，包括文章文件和对应的评论文件。
     *
     * @param article 要删除的文章对象
     */

    public void delArticle(Article article) {
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
