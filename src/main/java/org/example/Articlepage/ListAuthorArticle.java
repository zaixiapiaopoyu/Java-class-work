package org.example.Articlepage;

import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;

public class ListAuthorArticle {
    public static int listauthorarticle(String currentUser) {
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
}
