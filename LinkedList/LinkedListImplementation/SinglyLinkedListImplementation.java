package com.company;

public class SinglyLinkedListImplementation {

    private Node head;

    public SinglyLinkedListImplementation(Node head) {
        this.head = head;
    }

    public void addNodeToFront(Node node) {
        node.setNext(head);
        this.head = node;
    }

    public void addNodeToBack(Node node) {
        Node temp = head;
        while(temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(node);
    }

    public void addNode(Node node, Node previousNode) {
        Node temp = head;
        while(temp.getNext() != null && temp != previousNode) {
            temp = temp.getNext();
        }
        if(temp == previousNode) {
            node.setNext(previousNode.getNext());
            temp.setNext(node);
        }
    }

    public void removeNode(Node node) {
        Node temp = head;
        if(temp == node) {
            head = node.getNext();
            temp.setNext(null);
            return;
        }

        while (temp.getNext() != null && temp.getNext() != node) {
            temp = temp.getNext();
        }

        temp.setNext(node.getNext());
        node.setNext(null);
    }

    public void printList() {
        Node temp = head;
        while(temp != null) {
            System.out.println(temp.getName());
            temp = temp.getNext();
        }
    }
}
