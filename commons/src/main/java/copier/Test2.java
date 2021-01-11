package copier;

public class Test2 {
    public static void main(String[] args) {
        int number=123;
        Student stu1 = new Student();
        stu1.setNumber(number);
        Student stu2 = (Student)stu1.clone();
        stu2.setNumber(321);
        System.out.println(number);
        System.out.println(stu1 == stu2);
        System.out.println("学生1:" + stu1.getNumber());
        System.out.println("学生2:" + stu2.getNumber());
    }
    static class Student implements Cloneable{
        private int number;
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number = number;
        }
        @Override
        public Object clone() {
            Student stu = null;
            try{
                stu = (Student)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return stu;
        }
    }
}
