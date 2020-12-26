import java.util.Scanner;

public class Digit{
	// 변수 선언 및 초기화
	static Scanner scan= new Scanner(System.in); // 스캐너 객체 생성
	static int M[] = new int[1000];
	static int R[] = new int[10];
	static int count;
	int c=0;
	
	public static void main(String[] args) {
		Digit digit = new Digit();
		digit.input(); // input() 함수 실행
		System.out.println("=======================");
		digit.interpret(M, R); // interpret() 함수 실행
		digit.print(R, count); // print() 함수 실행
		
		scan.close(); // 스캐너 종료
	}
	void input(){
		 for(int i = 0; i < 1000; i++){
			 int input_M = scan.nextInt(); // 입력 받기
			 if(Integer.toString(input_M).equals("-1")) // -1을 입력했을 경우, 입력 받는 거 중단.
				 break;
			 c++; // 메모리 출력의 index 범위 확인을 위한 증가
			 M[i] = input_M % 1000;  // 입력받아서 메모리 M에 저장
		 }
	}
	int interpret(int[] M, int[] R){
		 int PC = 0; // 변수 선언
		 int opcode, operand1, operand2;
		 
		 for(count = 1; M[PC] != 100; count++){ // M[PC]가 100이면, 종료
			 opcode = M[PC] / 100; //opcode 100의 자릿수
			 operand1 =  (M[PC] - opcode*100) / 10; // 10의 자릿수
			 operand2 = M[PC] - opcode*100 - operand1*10; // 1의 자릿수
			 PC++; // PC 증가
			 
			 switch(opcode){ // % 1000을 한 이유는, 1000이 넘어가는 경우 1000으로 나눈 후의 나머지 값을 할당하기 위해
		  		case 2: // 2dn의 경우
		  			R[operand1] = operand2 % 1000;
		  			break;
		  		case 3: // 3dn의 경우
		  			R[operand1] = (R[operand1] + operand2) % 1000;
		  			break;
		  		case 4: // 4dn의 경우
		  			R[operand1] = (R[operand1] * operand2) % 1000;
		  			break;
		  		case 5: // 5ds의 경우
		  			R[operand1] = R[operand2] % 1000;
		  			break;
		  		case 6: // 6ds의 경우
		  			R[operand1] = (R[operand1] + R[operand2]) % 1000;
		  			break;
		  		case 7: // 7ds의 경우
		  			R[operand1] = (R[operand1] * R[operand2]) % 1000;
		  			break;
		  		case 8: // 8da의 경우
		  			R[operand1] = M[R[operand2] ] % 1000;
		  			break; 
		  		case 9: // 9sa의 경우
		  			M[R[operand2]] = R[operand1];
		  			if (M[R[operand2]] >= 1000)
		  				M[R[operand2]] = M[R[operand2]] % 1000;
		  			if (R[operand2] > c) // R[operand2] > c인 경우, M 출력을 위해 c 값을 변경해준다.
		  				c = R[operand2] + 1;
		  			break;
		  		case 0: // 2dn의 경우
		  			if(R[operand2] != 0)
		  				PC = R[operand1];
		  			break;
		  		default: //잘못입력된 경우, 무시하거나 다른 실행.
		  			break;
		  		}
			 }
		 return count; // 명령어 실행 횟수를 위한 count 반환
	}
	void print(int[] R, int count){
		System.out.println("명령어 실행 횟수: " + count); // 명령어 실행 횟수 출력
		System.out.println("[레지스터 값 출력]"); // 레스터 값 출력
		for(int i=0; i<10; i++)
			System.out.println("R[" + i + "]  =  " + R[i]);
		System.out.println("[메모리 값 출력]"); // 메모리 값 출력
		for(int i=0; i<c; i++)
			System.out.println("M[" + i + "]  =  " + M[i]);
	}
}
