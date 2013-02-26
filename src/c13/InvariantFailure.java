package c13;

//: c13:InvariantFailure.java
//Indicates that the invariant test failed
public class InvariantFailure implements InvariantState {
public Object value;
public InvariantFailure(Object value) {
this.value = value;
}
} ///:~