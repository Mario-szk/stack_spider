package com.esy.stack.download.impl;

import com.esy.stack.util.HttpClientUtil;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by guanjie on 2017/3/21.
 */
@Log4j
public class ParseHtmlOrInterface {

    private static String FILE_163_PATH = "d://sql.txt";
    private static String FILE_163_CHAR = "utf-8";

    //	String[] pettern = {"http://money.163.com/"};
    String[] pettern = {"http://money.163.com"};
    @Test
    public void testAnaly() {
        File file = new File(FILE_163_PATH);
        try {
            Document htmlDoc = Jsoup.parse(file, FILE_163_CHAR);
            Elements newsMain = htmlDoc.select(".list_litpic a" );
            for (Element element : newsMain) {
                Elements h2tags = element.select("h2");
                if(h2tags == null || h2tags.isEmpty())
                    continue;
                log.info(h2tags.get(0).text());
            }
//			System.out.println(newsMain.get(0).attr("title"));
//            System.out.println(newsMain.get(0).select("h2").text());
           log.info(newsMain);
        } catch (IOException e) {
            log.error("", e);
        }
    }
    @Test
    public void testAnalaysTitle() {
        String pa = "<a href=\"http://money.163.com/photoview/0BGT0025/29324.html\">面包车路边停三年 贴约千条“野广告\"</a>";
        Document doc = Jsoup.parse(pa);
        Elements e = doc.getElementsByTag("a");
        System.out.println(e);
        System.out.println(e.text());
        System.out.println(e.html());
        System.out.println(e.outerHtml());
        System.err.println(e.attr("href"));
    }

    @Test
    public void testDownloadHtml() throws Exception {
        HttpClientUtil.downloadToFile("http://m.chinatimes.cc/finance/guandian", "utf-8", new File("d://sql.txt"));
    }

}
