package com.swpu.kwic;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

class Alphabetizer extends Filter{
    private ArrayList<String> al = new ArrayList<String>();

    Alphabetizer(Pipe input, Pipe output){
        super(input, output);
    }

    //对读入的数据进行排序
    @Override
    protected void transform() throws IOException {
        String templine = null;

        //读入数据
        while((templine = input.readerLine()) != null){
            al.add(templine);
        }
        //按字母表排序
        Collections.sort(al, new AlphaabetizerComparator());
        //对排序后的数据进行输出
        for(int i = 0; i < al.size(); i++){
            output.writerLine(al.get(i));
        }
        input.closeReader();
        output.closeWriter();
    }
    private class AlphaabetizerComparator implements Comparator<String> {
    	private Collator collator;
    	AlphaabetizerComparator(){
    		this.collator = Collator.getInstance(Locale.ENGLISH);
    	}
		@Override
		public int compare(String o1, String o2) {
			return this.collator.compare(o1, o2);
		}
    }
}