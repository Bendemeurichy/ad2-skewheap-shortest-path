package benchmark;

import opgave.PriorityQueue;
import opgave.QueueItem;
import oplossing.MyPriorityQueue;
import oplossing.MyPriorityQueueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LeftistHeapBenchmark {
        public static void main(String[] args) throws IOException {
            int size = 10_000_000;
            int steps = 50;
            //create writers
            BufferedWriter addLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapAddBenchTime.csv"));
            BufferedWriter addLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistHeapAddBenchComp.csv"));
            BufferedWriter popAndDecreaseKeyLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapDecreaseKeyBenchTime.csv"));
            BufferedWriter popAndDecreaseKeyLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistHeapDecreaseKeyBenchComp.csv"));
            BufferedWriter popLeftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapPopBenchTime.csv"));
            BufferedWriter popLeftistHeapComp = new BufferedWriter(new FileWriter("benchmark/LeftistHeapPopBenchComp.csv"));
            benchmark.LeftistHeapBenchmark benchmark = new benchmark.LeftistHeapBenchmark();

            //run benchmarks
            System.out.println("addbench started");
            benchmark.addBench(addLeftistHeapTime,addLeftistHeapComp,size,steps);
            System.out.println("popanddecreasebench started");
            benchmark.popAndDecreaseKeyBench(popAndDecreaseKeyLeftistHeapTime,popAndDecreaseKeyLeftistHeapComp,size,steps);
            System.out.println("popbench started");
            benchmark.popBench(popLeftistHeapTime,popLeftistHeapComp,size,steps);

            //close writers
            popLeftistHeapTime.close();
            popLeftistHeapComp.close();
            popAndDecreaseKeyLeftistHeapTime.close();
            popAndDecreaseKeyLeftistHeapComp.close();
            addLeftistHeapTime.close();
            addLeftistHeapComp.close();
        }

        public void addBench(BufferedWriter timeWriter,BufferedWriter compWriter, int size,int steps) {

            for(int i=1;i<=size;i+=size/steps){
                PriorityQueue<Integer, Double> leftistheap = (new MyPriorityQueueFactory()).create();

                long start=System.currentTimeMillis();
                for (int j = 0; j < i; j++) {
                    leftistheap.add(j, i * 1.0);
                }
                long end=System.currentTimeMillis();
                System.out.println("Time: "+(end-start)+"ms");
                try {
                    timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer, Double>) leftistheap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void popAndDecreaseKeyBench(BufferedWriter timeWriter,BufferedWriter compWriter,int size,int steps){
            //TODO
            for(int i=1;i<=size;i+=size/steps) {
                PriorityQueue<Integer, Double> leftistheap = (new MyPriorityQueueFactory()).create();
                QueueItem<Integer,Double>[] items = new QueueItem[(int)i];

                for (int j = 0; j < i; j++) {
                    items[j]=leftistheap.add(i+j, i * 1.0);
                }

                long start = System.currentTimeMillis();
                for(int j=0;j<i;j++){
                    if(i%2==0){
                        leftistheap.poll();
                    }else {
                        items[j].decreaseKey(i-j);
                    }

                }
                long end = System.currentTimeMillis();
                System.out.println("Time: " + (end - start) + "ms");
                try {
                    timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer,Double>)leftistheap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void popBench(BufferedWriter timeWriter,BufferedWriter compWriter,int size,int steps){
            //TODO
            for(int i=1;i<=size;i+=size/steps) {
                PriorityQueue<Integer, Double> leftistheap = (new MyPriorityQueueFactory()).create();

                for (int j = 0; j < i; j++) {
                    leftistheap.add(i+j, i * 1.0);
                }

                long start = System.currentTimeMillis();
                for(int j=0;j<i;j++){
                    leftistheap.poll();
                }
                long end = System.currentTimeMillis();
                System.out.println("Time: " + (end - start) + "ms");
                try {
                    timeWriter.append(String.valueOf(i)).append(";").append(String.valueOf(end - start)).append("\n");
                    compWriter.append(String.valueOf(i)).append(";").append(String.valueOf(((MyPriorityQueue<Integer,Double>)leftistheap).getCompareCount())).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}

