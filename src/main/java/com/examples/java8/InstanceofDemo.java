package com.examples.java8;

class A {}
class B extends A {}
class C extends B {}
class D extends A {}
 
public class InstanceofDemo
{
  public static void main(String args[])
  {
    A obj = new B();
 
    System.out.println("a instanceof A: " + (obj instanceof B)); //true
    System.out.println("b instanceof A: " + (obj instanceof D)); //true
    System.out.println("c instanceof A: " + (obj instanceof C)); //true
  }
}
