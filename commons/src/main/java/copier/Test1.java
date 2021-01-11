package copier;

public class Test1 {
    public static void main(String[] args) {
        int number=123;
        Student stu1 = new Student();
        stu1.setNumber(number);
        Student stu2 =stu1;
        stu2.setNumber(321);
        System.out.println(number);
        System.out.println(stu1 == stu2);
        System.out.println("学生1:" + stu1.getNumber());
        System.out.println("学生2:" + stu2.getNumber());
    }
    static class Student{
        private int number;
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number = number;
        }
    }
}
