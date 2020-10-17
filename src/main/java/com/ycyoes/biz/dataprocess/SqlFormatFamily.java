package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;
import com.ycyoes.utils.StringUtils;

import java.util.List;

import static com.ycyoes.demos.test.text.HandleText.readFile;

public class SqlFormatFamily {
    public static void main(String[] args) {
        String name = "D:/huzhu.txt";
        System.out.println(name);
        List<String> list = readFile(name);
        String head = "insert into ZZ_FAMILY_202010171648(id, p_id, create_date, create_user) select sys_guid(), (select id from zz_person where card_num='";
        String tail = "'),sysdate, 'system' from dual;";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String cardNum = list.get(i);
            if (StringUtils.isNotBlank(cardNum)) {
                int len = cardNum.length();
                if (len < 17) {
                    System.out.println("长度: " + len + " 身份证长度不符 " + cardNum);
                }
                sb.append(head + list.get(i) + tail + "\n");
            }
        }
        FileUtils.writeToFile("D:/sql.txt", sb.toString(), false);
    }
}
