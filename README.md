# VPS
괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다.  
[백준 온라인저지 코딩사이트 문제](https://www.acmicpc.net/problem/9012) 를 푼 후 이를 참고하여 업그레이드 해본 코드입니다.  
* 추가기능
  * 단순 괄호() 이외에 [], {}에 대한 확인 추가  
  * 코드 상에서 찾아낼 수 있는 기능 추가  
  * 코드를 커맨드창과 파일로 받아올수 있도록 추가  
  * 에러위치, 에러 개수 출력  
   
* 메소드 및 기능
  * static void Dofile() : 코드를 파일로부터 읽어옴  
  * static void Docmd() : 코드를 커맨드 창으로부터 읽어옴  
  * static void check(String input[], int num) : 각 괄호에 대해 확인  
  * static void checkdum(char type1, char type2, String input[], int num) : 각 괄호에 대한 에러위치와 수 확인  
  * static void disp(char type1, char type2, int test, int[] index, int indexnum, int[] indexp, int indexpnum) : 에러위치, 수 표시  
  
* 문제점 및 보완점
  * 사용자를 위해 GUI 제공
  * ;이 있는곳을 코드의 한줄로 인식하도록 기능 추가
  * ()의 경우 코드의 한줄기준으로 열고 닫힘. 한줄에 대한 확인 필요  
  * +a

## <pre>VPS</pre> 
> [VPS.java](https://github.com/rlasanggus/VPS/tree/master/src)  
>> [public static void main(String[] args) throws IOException](https://github.com/rlasanggus/VPS#public-static-void-mainstring-args-throws-ioexception)  
>> [static void Dofile()](https://github.com/rlasanggus/VPS#static-void-dofile)  
>> [static void Docmd()](https://github.com/rlasanggus/VPS#static-void-docmd)  
>> [static void check(String input[], int num)](https://github.com/rlasanggus/VPS#static-void-checkstring-input-int-num)  
>> [static void checkdum(char type1, char type2, String input[], int num)](https://github.com/rlasanggus/VPS#static-void-checkdumchar-type1-char-type2-string-input-int-num)  
>> [static void disp(char type1, char type2, int test, int[] index, int indexnum, int[] indexp, int indexpnum)](https://github.com/rlasanggus/VPS#static-void-dispchar-type1-char-type2-int-test-int-index-int-indexnum-int-indexp-int-indexpnum)   


```java
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
```  
Scanner 사용을 위한 util 패키지의 Scanner class 참조  
파일 입출력을 위한 io 패키지의 모든 class 참조  
입력시 예외처리를 위한 util패키지의 InputMismatchException 참조  
#### <code>public static void main(String[] args) throws IOException</code>  
```java
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
```  
커맨드 창으로부터 코드를 입력받을 형태 입력받음  
잘못된 입력의 경우 예외처리  
정확한 입력이 될때까지 무한루프  

#### <code>static void Dofile()</code>  
```java
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
``` 
파일에서 한줄 단위로 코드르 읽어옴  
에러가 나면 e 를 출력후 종료  
파일을 끝까지 읽었으면 read 객체 반환  
```java
for(String a : input){
	if(a == null)
		break;
	System.out.println(codnum + "	" + a);
	codnum ++;
}
```  
읽어들인 코드를 출력(없어도 되는부분)  
```java
	check(input, num);
	
	if(scan != null)
		scan.close();
}
```  
읽은 코드를 check
>[check](https://github.com/rlasanggus/VPS#static-void-checkstring-input-int-num)

#### <code>static void Docmd()</code>  
```java
for(int i = 0; i<codnum; i++){
			
			System.out.print(i+1 + "	");
			input[i] = scan.next();
			if(input[i].equals("여기까지"))
				break;
			num++;
		}
```  
종료조건을 만족할때까지 입력받음  
```java
check(input, num);
	if(scan != null)
	scan.close();
}
```  
읽은 코드를 check
>[check](https://github.com/rlasanggus/VPS#static-void-checkstring-input-int-num)
#### <code>static void check(String input[], int num)</code>  
```java
	static void check(String input[], int num){
		checkdum('(', ')', input, num);
		checkdum('{', '}', input, num);
		checkdum('[', ']', input, num);
	}
```  
각 괄호에 해 확인  
>[checkdum](https://github.com/rlasanggus/VPS#static-void-checkdumchar-type1-char-type2-string-input-int-num)  
#### <code>static void checkdum(char type1, char type2, String input[], int num)</code>  
```java
for(int r=0; r<num; r++){
			for(int i=0; i<input[r].length(); i++){
				
				if(input[r].charAt(i) == type1){
					test++;
					if(indexpnum>=0){
						indexp[indexpnum] = r;
						indexpnum ++;
					}
				}
```
input 배열에는 코드들이 한줄 단위로 저장되어 있음.  
input[r].charAt(i)는 r번째 코드줄의 i번째 인덱스 위치의 char값. 이 값을 type1( ex:[ )와 비교  
같다면 indexp[]배열에 해당 코드줄의 번호를 입력  
indexpnum ++  
```java
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
```  
input 배열에는 코드들이 한줄 단위로 저장  
input[r].char(i)는 r번째 코드줄의 i번째 인덱스 위치의 char값. 이 값을 type2( ex:] )와 비교  
같다면 indexpnum을 감소시키고, indexp[] 에 저장되어있는값을 초기화( 괄호가 닫혔으니 열린곳의 위치는 의미가 없어짐)  
test 값이 0보다 작아진다면, 열린 횟수보다 닫힌 횟수가 많아졌다는것을 의미.  이는 무조껀적인 에러위치가됨  
index[]배열에 그 위치를 저장  
pretest에 test값을 저장. 만약 또다시 test<0 이면서 test<pretest 이면, 닫히는 괄호가 많은 상태에서 또다시 괄호가 열림. 무조껀적인 에러  

#### <code>static void disp(char type1, char type2, int test, int[] index, int indexnum, int[] indexp, int indexpnum)</code>  
```java
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
```  
test == 0 이라면 열린 개수와 닫힌 개수가 같고 열리지 않았는데 닫힌 괄호 또한 없음. 정상  
test > 0 이라면 열린 개수가 닫힌 개수보다 많음. 열리는 괄호는 indexp[]에 위치가 저장되어있음. 에러위치 출력  
test < 0 이라면 열린 개수가 닫힌 개수보다 적음. 비정상적인 닫히는 괄호는 index[]에 위치가 저장되어 있음. 에러위치 출력  
