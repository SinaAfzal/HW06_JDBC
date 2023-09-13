package entity;

public class Authors {
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private Books[] books;
    public Authors(Integer id, String firstName, String lastName, int age, Books[] books){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.books=books;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Books[] getBooks() {
        return books;
    }

    public void setBooks(Books[] books) {
        this.books = books;
    }

}
