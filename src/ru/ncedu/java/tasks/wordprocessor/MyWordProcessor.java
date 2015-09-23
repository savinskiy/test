/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.wordprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.StringTokenizer;
/**
 *
 * @author DimaZ
 */
public class MyWordProcessor implements WordProcessor {

    private String text;
    
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setSource(String src) {
        text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        text = "";
        File file = new File(srcFile);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            //variable line does NOT have new-line-character at the end
            text += line + '\n';
        }
        br.close();
    }
    
    @Override
    public void setSource (FileInputStream fis) throws IOException {
        text = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            //variable line does NOT have new-line-character at the end
            text += line + '\n';
        }
        br.close();
    }
    
    @Override
    public Set<String> wordsStartWith (String begin) {
        Set<String> result = new HashSet<String>();
        StringTokenizer st = new StringTokenizer(text, "\t\n\r ");
        while(st.hasMoreTokens()){
            String tmp = st.nextToken();
            String tmp2 = tmp.toLowerCase();
            if(tmp2.startsWith(begin.toLowerCase()))
            {
                result.add(tmp);
            }
        }
        return result;
    }
}
