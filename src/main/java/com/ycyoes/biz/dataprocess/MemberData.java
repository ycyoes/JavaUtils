package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;
import com.ycyoes.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycyoes.demos.test.text.HandleText.readFile;

public class MemberData {

    public static void main(String[] args) {
        Map<String, Integer> relationMap = new HashMap();
        relationMap.put("四女",35);
        relationMap.put("户主",0);
        relationMap.put("小集体户主",3);
        relationMap.put("配偶",10);
        relationMap.put("夫",11);
        relationMap.put("妻",12);
        relationMap.put("子",20);
        relationMap.put("独生子",21);
        relationMap.put("长子",22);
        relationMap.put("次子",23);
        relationMap.put("三子",24);
        relationMap.put("四子",25);
        relationMap.put("五子",26);
        relationMap.put("养子或继子",27);
        relationMap.put("女婿",28);
        relationMap.put("其他儿子",29);
        relationMap.put("女",30);
        relationMap.put("独生女",31);
        relationMap.put("长女",32);
        relationMap.put("二女",33);
        relationMap.put("三女",34);
        relationMap.put("五女",36);
        relationMap.put("养女",37);
        relationMap.put("儿媳",38);
        relationMap.put("其他女儿",39);
        relationMap.put("孙(外孙)子(女)",40);
        relationMap.put("孙子",41);
        relationMap.put("孙女",42);
        relationMap.put("外孙子",43);
        relationMap.put("外孙女",44);
        relationMap.put("孙(外孙)媳妇",45);
        relationMap.put("孙(外孙)女婿",46);
        relationMap.put("曾(外)孙子",47);
        relationMap.put("曾(外)孙女",48);
        relationMap.put("其他(外)孙子(女)",49);
        relationMap.put("父母",50);
        relationMap.put("父亲",51);
        relationMap.put("母亲",52);
        relationMap.put("公公",53);
        relationMap.put("婆婆",54);
        relationMap.put("岳父",55);
        relationMap.put("岳母",56);
        relationMap.put("继父或养父",57);
        relationMap.put("继母或养母",58);
        relationMap.put("其他父母关系",59);
        relationMap.put("祖(外祖)父母",60);
        relationMap.put("祖父",61);
        relationMap.put("祖母",62);
        relationMap.put("外祖父",63);
        relationMap.put("外祖母",64);
        relationMap.put("配偶的祖(外祖)父母",65);
        relationMap.put("曾祖父",66);
        relationMap.put("曾祖母",67);
        relationMap.put("配偶的曾祖父母",68);
        relationMap.put("其他祖(外祖)父母",69);
        relationMap.put("兄弟姐妹",70);
        relationMap.put("兄",71);
        relationMap.put("嫂",72);
        relationMap.put("弟",73);
        relationMap.put("弟媳",74);
        relationMap.put("姐姐",75);
        relationMap.put("姐夫",76);
        relationMap.put("妹妹",77);
        relationMap.put("妹夫",78);
        relationMap.put("其他兄弟姐妹",79);
        relationMap.put("伯父",81);
        relationMap.put("伯母",82);
        relationMap.put("叔父",83);
        relationMap.put("婶母",84);
        relationMap.put("舅父",85);
        relationMap.put("舅母",86);
        relationMap.put("姨父",87);
        relationMap.put("姨母",88);
        relationMap.put("姑父",89);
        relationMap.put("姑母",90);
        relationMap.put("堂兄弟(姐妹)",91);
        relationMap.put("表兄弟(姐妹)",92);
        relationMap.put("侄子",93);
        relationMap.put("侄女",94);
        relationMap.put("外甥",95);
        relationMap.put("外甥女",96);
        relationMap.put("其他亲属",97);
        relationMap.put("保姆",98);
        relationMap.put("非亲属",99);

        //String name = System.getProperty("user.dir") + "\\src\\main\\java\\com\\ycyoes\\demos\\test\\text\\";
        String name = "D:/fengdong.csv";
        System.out.println(name);
        List<String> list = readFile(name);
        System.out.println("总长度: " + list.size());
//        list.stream().forEach(System.out::println);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
            String personInfo = list.get(i).trim().replaceAll(" ", "");
            if (StringUtils.isNotBlank(personInfo)) {
                String[] personInfos = list.get(i).split(",");
                if(personInfos.length < 5) {
//                    System.out.println("length: " + personInfos.length + " " + list.get(i));
                    System.out.println("i: " + i + " " + personInfos[0] + " " + personInfos[2] + " 信息不全！");
                } else {
//                    System.out.println("length: " + personInfos.length + " i: " + i + " 2:" + personInfos[3].trim());
//                    System.out.println(list.get(i));
                    Integer relationVal = relationMap.get(personInfos[4].trim());
                    if(relationVal == null) {
                        System.out.println("i: " + i + " " + personInfos[0] + " " + personInfos[1] + " 未匹配到值！");
                    } else if(relationVal != 0) {
                        String cardNum = personInfos[0].trim();
                        if (cardNum.length() < 18) {
//                            System.out.println("l: " + i + " 身份证号不符合规范 " + cardNum + " len: " + cardNum.length());
//                            System.out.println(personInfos[0].trim() + "," + personInfos[1].trim() + "," + personInfos[2].trim() + "," + personInfos[3].trim() + "," + personInfos[4].trim() + "," + relationVal);
                        }
//                        sb.append(personInfos[0].trim() + "," + personInfos[1].trim() + "," + personInfos[2].trim() + "," + personInfos[3].trim() + "," + personInfos[4].trim() + "," + relationVal + "\n");
                        sb.append(personInfos[0].trim() + "," + personInfos[2].trim() + "," + relationVal + "\n");
                    }

//                    System.out.println(relationVal);
                }

            }

        }
                    System.out.println("personInfo: " + sb.toString());
                FileUtils.writeToFile("D:/member.csv", sb.toString(), false);

    }
}
