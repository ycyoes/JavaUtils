package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;
import com.ycyoes.utils.StringUtils;

import java.util.List;

import static com.ycyoes.demos.test.text.HandleText.readFile;

public class SqlFormatPersonRelation {
    public static void main(String[] args) {
        String name = "D:/huzhu.txt";
        List<String> list = readFile(name);
        String head = "insert into ZZ_FAMILY_PERSON_202010171849(id, p_id, f_id, relation, create_date, create_user) select sys_guid(), (select id from zz_person where card_num='612525197901025849'), (select f.id from ZZ_FAMILY f inner join zz_person p on f.p_id=p.id where p.card_num='";
        String tail = "'), '0', sysdate, 'system' from dual;";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String cardNum = list.get(i);
            if (StringUtils.isNotBlank(cardNum)) {
                int len = cardNum.length();
                if (len < 18) {
                    System.out.println("长度: " + len + " 身份证长度不符 " + cardNum);
                }
                sb.append(head + list.get(i) + tail + "\n");
            }
        }
        FileUtils.writeToFile("D:/sql_person.txt", sb.toString(), false);
    }
}
