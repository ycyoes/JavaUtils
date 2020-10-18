package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;
import com.ycyoes.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycyoes.demos.test.text.HandleText.readFile;

/**
 * 家庭成员SQL格式化
 */
public class MemberSqlFormat {
    public static void main(String[] args) {
        String name = "D:/member.csv";
        List<String> list = readFile(name);
        String head = "insert into ZZ_FAMILY_PERSON_202010181705(id, p_id, f_id, relation, create_date, create_user) select sys_guid(), (select id from zz_person where card_num='";
        String huzhu = "'), (select f.id from ZZ_FAMILY f inner join zz_person p on f.p_id=p.id where p.card_num='";
        String middle = "'), '";
        String tail = "', sysdate, 'system' from dual;";
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String[] memberInfo = list.get(i).split(",");
            String cardNum = memberInfo[0];
            String huzhuNum = memberInfo[1];
            String relation = memberInfo[2];

            if (StringUtils.isNotBlank(cardNum) && StringUtils.isNotBlank(relation)) {
                int len = cardNum.length();
                if (len < 18 || Integer.valueOf(relation) < 0) {
                    System.out.println("长度: " + len + " 身份证长度不符 " + cardNum);
                }
                map.put(cardNum, huzhuNum + "," + relation);
//                sb.append(head + cardNum + middle + relation + tail + "\n");
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            String val = entry.getValue().toString();
//            String[] vals = val.contains(",") ? val.split(",") : new String[]{};
            if (val.contains(",")) {
                String[] vals = val.split(",");
                sb.append(head + entry.getKey() + huzhu + vals[0] + middle + vals[1] + tail + "\n");

            }
//            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        System.out.println(sb.toString());
        FileUtils.writeToFile("D:/sql_person_relation1.txt", sb.toString(), false);
    }
}
