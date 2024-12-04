package org.example.springdatajpa2.mix;

import java.util.ArrayList;
import java.util.List;

public class OuterClass {
    private int length = 0;
    int id;
    private List<InnerClass> list;
    public OuterClass(){
        list = new ArrayList<InnerClass>();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<InnerClass> getList() {
        return list;
    }

    public void setList(List<InnerClass> list) {
        this.list = list;
    }

    class InnerClass {
        private int id;
        private String name;

        public InnerClass( String name) {
            this.id = ++OuterClass.this.length;
            this.name = name;
            OuterClass.this.list.add(this);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "InnerClass{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    static class StaticInnerClass {


    }
}
class Main{
    public static void main(String[] args) {
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
    }
}
