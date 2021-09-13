/*
    斗地主案例：
        1.准备牌
        2.洗牌
        3.发牌
        4.看牌
        5.开始斗地主——暂时不写
 */
package com.day12project.theProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DouDiZhu {
    public static void main(String[] args) {
        /*准备牌——54张
            1.1 牌由花色和点数组成
            1.2 将组合好的牌添加到集合中
            //创建一个集合作为牌盒——用于存储牌-->Map集合 Map<K,V>  K——牌的编号，用于洗牌时的排序； V——每一张牌
        */
        //创建一个Map集合，用作牌盒
        HashMap<Integer, String> map = new HashMap<>();
        //创建一个集合存储牌的编号——因为洗牌时要将牌打乱顺序(shuffle)和排序(sort)——Collections中的方法
        ArrayList<Integer> al = new ArrayList<>();
        //根据处理后List中牌的编号，去Map集合中找到对应编号的牌
        //牌由花色和点数组成——使用嵌套循环
        //定义花色
        String[] colors = {"♠", "♥", "♣", "♦"};
        //定义点数——斗地主中大牌总是在左边——便于后面的排序
        String[] nums = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        //单独将大/小王添加到集合中——牌盒、编号

        //定义一个编号
        int index = 0;
        //将大王当作Map集合中的第一个元素——大王最大
        map.put(index,"大王");
        //将大王的编号添加到编号集合中
        al.add(index);
        //编号自增
        index++;

        //小王同理
        map.put(index,"小王");
        al.add(index);
        //编号自增
        index++;

        //将花色和点数进行组合并添加到牌盒、编号集合中——使用嵌套循环
        //内外层循环的区别：原因——点数比花色大：如方块2比黑桃3大
            //外层花色，内层点数：如：黑桃2到黑桃3……
            //外层点数，内层花色：如：黑桃2到方块2……
        for(String num:nums){
            for(String color:colors){
                //存入牌盒
                map.put(index,color+num);
                //遁入编号集合
                al.add(index);
                //编号自增
                index++;
            }
        }
        System.out.println(al);
        System.out.println(map);

        //洗牌——使用Collections中的shuffle方法打乱牌的编号
        Collections.shuffle(al);
        System.out.println(al);

        //发牌——斗地主有3个玩家，每个玩家17张，另包含3张底牌——把编号发给对应的人
            //1.先预留3张地主牌，然后依次轮流发牌

        //创建3个玩家集合，1个地主牌集合
        ArrayList<Integer> player1= new ArrayList<>();
        ArrayList<Integer> player2= new ArrayList<>();
        ArrayList<Integer> player3= new ArrayList<>();
        ArrayList<Integer> dizhu= new ArrayList<>();

        //遍历编号集合，分别发牌
        for (int i = 0; i < al.size(); i++) {    //此时i是索引，而不是牌的编号
            if(i>50){
                dizhu.add(al.get(i));//此时的al是经过shuffle处理打乱的集合，是牌的编号
            }else if(i%3==0){
                player1.add(al.get(i));
            }else if(i%3==1){
                player2.add(al.get(i));
            }else if(i%3==2){
                player3.add(al.get(i));
            }
        }


        //看牌——调用方法
        lookPoker("何同学",player1,map);
        lookPoker("潘同学",player2,map);
        lookPoker("谢同学",player3,map);
        lookPoker("地主牌",dizhu,map);
    }

    //参数列表：名字，玩家分配到的牌号，牌号对应的牌面
    public static void lookPoker(String name,ArrayList<Integer> al,HashMap<Integer,String> map){
        Collections.sort(al);
        System.out.print(name+"的牌是：");
        for (Integer pokerNum : al) {//遍历牌号
            String poker=map.get(pokerNum);//通过key得到相对应的value值
            System.out.print(poker+" ");
        }
        System.out.println("");
    }
}