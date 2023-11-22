package benchmark;
import opgave.PriorityQueue;
import oplossing.MyPriorityQueue;
import oplossing.MyPriorityQueueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LeftistHeapBenchmark {
        public static void main(String[] args) throws IOException {
            long size = 10_000_000;
            //create writers
            BufferedWriter addLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapAddBenchTime.csv"));
            BufferedWriter addLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistHeapAddBenchComp.csv"));
            BufferedWriter popAndDecreaseKeyLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistDecreaseKeyHeapBench.csv"));
            BufferedWriter popAndDecreaseKeyLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistDecreaseKeyHeapBenchComp.csv"));
            BufferedWriter popLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapPopBenchTime.csv"));
            BufferedWriter popLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistHeapPopBenchComp.csv"));
            benchmark.LeftistHeapBenchmark benchmark = new benchmark.LeftistHeapBenchmark();

            //run benchmarks
            benchmark.addBench(addLeftistHeapTime,addLeftistHeapComp,size);
            benchmark.popAndDecreaseKeyBench(popAndDecreaseKeyLeftistHeapTime,popAndDecreaseKeyLeftistHeapComp,size);
            benchmark.popBench(popLeftistHeapTime,popLeftistHeapComp,size);

            //close writers
            popLeftistHeapTime.close();
            popLeftistHeapComp.close();
            popAndDecreaseKeyLeftistHeapTime.close();
            popAndDecreaseKeyLeftistHeapComp.close();
            addLeftistHeapTime.close();
            addLeftistHeapComp.close();
        }

        public void addBench(BufferedWriter timeWriter,BufferedWriter compWriter, long size) {
            Random rnd = new Random();

            for(long i=1;i<size;i+=size/1000){
                PriorityQueue<Integer, Double> skewHeap = (new MyPriorityQueueFactory()).create();

                long start=System.currentTimeMillis();
                for (int j = 0; j < i; j++) {
                    skewHeap.add(rnd.nextInt(1000), i * 1.0);
                }
                long end=System.currentTimeMillis();
                System.out.println("Time: "+(end-start)+"ms");
                try {
                    timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer, Double>) skewHeap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void popAndDecreaseKeyBench(BufferedWriter timeWriter,BufferedWriter compWriter,long size){
            //TODO
            Random rnd = new Random();
            for(long i=1;i<size;i+=size/1000) {
                PriorityQueue<Integer, Double> skewHeap = (new MyPriorityQueueFactory()).create();

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
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer,Double>)skewHeap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void popBench(BufferedWriter timeWriter,BufferedWriter compWriter,long size){
            //TODO
            Random rnd = new Random();
            for(long i=1;i<size;i+=size/1000) {
                PriorityQueue<Integer, Double> skewHeap = (new MyPriorityQueueFactory()).create();

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
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer,Double>)skewHeap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}

