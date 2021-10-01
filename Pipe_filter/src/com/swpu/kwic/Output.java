package com.swpu.kwic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Output extends Filter{

    //输出文件的文件名
    private File file;
    Output(Pipe input, File file){
        super(input, null);
        this.file = file;
    }

    //输出数据
    @Override
    protected void transform() throws IOException {
        PrintWriter pw = new PrintWriter(file);
        String templine = "";
        while((templine = input.readerLine()) != null){
            pw.write(templine);
            pw.write("\n");
        }
        pw.flush();
        pw.close();
        input.closeReader();
    }
}