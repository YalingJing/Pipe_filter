package com.swpu.kwic;

import java.io.IOException;

public abstract class Filter implements Runnable {
    // 定义输入管道
    protected Pipe input;
    // 定义输出管道
    protected Pipe output;

    private boolean isStart = false;

    //构造函数
    Filter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    //调用之后线程开始执行
    public void start() {
        if (!isStart) {
            isStart = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            this.transform();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    // 将输入数据转换为所需数据并写入输出管道
    protected abstract void transform() throws IOException;
}