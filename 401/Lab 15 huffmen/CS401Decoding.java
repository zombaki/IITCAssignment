public class CS401Decoding {
	public Node root;

    public CS401Decoding(){
        this.root = new Node();
    }

    public void add(char data, String sequence){

        Node temp = this.root;
        int i = 0;
        for(i=0;i<sequence.length()-1;i++){

          if(sequence.charAt(i)=='0'){
                if(temp.left == null){
                    temp.left = new Node();
                    temp = temp.left;
                }
                else{
                   temp = (Node) temp.left;
                }
            }
            else
              if(sequence.charAt(i)=='1'){
                if(temp.right == null){
                    temp.right = new Node();
                    temp = temp.right;
                }
                else{
                    temp = (Node) temp.right;
                }
         }}

        if(sequence.charAt(i)=='0'){

            temp.left = new Node(data);
           }
        else{
            temp.right = new Node(data);

        }
        }

    public String getDecodedMessage(String encoding){

        String output = "";
        Node temp = this.root;
        for(int i = 0;i<encoding.length();i++){

            if(encoding.charAt(i) == '0'){
                temp = temp.left;

                if(temp.left == null && temp.right == null){
                    output+= temp.getData();
                    //System.out.println(temp);
                    temp = this.root;
                }
            }
            else
            {
                temp = temp.right;
                if(temp.left == null && temp.right == null){
                    output+= temp.getData();
                    //System.out.println(temp);
                    temp = this.root;
                }

            }
        }
        return output;
    }
  
}
class Node{

    Node left;
    Node right;
    char data;

    public Node(){

    }
    public Node(char data){
        this.data = data;
    }
    public void setData(char data){
        this.data = data;
    }
    public char getData(){
        return this.data;
    }
    @Override
    public String toString(){
       if(this.data == Character.UNASSIGNED){
           return "No Value";
       }
       else
           return ""+this.data;
    }
}
