import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Precedence {

    private int get_index(char a){
        switch (a){
            case '+':{
                return 0;
            }
            case '*':{
                return 1;

            }
            case '(':{
                return 2;

            }
            case ')':{
                return 3;
            }
            case 'i':{
                return 4;
            }
            case '#':{
                return 5;
            }
            case 'N':{
                return 6;
            }
            default:{
                return 7;
            }
        }
    }
    int[][] table={{1,2,2,1,2},{1,1,2,1,2},{2,2,2,3,2},{1,1,0,1,0},{1,1,0,1,0},{2,2,2,0,2}};



    String[] standard={"N+N","N*N","(N)","i"};

    private boolean is_ok(String string){
        for (int i = 0; i < standard.length; i++) {
            if(standard[i].equals(string))
                return true;
        }
        return false;
    }
    private void execute(char[] input,char[] stack){
        int k=1,j;
        stack[k]='#';
        for (int i = 0; i < input.length; i++) {
            if(get_index(stack[k])>=0&&get_index(stack[k])<=5){
                j=k;
            }
            else {
                if (get_index(stack[k]) == 6){
                    j=k-1;
                }
                else {
                    System.out.println("E20");
                    return;
                }
            }
            System.out.println("I"+stack[j]);
            int judge=table[get_index(stack[j])][get_index(input[i])];
            while (judge==1){
                while (true){
                    char tmp=stack[j];
                    if(stack[j-1]>=0&&stack[j-1]<=5){
                        j=j-1;
                    }
                    else {
                        j=j-2;
                    }
                    if(table[get_index(stack[j])][get_index(tmp)]==2){
                        break;
                    }
                }
                String sentence="";
                for(int count=j+1;count<=k;count++){
                    sentence+=stack[count];
                }
                if(is_ok(sentence)){
                    System.out.println("R");
                    k=j+1;
                    stack[k]='N';
                }
                else {
                    System.out.println("RE");
                    return;
                }
                judge=table[get_index(stack[j])][get_index(input[i])];
            }
            if(judge==2||judge==3){
                k++;
                System.out.println("I"+input[i]);
                stack[k]=input[i];
            }
            if(judge==0){
                System.out.println("E");
                return;
            }
        }
        if(stack[k]=='#')
            return;
        else {
            System.out.println("E");
            return;
        }

    }
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length+10];
        reader.read(buf);
        reader.close();
        char buf2[]=new char[length+10];
        new Precedence().execute(buf,buf2);
    }
}
