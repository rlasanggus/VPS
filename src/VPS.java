import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
//����ó���߰�, ������ ��ġ ������ġ�߰� �߰�, ������ ��ġ ������ġ�߰� �߰�
public class VPS {
	public static void main(String[] args) throws IOException{
		
		System.out.println("Ȯ���ϰ��� �ϴ� �ڵ��� �Է� ���¸� �Է��ϼ���");
		int go = 0;
		Scanner scan;
		for(;;){
			scan = new Scanner(System.in);
			System.out.println("1.Ŀ�ǵ�â		2.����");
			try{
				go = scan.nextInt();
				if(go == 1){
					Docmd();
					break;
				}
				else if(go == 2){
					Dofile();
					break;
				}
				else{
					System.out.println("error");	
				}
			}catch(InputMismatchException e){
				System.out.println("���ڰ� �ƴմϴ�.");
				continue;
			}
		}
		if(scan != null)
			scan.close();
	}

//���Ͽ��� �о����
	static void Dofile() throws IOException{
		System.out.println("���� ���������� ��ο� 'codtest.txt' ���·� �ҽ��ڵ带 �������ּ���.");
		System.out.println("�ҽ��ڵ��� �뷫���� �ڵ� �� ���� �Է��� �ּ���");
		
		int codnum;
		int num = 0;
		String[] input;
		Scanner scan;
		
		for(;;){
			try{
				scan = new Scanner(System.in);
				codnum = scan.nextInt();
				break;
			}catch(InputMismatchException e){
				System.out.println("���ڰ� �ƴմϴ�.");
				continue;
			}
		}
		
		input = new String[codnum + 50];
		BufferedReader read = null;
		
		try{
			read = new BufferedReader(new FileReader("codtest.txt"));
			while((input[num] = read.readLine()) != null){
				num++;
			}
		}catch(IOException e){
			System.out.println(e);
			System.exit(1);
		}finally{
			if(read != null)
				read.close();
		}
		
		codnum = 1;
		for(String a : input){
			if(a == null)
				break;
			System.out.println(codnum + "	" + a);
			codnum ++;
		}
		check(input, num);
		
		if(scan != null)
			scan.close();
	}

//Ŀ�ǵ�â���� �Է�
	static void Docmd(){
	
		int codnum;
		Scanner scan;
		System.out.println("�ڵ������� �ִ� �ټ��� �Է����ּ���");
		
		for(;;){
		try{
				scan = new Scanner(System.in);
				codnum = scan.nextInt();
				break;
			}catch(InputMismatchException e){
				System.out.println("���ڰ� �ƴմϴ�.");
				continue;
			}
		}
		
		String[] input = new String[codnum];
		
		int num = 0;
		System.out.println("�ڵ带 �Է��� �ּ���. �Է��� ���߰������ '�������'�� �Է��ϼ���");
		
		for(int i = 0; i<codnum; i++){
			
			System.out.print(i+1 + "	");
			input[i] = scan.next();
			if(input[i].equals("�������"))
				break;
			num++;
		}
		
		check(input, num);
		if(scan != null)
			scan.close();
	}

//Ȯ��
	static void check(String input[], int num){
		checkdum('(', ')', input, num);
		checkdum('{', '}', input, num);
		checkdum('[', ']', input, num);
	}
	
//������ġ, ������ 		
	static void checkdum(char type1, char type2, String input[], int num){
		int test = 0;
		int[] index = new int[num];
		int[] indexp = new int[num];
		int indexnum = 0;
		int pretest = 0;
		int indexpnum = 0;
		
		for(int r=0; r<num; r++){
			for(int i=0; i<input[r].length(); i++){
				
				if(input[r].charAt(i) == type1){
					test++;
					if(indexpnum>=0){
						indexp[indexpnum] = r;
						indexpnum ++;
					}
				}
				
				if(input[r].charAt(i) == type2){
					test--;
					if(indexpnum>0){
						indexpnum--;
						indexp[indexpnum] = 0;
					}
					if(test < 0 && test < pretest){
						index[indexnum] = r;
						indexnum ++;
						pretest = test;
					}
				}
			}
		}
		

		disp(type1, type2, test, index, indexnum, indexp, indexpnum);
	}
	
//������ġ, �� ǥ��	
	static void disp(char type1, char type2, int test, int[] index, int indexnum, int[] indexp, int indexpnum){
		if(test == 0)
			System.out.println(type1 + "" + type2 + " ����");
		else{
			if(test>0){
				System.out.print(type1 + "��  " + test + "�� �����ϴ� ");
				for(int i=0; i<indexpnum; i++){
					System.out.print(indexp[i]+1 + "/");
				}
			}
			else{
				System.out.print(type2 + "��  " + -test + "�� �����ϴ� ");
			for(int i=0; i<indexnum; i++){
				System.out.print(index[i]+1 + "/");
			}
			}
			System.out.println("��° ���� Ȯ���ϼ���.");
		}
	}
	
}
