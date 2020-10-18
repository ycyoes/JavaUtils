package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;
import com.ycyoes.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycyoes.demos.test.text.HandleText.readFile;

public class SqlFormatFamilyPerson {
        public static void main(String[] args) {
        String name = "D:/huzhu.txt";
        List<String> list = readFile(name);
        String head = "insert into ZZ_FAMILY_PERSON_202010181500(id, p_id, f_id, relation, create_date, create_user) select sys_guid(), (select id from zz_person where card_num='612525197901025849'), (select f.id from ZZ_FAMILY f inner join zz_person p on f.p_id=p.id where p.card_num='";
        String tail = "'), '0', sysdate, 'system' from dual;";
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String cardNum = list.get(i);
            if (StringUtils.isNotBlank(cardNum)) {
                int len = cardNum.length();
                if (len < 18) {
                    System.out.println("长度: " + len + " 身份证长度不符 " + cardNum);
                }
                map.put(cardNum, i);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            sb.append(head + entry.getKey() + tail + "\n");
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
            System.out.println(sb.toString());
        FileUtils.writeToFile("D:/sql_person1.txt", sb.toString(), false);
    }
}
