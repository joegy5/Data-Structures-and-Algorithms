package com.company;

public class Node {

    private String name;
    private Node next;

    public Node(String name, Node next) {
        this.name = name;
        this.next = next;
    }

    public Node(String name) {
        this.name = name;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


