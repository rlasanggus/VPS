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



