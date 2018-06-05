/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deber3listas;

/**
 *
 * @author Juandi
 * @param <E>
 */
public class DoublyLinkedList<E>{    
    
    private static class Node<E>{
      private E element;
      private Node<E> prev;
      private Node <E> next;
      public Node (E e, Node<E> p, Node<E> n){
          element = e;
          prev = p;
          next = n;      
      }
      public E getElement() {return element;}
      public Node<E> getPrev(){return prev;}
      public Node<E> getNext(){return next;}
      public void setPrev(Node <E> p){prev = p;}
      public void setNext(Node <E> n){next = n;}      
    }
    
    private Node<E> header; //header centinela
    private Node<E> trailer; //trailer centinela
    private int size = 0;
    
    public DoublyLinkedList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);    //vuelve circular la lista
    }
    
    public int size() {return size;}
    
    public boolean isEmpty(){return size == 0;}
    
    public E first(){
        if (isEmpty())return null;
        return header.getNext().getElement();
    }
    
    public E last(){
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();    
    }
    
    public void addBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }   
    
    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }
    
    public void addLast(E e){
        addBetween(e, trailer.getPrev(), trailer);
    }
    
    public E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }
    
    public E removeFirst(){
        if (isEmpty())return null;
        return remove(header.getNext());
    }
    
    public E removeLast(){
        if (isEmpty())return null;
        return remove(trailer.getPrev());
    }
    
    
    public void removeAllIter(E value){
        Node<E> current = this.header;
        Node<E> next;        
        if (current == null) return;       
        while (current != null){
            if (current.element == value){
                next = current.next;
                remove(current);
                current = next;
                }
            else
                current = current.next;
        }   
    }   
    
    public void removeAllRec(Node<E> e){
        Node<E> current = this.header;
        Node<E> next;        
        if (current == null) return;       
        while (current.next != null && current.element == current.next.element){
            current.next = current.next.next;}
        removeAllRec(e.next);
    }
    
    @Override
    public String toString(){
        String s = "";
        for (Node<E> p = header; p!= null; p = p.next){
            s += (p.element + " -> ");
        }
    return s + "";
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoublyLinkedList<Integer> linkedlist = new DoublyLinkedList<>();
        linkedlist.addFirst(2);
        linkedlist.addFirst(3);
        linkedlist.addFirst(1);
        linkedlist.addFirst(3);
        linkedlist.addFirst(2);
        
        System.out.println(linkedlist.toString());
        
        linkedlist.removeAllIter(3); //Prueba del método iterativo
        
        System.out.println(linkedlist.toString());
        
        
        
    }
    
}
