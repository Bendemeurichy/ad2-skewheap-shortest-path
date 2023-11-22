package benchmark;

import opgave.PriorityQueue;
import oplossing.SkewHeap;
import oplossing.SkewHeapFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SkewHeapBenchmark {

    public static void main(String[] args) throws IOException {
        long size = 10_000_000;
        //create writers
        BufferedWriter addSkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapAddBenchTime.csv"));
        BufferedWriter addSkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewHeapAddBenchComp.csv"));
        BufferedWriter popAndDecreaseKeySkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewDecreaseKeyHeapBench.csv"));
        BufferedWriter popAndDecreaseKeySkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewDecreaseKeyHeapBenchComp.csv"));
        BufferedWriter popSkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapPopBenchTime.csv"));
        BufferedWriter popSkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewHeapPopBenchComp.csv"));
        SkewHeapBenchmark benchmark = new SkewHeapBenchmark();

        //run benchmarks
        benchmark.addBench(addSkewHeapTime,addSkewHeapComp,size);
        benchmark.popAndDecreaseKeyBench(popAndDecreaseKeySkewHeapTime,popAndDecreaseKeySkewHeapComp,size);
        benchmark.popBench(popSkewHeapTime,popSkewHeapComp,size);

        //close writers
        popSkewHeapTime.close();
        popSkewHeapComp.close();
        popAndDecreaseKeySkewHeapTime.close();
        popAndDecreaseKeySkewHeapComp.close();
        addSkewHeapTime.close();
        addSkewHeapComp.close();
    }

    public void addBench(BufferedWriter timeWriter,BufferedWriter compWriter, long size) {
        Random rnd = new Random();

        for(long i=1;i<size;i+=size/1000){
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();

            long start=System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                skewHeap.add(rnd.nextInt(1000), i * 1.0);
            }
            long end=System.currentTimeMillis();
            System.out.println("Time: "+(end-start)+"ms");
            try {
                timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((SkewHeap<Integer, Double>) skewHeap).getCompareCount())).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void popAndDecreaseKeyBench(BufferedWriter timeWriter,BufferedWriter compWriter,long size){
        //TODO
        Random rnd = new Random();
        for(long i=1;i<size;i+=size/1000) {
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();

            for (int j = 0; j < i; j++) {
                skewHeap.add(1000+rnd.nextInt(1000), i * 1.0);
            }

            long start = System.currentTimeMillis();
            for(int j=0;j<i;j++){
                if(i%3==0){
                    skewHeap.poll();
                }else {
                    skewHeap.peek().decreaseKey(rnd.nextInt(1000));
                }

            }
            long end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start) + "ms");
            try {
                timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((SkewHeap<Integer,Double>)skewHeap).getCompareCount())).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void popBench(BufferedWriter timeWriter,BufferedWriter compWriter,long size){
        //TODO
        Random rnd = new Random();
        for(long i=1;i<size;i+=size/1000) {
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();

            for (int j = 0; j < i; j++) {
                skewHeap.add(rnd.nextInt(1000), i * 1.0);
            }

            long start = System.currentTimeMillis();
            for(int j=0;j<i;j++){
                skewHeap.poll();
            }
            long end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start) + "ms");
            try {
                timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((SkewHeap<Integer,Double>)skewHeap).getCompareCount())).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}