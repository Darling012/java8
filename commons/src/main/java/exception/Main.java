package exception;

public class Main {
   public static void main(String[] args) {
       try {
           int[] data = new int[]{1,2,3};
           System.out.println(getDataByIndex(-1,data));
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

   }

   public static int getDataByIndex(int index,int[] data) {
       if(index<0||index>=data.length)
           throw new ArrayIndexOutOfBoundsException("数组下标越界");
       return data[index];
   }
}
