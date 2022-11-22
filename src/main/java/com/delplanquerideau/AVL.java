package com.delplanquerideau;

public class AVL {
  Node root;
  
  
  
  public AVL(){
  }
  
  public AVL(Node n){
    Node root = new Node(n);  //todo : create node constructor from node
  }
  
  
  public boolean isVoid(){
    return (this.root = null);
  }
  
  
  public String preorder(Node n, String s){
    s = s + n.toString; 
    if(!n.isLeaf()){
        s = s + "(" + preorder(n.leftNode) + ")";
        s = s + "(" preorder(n.rightNode) + ")";
    } 
    return s;
  }
  
  public Node insert(Node n){
    if (root == null){
      
    }
    insert_rec(root,n);
  
  public Node insert_rec(Node curr, Node toAdd){
    if(curr == null)
    if(curr.getWeight() > n.getWeight){
      
      
    
