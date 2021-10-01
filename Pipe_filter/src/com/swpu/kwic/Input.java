package com.swpu.kwic;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Input extends Filter{

    //输入文件的文件名
    private File infile;
    Input(File file, Pipe output){
        super(null, output);
        this.infile = file;
    }

    @Override
    //读取数据
    protected void transform() throws IOException {
        Scanner sc = new Scanner(infile);
        String templine = "";
        while(sc.hasNextLine()){
            templine = sc.nextLine();
            output.writerLine(templine);
        }
        output.closeWriter();
        sc.close();
    }
}
