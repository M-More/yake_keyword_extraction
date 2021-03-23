import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import home.tut.yake.Yake;

public class AllDataKE {
    public static void main(String[] args) {
        StringBuilder altogether = new StringBuilder();
        File file = new File("/Users/MMore/Desktop/news/中兴");
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            File file_tmp = tempList[i];
            BufferedReader reader = null;
            StringBuffer sbf = new StringBuffer();
            try {
                reader = new BufferedReader(new FileReader(file_tmp));
                String tempStr;
                while ((tempStr = reader.readLine()) != null) {
                    sbf.append(tempStr);
                }
                reader.close();
                altogether.append(sbf.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        long start=  System.currentTimeMillis();
//        List<String> keywordList = HanLP.extractKeyword(altogether.toString(), 25);
        List<String> keywordList = new TextRankKeyword().getKeyword("",altogether.toString());
//        List<String> keywordList = new Yake.KeywordExtractor().extract_keywords(altogether.toString());
        for (String s : keywordList)
        {
            System.out.println(s);
        }
        long end=  System.currentTimeMillis();
        System.out.println(end-start);
    }
}
