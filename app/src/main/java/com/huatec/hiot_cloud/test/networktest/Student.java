package com.huatec.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 学生实体类
 */
class Student implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
   // private int age;

    /**
     * 婚姻状况
     */
 //   private boolean married;

    /**
     * 毕业情况
     *
     */
    private boolean graduated;

    /**
     * 身高
     *
     */
    private int height;

    /**
     * 编号
     *
     */
    private int id;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  //  public int getAge() {
  //      return age;
    //}

 //   public void setAge(int age) {
   //     this.age = age;
 //   }

 //   public boolean isMarried() {
 //       return married;
   // }

   // public void setMarried(boolean married) {
     //   this.married = married;
    //}


    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
