import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hankcs.hanlp.HanLP;
import home.tut.yake.Yake;

public class LargeListIntegerSum {

    private List<List<String>> sum = new ArrayList<>();
    private CyclicBarrier barrier;//障栅集合点(同步器)
    private List<String> list;
    private int threadCounts;//使用的线程数
    public LargeListIntegerSum(List<String> list,int threadCounts) {
        this.list=list;
        this.threadCounts=threadCounts;
    }
    /**
     * 获取List中所有整数的和
     * @return
     */
    public List<List<String>> getIntegerSum(){
        ExecutorService exec=Executors.newFixedThreadPool(threadCounts);
        int len=list.size()/threadCounts;//平均分割List
        //List中的数量没有线程数多（很少存在）
        if(len==0){
            threadCounts=list.size();//采用一个线程处理List中的一个元素
            len=list.size()/threadCounts;//重新平均分割List
        }
        barrier=new CyclicBarrier(threadCounts+1);
        for(int i=0;i<threadCounts;i++){
            //创建线程任务
            if(i==threadCounts-1){//最后一个线程承担剩下的所有元素的计算
                exec.execute(new SubIntegerSumTask(list.subList(i*len,list.size())));
            }else{
                exec.execute(new SubIntegerSumTask(list.subList(i*len, len*(i+1)>list.size()?list.size():len*(i+1))));
            }
        }
        try {
            barrier.await();//关键，使该线程在障栅处等待，直到所有的线程都到达障栅处
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+":Interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println(Thread.currentThread().getName()+":BrokenBarrier");
        }
        exec.shutdown();
        return sum;
    }
    /**
     * 分割计算List整数和的线程任务
     *
     */
    public class SubIntegerSumTask implements Runnable{
        private List<String> subList;
        public SubIntegerSumTask(List<String> subList) {
            this.subList=subList;
        }
        public void run() {
            List<List<String>> subSum = new ArrayList<>();
            for (String i : subList) {
//                List<String> keywordList = HanLP.extractKeyword(i, 25);
//                List<String> keywordList = new TextRankKeyword().getKeyword("",i);
                List<String> keywordList = new Yake.KeywordExtractor().extract_keywords(i);
                subSum.add(keywordList);
            }
            synchronized(LargeListIntegerSum.this){ //在LargeListIntegerSum对象上同步
                sum.addAll(subSum);
            }
            try {
                barrier.await();    //关键，使该线程在障栅处等待，直到所有的线程都到达障栅处
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+":Interrupted");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + ":BrokenBarrier");
            }
        }

    }


    public static void main(String[] args) {
        List<String> fileContents = new ArrayList<String>();
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
                fileContents.add(sbf.toString());
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


        int threadCounts = 8;//采用的线程数

        long start=  System.currentTimeMillis();
        LargeListIntegerSum countListIntegerSum=new LargeListIntegerSum(fileContents,threadCounts);

        List<List<String>> sum=countListIntegerSum.getIntegerSum();
        Map<String, Integer> wordScore = new HashMap<>();
        for (List<String> singleDocList : sum)
        {
            for (int i=0; i<singleDocList.size(); i++){
                if (!wordScore.containsKey(singleDocList.get(i))){
                    wordScore.put(singleDocList.get(i), 0);
                }
                wordScore.put(singleDocList.get(i),wordScore.get(singleDocList.get(i))+(singleDocList.size()-i));
            }
        }
        List<Map.Entry<String, Integer>> keywordList = new ArrayList<Map.Entry<String, Integer>>(wordScore.entrySet());
        Collections.sort(keywordList, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return Integer.compare(0, o1.getValue() - o2.getValue());
            }
        });
//        for(Map.Entry<String, Integer> e : keywordList)
//        {
//            System.out.println(e.getKey());
//        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

}
