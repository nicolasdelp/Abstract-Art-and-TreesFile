package com.delplanquerideau;

public class AVL {
    Node root;



    public AVL(){
    }

    public AVL(Node n){
        Node root = new Node(n);  //todo : create node constructor from node
    }


    public boolean isEmpty(){
        return (this.root = null);
    }


    public String preorder(Node n, String s){
        s = s + n.toString();
        if(!n.getIsALeaf()){
            s = s + "(" + preorder(n.getLeftNode(), s) + ")";
            s = s + "(" + preorder(n.getRightNode(), s) + ")";
        }
        return s;
    }

    public Node insert(Node n){
        insert_rec(root,n);

        public Node insert_rec(Node curr, Node toAdd){
            if(curr==null){
                return toAdd;	//we get the existing node (from the Kd Tree)
            }
            else if(toAdd.getWeight() <= curr.getWeight()){	//if equal, insert node as child, choice for next division is left to fate
                curr.setLeftNode(insert_rec(curr.getLeftNode(), toAdd));
            }
            else{
                curr.setRightNode(insert_rec(curr.getRightNode(), toAdd)) ;
            }

            updateHeight(n);
            rotate(n);
            return Node;









            // //override
            // public Node delete(Node toDel){
            // 	delete_rec(curr, root);
            // }

            // public Node delete_rec(Node curr, Node toDel){	//find method is useless, delete wants to have the parents of the node to delete
            // 	if(n==null) return null;
            // 	else if(toAdd.getWeight() < curr.getWeight()){	//if equal, delete ? how to know which to delete? is deletion necessary here?
            // 		curr.setLeftNode(delete_rec(curr.getLeftNode(), toAdd));
            // 	}
            // 	else{
            // 		curr.setRightNode(delete_rec(curr.getRightNode(), toAdd)) ;
            // 	}

            // }



            public Node getMax(){
                Node n = root;
                while(!n.isLeaf){
                    n = n.getRightNode;
                }
                return n;
            }

            public Node getMin(){
                Node n = root;
                while(!n.isLeaf){
                    n = n.getLeftNode;
                }
                return n;
            }


        }