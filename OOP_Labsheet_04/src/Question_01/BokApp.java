package Question_01;

public class BokApp {
    public static void main(String[] args) {

        Book b1 = new Book("Java Basics", "John");
        Book b2 = new Book("Python Guide", "Mike", "ISBN101");
        Book b3 = new Book("C++ Programming", "Anna", "ISBN202", 4500);

        System.out.println(b1.getBookDetails());
        System.out.println(b2.getBookDetails());
        System.out.println(b3.getBookDetails());

        System.out.println(b3.getBookDetails("ISBN202"));
    }


}
