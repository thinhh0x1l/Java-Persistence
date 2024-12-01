package ex2;

import java.util.StringTokenizer;

public class User {
    private String firstName;
    private String lastName;
    public String getName(){
        return firstName + " " + lastName;
    }
    public void setName(String name){
        StringTokenizer tokenizer = new StringTokenizer(name, " ");
        firstName = tokenizer.nextToken();
        System.out.println(firstName);
        lastName = tokenizer.nextToken();
        System.out.println(lastName);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("John Smith lone");
        System.out.println(user.getName());
    }
}
