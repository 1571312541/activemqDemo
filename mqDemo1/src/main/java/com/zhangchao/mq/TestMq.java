package com.zhangchao.mq;

/**
 * @Author zc
 * @Date 2018/10/9 11:12
 **/
public class TestMq {
    public static void main(String[] args){
        Producer Producer = new Producer();
        Producer.init();
        TestMq testMq = new TestMq();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread 1
        new Thread(testMq.new ProductorMq(Producer)).start();
        //Thread 2
        new Thread(testMq.new ProductorMq(Producer)).start();
        //Thread 3
        new Thread(testMq.new ProductorMq(Producer)).start();
        //Thread 4
        new Thread(testMq.new ProductorMq(Producer)).start();
        //Thread 5
        new Thread(testMq.new ProductorMq(Producer)).start();
    }

    private class ProductorMq implements Runnable{
        Producer Producer;
        public ProductorMq(Producer Producer){
            this.Producer = Producer;
        }

        public void run() {
            while(true){
                try {
                    Producer.sendMessage("Jaycekon-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
