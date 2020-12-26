import java.util.Scanner;

public class Digit{
	// ���� ���� �� �ʱ�ȭ
	static Scanner scan= new Scanner(System.in); // ��ĳ�� ��ü ����
	static int M[] = new int[1000];
	static int R[] = new int[10];
	static int count;
	int c=0;
	
	public static void main(String[] args) {
		Digit digit = new Digit();
		digit.input(); // input() �Լ� ����
		System.out.println("=======================");
		digit.interpret(M, R); // interpret() �Լ� ����
		digit.print(R, count); // print() �Լ� ����
		
		scan.close(); // ��ĳ�� ����
	}
	void input(){
		 for(int i = 0; i < 1000; i++){
			 int input_M = scan.nextInt(); // �Է� �ޱ�
			 if(Integer.toString(input_M).equals("-1")) // -1�� �Է����� ���, �Է� �޴� �� �ߴ�.
				 break;
			 c++; // �޸� ����� index ���� Ȯ���� ���� ����
			 M[i] = input_M % 1000;  // �Է¹޾Ƽ� �޸� M�� ����
		 }
	}
	int interpret(int[] M, int[] R){
		 int PC = 0; // ���� ����
		 int opcode, operand1, operand2;
		 
		 for(count = 1; M[PC] != 100; count++){ // M[PC]�� 100�̸�, ����
			 opcode = M[PC] / 100; //opcode 100�� �ڸ���
			 operand1 =  (M[PC] - opcode*100) / 10; // 10�� �ڸ���
			 operand2 = M[PC] - opcode*100 - operand1*10; // 1�� �ڸ���
			 PC++; // PC ����
			 
			 switch(opcode){ // % 1000�� �� ������, 1000�� �Ѿ�� ��� 1000���� ���� ���� ������ ���� �Ҵ��ϱ� ����
		  		case 2: // 2dn�� ���
		  			R[operand1] = operand2 % 1000;
		  			break;
		  		case 3: // 3dn�� ���
		  			R[operand1] = (R[operand1] + operand2) % 1000;
		  			break;
		  		case 4: // 4dn�� ���
		  			R[operand1] = (R[operand1] * operand2) % 1000;
		  			break;
		  		case 5: // 5ds�� ���
		  			R[operand1] = R[operand2] % 1000;
		  			break;
		  		case 6: // 6ds�� ���
		  			R[operand1] = (R[operand1] + R[operand2]) % 1000;
		  			break;
		  		case 7: // 7ds�� ���
		  			R[operand1] = (R[operand1] * R[operand2]) % 1000;
		  			break;
		  		case 8: // 8da�� ���
		  			R[operand1] = M[R[operand2] ] % 1000;
		  			break; 
		  		case 9: // 9sa�� ���
		  			M[R[operand2]] = R[operand1];
		  			if (M[R[operand2]] >= 1000)
		  				M[R[operand2]] = M[R[operand2]] % 1000;
		  			if (R[operand2] > c) // R[operand2] > c�� ���, M ����� ���� c ���� �������ش�.
		  				c = R[operand2] + 1;
		  			break;
		  		case 0: // 2dn�� ���
		  			if(R[operand2] != 0)
		  				PC = R[operand1];
		  			break;
		  		default: //�߸��Էµ� ���, �����ϰų� �ٸ� ����.
		  			break;
		  		}
			 }
		 return count; // ��ɾ� ���� Ƚ���� ���� count ��ȯ
	}
	void print(int[] R, int count){
		System.out.println("��ɾ� ���� Ƚ��: " + count); // ��ɾ� ���� Ƚ�� ���
		System.out.println("[�������� �� ���]"); // ������ �� ���
		for(int i=0; i<10; i++)
			System.out.println("R[" + i + "]  =  " + R[i]);
		System.out.println("[�޸� �� ���]"); // �޸� �� ���
		for(int i=0; i<c; i++)
			System.out.println("M[" + i + "]  =  " + M[i]);
	}
}
