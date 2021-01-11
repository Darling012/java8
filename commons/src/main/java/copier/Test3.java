package copier;

public class Test3 {
    public static void main(String[] args) {
        int number=123;
        Address addr = new Address();
        addr.setAdd("A");
        Student stu1 = new Student();
        stu1.setNumber(number);
        stu1.setAddr(addr);
        Student stu2 = (Student)stu1.clone();
        stu2.setNumber(321);
        addr.setAdd("B");
        System.out.println(number);
        System.out.println(stu1 == stu2);
        System.out.println(stu1.getAddr()==stu2.getAddr());
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
    }
    static class Student implements Cloneable{
        private int number;
        private Address addr;
        public Address getAddr() {
            return addr;
        }
        public void setAddr(Address addr) {
            this.addr = addr;
        }
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
    static class Address {
        private String add;
        public String getAdd() {
            return add;
        }
        public void setAdd(String add) {
            this.add = add;
        }
    }
}
