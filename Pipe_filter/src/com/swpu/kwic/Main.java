package com.swpu.kwic;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //定义输入和输出文件
        File infile = new File("D:\\大三上学习文档\\软件体系结构\\软件体系结构实验\\input.txt");
        File outfile = new File("D:\\大三上学习文档\\软件体系结构\\软件体系结构实验\\output.txt");

        Scanner inputfile;
        Scanner outputfile;

        try {
            inputfile = new Scanner(infile);
            outputfile = new Scanner(outfile);
            // 定义三个管道
            Pipe pipe1 = new Pipe();
            Pipe pipe2 = new Pipe();
            Pipe pipe3 = new Pipe();

            // 定义四种过滤器
            //输入
            Input input = new Input(infile, pipe1);
            //循环移位
            Shift shift = new Shift(pipe1, pipe2);
            //排序
            Alphabetizer alph = new Alphabetizer(pipe2, pipe3);
            //输出
            Output output = new Output(pipe3, outfile);

            //执行四个过滤器线程
            input.start();
            // 执行完输入过滤器后打印从input文件读取的文本
            System.out.println("-----  输入   -----");
            String str = null;
            while (inputfile.hasNextLine()) {
                str = inputfile.nextLine();
                System.out.println(str);
            }
            shift.start();
			alph.start();
			output.start();

            inputfile.close();
            outputfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}