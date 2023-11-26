package benchmark;

import opgave.PriorityQueue;
import opgave.QueueItem;
import oplossing.SkewHeap;
import oplossing.SkewHeapFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SkewHeapBenchmark {

    public static void main(String[] args) throws IOException {
        int size = 10_000_000;
        int steps= 50;
        //create writers
        BufferedWriter addSkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapAddBenchTime.csv"));
        BufferedWriter addSkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewHeapAddBenchComp.csv"));
        BufferedWriter popAndDecreaseKeySkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapDecreaseKeyBenchTime.csv"));
        BufferedWriter popAndDecreaseKeySkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewHeapDecreaseKeyBenchComp.csv"));
        BufferedWriter popSkewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapPopBenchTime.csv"));
        BufferedWriter popSkewHeapComp = new BufferedWriter(new FileWriter("benchmark/SkewHeapPopBenchComp.csv"));
        SkewHeapBenchmark benchmark = new SkewHeapBenchmark();

        //run benchmarks
        System.out.println("addbench started");
        benchmark.addBench(addSkewHeapTime,addSkewHeapComp,size,steps);
        System.out.println("popanddecreasebench started");
        benchmark.popAndDecreaseKeyBench(popAndDecreaseKeySkewHeapTime,popAndDecreaseKeySkewHeapComp,size,steps);
        System.out.println("popbench started");
        benchmark.popBench(popSkewHeapTime,popSkewHeapComp,size,steps);

        //close writers
        popSkewHeapTime.close();
        popSkewHeapComp.close();
        popAndDecreaseKeySkewHeapTime.close();
        popAndDecreaseKeySkewHeapComp.close();
        addSkewHeapTime.close();
        addSkewHeapComp.close();
    }

    public void addBench(BufferedWriter timeWriter,BufferedWriter compWriter, int size,int steps) {
        Random rnd = new Random();

        for(int i=1;i<=size;i+=size/steps){
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();

            long start=System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                skewHeap.add(j, i * 1.0);
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

    public void popAndDecreaseKeyBench(BufferedWriter timeWriter,BufferedWriter compWriter,int size,int steps){
        //TODO
        for(int i=1;i<=size;i+=size/steps) {
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();
            QueueItem<Integer,Double>[] items = new QueueItem[(int)i];


            for (int j = 0; j < i; j++) {
                items[j]=skewHeap.add(i+j, i * 1.0);
            }

            long start = System.currentTimeMillis();
            for(int j=0;j<i;j++){
                if(i%2==0){
                    skewHeap.poll();
                }else {
                    items[j].decreaseKey(i-j);
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

    public void popBench(BufferedWriter timeWriter,BufferedWriter compWriter,int size,int steps){
        //TODO
        for(int i=1;i<=size;i+=size/steps) {
            PriorityQueue<Integer, Double> skewHeap = (new SkewHeapFactory()).create();

            for (int j = 0; j < i; j++) {
                skewHeap.add(i+j, i * 1.0);
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