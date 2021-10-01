package com.swpu.kwic;

import java.io.IOException;
import java.util.ArrayList;
public class Shift extends Filter{

    //单词的动态数组
    private ArrayList<String> wordlist = new ArrayList<String>();
    //移位重组后的动态数组
    private ArrayList<String> linelist = new ArrayList<String>();

    Shift(Pipe input, Pipe output){
        super(input, output);
    }

    @Override
    protected void transform() throws IOException {
        String templine = "";
        //读数据
        System.out.println("-----  循环移位重组后的结果：  -----");
        while((templine = input.readerLine()) != null){

            //将数据拆分为不同单词
            this.lineSplitWord(templine);

            //将单词移位重组为句子（依次将第一个单词移到最后一位）
            this.recombination();

            //输出移位重组后的结果
            for(int i = 0; i < linelist.size(); i++){
                System.out.println(linelist.get(i));
                output.writerLine(linelist.get(i));
            }

            //清空wordlist、linelist和templine
            wordlist.clear();
            linelist.clear();
            templine = "";
        }
        input.closeReader();
        output.closeWriter();
    }

    //从一行中提取单词存入单词表中
    private void lineSplitWord(String line){
        String word = "";
        int i = 0;
        while(i < line.length()){
            if(line.charAt(i) != ' '){
                word += line.charAt(i);
            }
            else{
                wordlist.add(word);
                word = "";
            }
            i++;
        }
        if (word.length() > 0) {
            wordlist.add(word);
        }
    }

    //对每个单词进行循环移位操作
    private void recombination(){
        String templine = "",str = "";
        int len = wordlist.size();
        int k = wordlist.size();
        int i;
        while(len > 0){
            for(i = 0;i < k;i++){
                templine = templine + wordlist.get(i)+" ";
            }
            for(i = 0;i < k; i++){
                if(i == 0){
                    str = wordlist.get(i);
                }
                if(i!=0){
                    wordlist.set(i-1,wordlist.get(i));
                }
            }
            wordlist.set(k-1,str);
            linelist.add(templine);
            templine="";
            len--;
        }
    }
}