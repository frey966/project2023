package com.example.shareSphere.test;

public class GenericMethod {
    public static void main(String[] args) {
        Double[] num = {1.11, 2.22, 3.33, 4.44, 5.55, 6.66};
        String[] str = {"Hello", "World", "你好", "世界"};

        Generic01 generic01 = new Generic01();
        generic01.toGeneric01(num);
        generic01.toGeneric01(str);


        Generic02<Double> doubleGeneric02 = new Generic02<>();
        doubleGeneric02.toGeneric02(num);
        Generic02<String> doubleGeneric03 = new Generic02<>();
        doubleGeneric03.toGeneric02(str);
    }
}

class Generic01 {
    public <T> T toGeneric01(T[] arr) {
        return arr[arr.length - 1];
    }

    public <T> T toGeneric011(Student student) {
        student.setAge(111);
        student.setName("test1");
        return (T) student;
    }

}

class Generic02<T> {
    public T toGeneric02(T[] arr) {
        return arr[arr.length - 1];
    }
}

class Generic03 {
    public <T> void toGeneric03(T[] arr) {
        T t = arr[arr.length - 1];
    }
}
