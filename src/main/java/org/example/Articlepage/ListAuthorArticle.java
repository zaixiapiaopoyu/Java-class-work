package org.example.Articlepage;

import org.example.AllCanUse.NewPage;
import org.example.AllCanUse.load;

import java.util.List;

/**
 * ListAuthorArticle 类用于列出当前用户发布的所有文章标题。
 */

public class ListAuthorArticle {

    /**
     * 列出指定用户的所有文章标题。
     *
     * @param currentUser 当前用户的用户名
     * @return 当前用户发布的文章总数
     */

    public int listauthorarticle(String currentUser) {
        NewPage newPage = new NewPage();
        newPage.newpage();
        load load = new load();
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
