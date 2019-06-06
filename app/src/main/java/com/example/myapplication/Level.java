package com.example.myapplication;

public class Level {

    //关卡数
    private int number;
    //是否已闯关成功
    private boolean isMarked=false;

    public Level(int number1,boolean isMarked1){
        number = number1;
        isMarked = isMarked1;
    }


    public void setIsMarked(){

        isMarked = true;
    }

    public boolean getIsMarked(){
        return isMarked;
    }

    public int getNumber(){
        return number;
    }

}
