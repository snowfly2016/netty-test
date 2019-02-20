package jdk8.ch04;

public class AutoCloseAbleTest implements AutoCloseable {

    public void doSomeThing(){
        System.out.println("do something invoked");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close invoked");
    }

    public static void main(String[] args) throws Exception{
        try(AutoCloseAbleTest autoCloseAbleTest = new AutoCloseAbleTest()) {
            autoCloseAbleTest.doSomeThing();
        }
    }
}
