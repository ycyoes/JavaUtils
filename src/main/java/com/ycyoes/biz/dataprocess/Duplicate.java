package com.ycyoes.biz.dataprocess;

import com.ycyoes.demos.test.text.HandleText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duplicate {
    public static void main(String[] args) {
        List<String> cardNums = HandleText.readFile("e:/person.txt");
        System.out.println(cardNums + "\n" + cardNums.size());
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < cardNums.size(); i++) {
            String cardNum = cardNums.get(i).trim();
            if (map.containsKey(cardNum)) {
                System.out.println("身份证号重复：" + cardNum);
            }
            map.put(cardNum, i + "");
        }
        System.out.println(map.size());
    }
}
