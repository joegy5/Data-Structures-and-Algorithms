package com.company;

public class Main {

    public static void main(String[] args) {


        Node john = new Node("John");
        Node jane = new Node("Jane");
        Node joe = new Node("Joe");
        Node bob = new Node("Bob");

        SinglyLinkedListImplementation list = new SinglyLinkedListImplementation(john);
        list.addNodeToFront(jane);
        list.addNodeToBack(joe);
        list.addNode(bob, jane);

        list.printList();

        System.out.println();

        list.removeNode(jane);
        list.removeNode(joe);

        list.printList();
    }
}
