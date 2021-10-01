package com.swpu.kwic;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class Pipe {
    //输入管道
    private Scanner pipereader;
    //输出管道
    private PrintWriter pipewriter;

    public Pipe(){
        PipedWriter pw = new PipedWriter();
        PipedReader pr = new PipedReader();
        try{
            pw.connect(pr);
        } catch (IOException e){
            e.getMessage();
        }
        pipewriter = new PrintWriter(pw);
        pipereader = new Scanner(pr);
    }

    //读入一行数据到管道
    public String readerLine() throws IOException{
        if (pipereader.hasNextLine()) {
            return pipereader.nextLine();
        }
        return null;
    }
    //从管道输出一行数据
    public void writerLine(String strline) throws IOException{
        pipewriter.println(strline);
    }
    //将读管道关闭
    public void closeReader() throws IOException{
        pipereader.close();
    }
    //将写管道关闭
    public void closeWriter() throws IOException{
        pipewriter.flush();
        pipewriter.close();
    }
}