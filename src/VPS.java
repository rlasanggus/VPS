import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
//예외처리추가, 닫히는 위치 에러위치발견 추가, 열리는 위치 에러위치발견 추가
public class VPS {
	public static void main(String[] args) throws IOException{
		
		System.out.println("확인하고자 하는 코드의 입력 형태를 입력하세요");
		int go = 0;
		Scanner scan;
		for(;;){
			scan = new Scanner(System.in);
			System.out.println("1.커맨드창		2.파일");
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
				System.out.println("숫자가 아닙니다.");
				continue;
			}
		}
		if(scan != null)
			scan.close();
	}

//파일에서 읽어오기
	static void Dofile() throws IOException{
		System.out.println("현재 실행파일의 경로에 'codtest.txt' 형태로 소스코드를 저장해주세요.");
		System.out.println("소스코드의 대략적인 코드 줄 수를 입력해 주세요");
		
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
				System.out.println("숫자가 아닙니다.");
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

//커맨드창에서 입력
	static void Docmd(){
	
		int codnum;
		Scanner scan;
		System.out.println("코드파일의 최대 줄수를 입력해주세요");
		
		for(;;){
		try{
				scan = new Scanner(System.in);
				codnum = scan.nextInt();
				break;
			}catch(InputMismatchException e){
				System.out.println("숫자가 아닙니다.");
				continue;
			}
		}
		
		String[] input = new String[codnum];
		
		int num = 0;
		System.out.println("코드를 입력해 주세요. 입력을 멈추고싶으면 '여기까지'를 입력하세요");
		
		for(int i = 0; i<codnum; i++){
			
			System.out.print(i+1 + "	");
			input[i] = scan.next();
			if(input[i].equals("여기까지"))
				break;
			num++;
		}
		
		check(input, num);
		if(scan != null)
			scan.close();
	}

//확인
	static void check(String input[], int num){
		checkdum('(', ')', input, num);
		checkdum('{', '}', input, num);
		checkdum('[', ']', input, num);
	}
	
//에러위치, 에러수 		
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
	
//에러위치, 수 표시	
	static void disp(char type1, char type2, int test, int[] index, int indexnum, int[] indexp, int indexpnum){
		if(test == 0)
			System.out.println(type1 + "" + type2 + " 정상");
		else{
			if(test>0){
				System.out.print(type1 + "가  " + test + "개 많습니다 ");
				for(int i=0; i<indexpnum; i++){
					System.out.print(indexp[i]+1 + "/");
				}
			}
			else{
				System.out.print(type2 + "가  " + -test + "개 많습니다 ");
			for(int i=0; i<indexnum; i++){
				System.out.print(index[i]+1 + "/");
			}
			}
			System.out.println("번째 줄을 확인하세요.");
		}
	}
	
}
