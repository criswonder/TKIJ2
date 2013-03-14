
package test.serializeable;

import java.io.Serializable;

public class Person implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5430556193897755112L;
    private String firstName;
    private String lastName;
    private int age;
    
    /**
     * Creates a new instance of Person
     */
    public Person() {
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
    
    //Overriding toString to be able to print out the object in a readable way
    //when it is later read from the file.
    public String toString() {
        
        StringBuffer buffer = new StringBuffer();
        buffer.append(firstName);
        buffer.append("\n");
        buffer.append(lastName);
        buffer.append("\n");
        buffer.append(age);
        buffer.append("\n");
        
        return buffer.toString();
    }
    
    
}