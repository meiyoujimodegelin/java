import java.util.Scanner;
import java.util.Arrays;
import java.lang.Comparable;

//ѧ���� Ҫʵ�ִӴ�С����
class Student implements Comparable<Student> {
	private int id;
	private String name;

	private int chinese, math, english;
	private int sum, average;
	// ����
	private int rank;
	// flag=1 �����ܷ����� flag=2�������ĳɼ����� flag=3������ѧ�ɼ�����
	// flag=4����Ӣ��ɼ�����
	private int flag;

	Student() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getSum() {
		return sum;
	}

	public void setSum() {
		sum = chinese + math + english;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage() {
		average = getSum() / 3;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public String toString() {
		String s = "ѧ�ţ�" + getId() + "\t" + "������" + getName() + "\t" + "���ģ� " + getChinese() + "\t" + "��ѧ�� " + getMath()
				+ "\t" + "Ӣ�" + getEnglish() + "\t" + "�ܷ֣�" + getSum() + "\t" + "ƽ���֣�" + getAverage() + "\t" + "������"
				+ getRank() + "\t";
		return s;
	}

	public int compareTo(Student o) {

		switch (flag) {
		// �����ִܷӴ�С����
		case 1:
			return (o.getSum() - this.getSum());
		// �������ĴӴ�С����
		case 2:
			return (o.getChinese() - this.getChinese());
		// ������ѧ �Ӵ�С����
		case 3:
			return (o.getMath() - this.getMath());
		// ����Ӣ��Ӵ�С����
		case 4:
			return (o.getEnglish() - this.getEnglish());
		}
		return (o.getSum() - this.getSum());

	}

}

// ������
class Manage {
	// ���һ��ѧ��
	Scanner reader = new Scanner(System.in);

	public Manage() {
		// TODO Auto-generated constructor stub
	}

	void add(Student[] students, int[] len) {
		if (isFull(students, len)) {
			System.out.println("���ʧ��");
			// ����
			return;
		}
		Student s = new Student();
		System.out.println("����ѧ�ţ�");
		int id = reader.nextInt();
		System.out.println("����ѧ��������");
		String name = reader.next();
		s.setId(id);
		s.setName(name);
		students[len[0]] = s;
		len[0]++;
		System.out.println("��ӳɹ�");
	}

	// ����һ��ѧ��
	Student search(Student[] students, int[] len) {
		System.out.println("����ѧ�����");
		int id = reader.nextInt();
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ���ѧ��");
			// ����
			return null;
		}

		int index = getIndex(students, len, id);
		// ѧ����Ų����ڣ������ʧ��
		if (index == -1) {
			System.out.println("����ʧ��");
			return null;
		}
		return students[index];
	}

	// ����ѧ����Ÿ���ѧ��������Ϣ
	void update(Student[] students, int[] len) {
		System.out.println("������ѧ��");
		int id = reader.nextInt();
		// ����Ϊ�� ����ʧ��
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ���ѧ��");
			return;
		}
		int index = getIndex(students, len, id);
		// ѧ����Ų����ڣ������ʧ��
		if (index == -1) {
			System.out.println("����ʧ��");
			return;
		}
		// ����ѧ������ ����������Ҫ�ٸ�������ֵ
		System.out.println("����ѧ��������");
		String name = reader.next();
		students[index].setName(name);
		return;

	}

	// ����ѧ�����ɾ��ѧ�� ����Ҫ�ı�
	void delete(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ���ѧ��");
			return;
		}
		System.out.println("����ѧ�ţ�");
		int id = reader.nextInt();
		// ͨ��ѧ��ȷ��ѧ����λ��
		int index = getIndex(students, len, id);
		// ����ɾ��0 ��len-1λ�õ�ѧ��
		if (index == -1) {
			System.out.println("ɾ��ʧ��");
			return;
		}
		for (int i = index + 1; i < len[0]; i++) {
			students[i - 1] = students[i];
		}
		len[0]--;
		System.out.println("ɾ���ɹ�");

		return;
	}

	// ����ѧ���������ѧ�����ųɼ�
	void inputGrade(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ�û��ѧ����");
			return;
		}
		System.out.println("����ѧ�ţ�");
		int id = reader.nextInt();
		int index = getIndex(students, len, id);
		if (index == -1) {
			System.out.println("�����ѧ�Ų�����");
			return;
		}
		// ��ʼ���óɼ�
		System.out.println("�������ĵĳɼ���");
		int chinese = reader.nextInt();
		if (chinese < 0 || chinese > 100) {
			System.out.println("�ɼ���Ч");
			return;
		}
		System.out.println("������ѧ�ĳɼ���");

		int math = reader.nextInt();
		if (math < 0 || math > 100) {
			System.out.println("�ɼ���Ч");
			return;
		}
		System.out.println("����Ӣ��ĳɼ���");
		int english = reader.nextInt();
		if (english < 0 || english > 100) {
			System.out.println("�ɼ���Ч");
			return;
		}
		students[index].setChinese(chinese);
		students[index].setMath(math);
		students[index].setEnglish(english);
		students[index].setSum();
		students[index].setAverage();
		return;
	}

	// ����ĳ�ųɼ���������
	void sortBySimple(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ�û��ѧ����");
			return;
		}
		// ѧ�����յ�����¼�������
		System.out.println("��ѡ��");
		System.out.println("1���������ĳɼ�����");
		System.out.println("2��������ѧ�ɼ�����");
		System.out.println("3������Ӣ��ɼ�����");
		int flag = reader.nextInt();
		if (flag != 1 && flag != 2 && flag != 3) {
			System.out.println("��������");
			return;
		}
		for (int i = 0; i < len[0]; i++) {
			students[i].setFlag(flag + 1);
		}
		Arrays.sort(students);
		// ��������
		for (int i = 0; i < len[0]; i++) {
			int rank = getIndexBySum(students, len, students[i].getSum()) + 1;
			students[i].setRank(rank);

		}
		System.out.println("�������");
		return;
	}

	// �����ܷ�����
	void sortBySum(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ�û��ѧ����");
			return;
		}

		// ѧ�����յ�����¼�������
		for (int i = 0; i < len[0]; i++) {
			students[i].setFlag(1);
		}

		Arrays.sort(students);

		// ��������
		for (int i = 0; i < len[0]; i++) {
			int rank = getIndexBySum(students, len, students[i].getSum()) + 1;
			students[i].setRank(rank);

		}
		System.out.println("�������");
		return;
	}

	// ��ӡ��ǰ��Ϣ
	void show(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("��ǰ�Ѿ���ѧ��");
			return;
		}
		for (int i = 0; i < len[0]; i++) {
			System.out.println(students[i].toString());
		}
	}

	// ͨ��ѧ��ȷ��ѧ���������е�λ��
	int getIndex(Student[] students, int[] len, int id) {
		// ˳�����
		for (int i = 0; i < len[0]; i++) {
			if (students[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// ͨ���ܷ�ȷ��ѧ���������е�λ��
	int getIndexBySum(Student[] students, int[] len, int sum) {
		for (int i = 0; i < len[0]; i++) {
			if (students[i].getSum() == sum) {
				return i;
			}
		}
		return -1;
	}

	// �п�
	boolean isEmpty(Student[] students, int[] len) {
		if (len[0] == 0) {
			return true;
		} else {
			return false;
		}

	}

	// ����
	boolean isFull(Student[] students, int[] len) {
		if (len[0] >= 10) {
			return true;
		} else {
			return false;
		}
	}

}

public class StudentInfo {

	public static void main(String[] args) {
		// ��ʾѧ����Ϣ
		Scanner reader = new Scanner(System.in);
		// System.out.println("����ѧ������");
		// int num=reader.nextInt();
		// �����10��ѧ��
		Student[] students = new Student[10];
		for (int i = 0; i < 10; i++) {
			students[i] = new Student();
		}
		// ��ǰ����Ϊ0
		int[] len = new int[] { 0 };
		Manage manage = new Manage();
		while (true) {
			System.out.println("��ѡ����Ҫִ�еĹ���:");
			System.out.println("0�����һ��ѧ��");
			System.out.println("1������һ��ѧ��");
			System.out.println("2������ѧ����Ÿ���ѧ��������Ϣ");
			System.out.println("3������ѧ�����ɾ��ѧ��");
			System.out.println("4������ѧ���������ѧ�����ųɼ�");
			System.out.println("5������ĳ�ųɼ���������");
			System.out.println("6�������ܷ�����");
			System.out.println("7����ӡ��Ϣ");
			System.out.println("8���˳�ϵͳ");
			// ����������Ķ���
			int key = reader.nextInt();
			switch (key) {
			case 0:
				System.out.println("���һ��ѧ��\n");
				manage.add(students, len);
				break;
			case 1:
				System.out.println("����һ��ѧ��\n");
				Student s = manage.search(students, len);
				if (s == null) {
					System.out.println();
				} else {
					System.out.println(s);
				}

				break;
			case 2:
				System.out.println("����ѧ����Ÿ���ѧ��������Ϣ\n");
				manage.update(students, len);
				break;
			case 3:
				System.out.println("����ѧ�����ɾ��ѧ��\n");
				manage.delete(students, len);
				break;
			case 4:
				System.out.println("����ѧ���������ѧ�����ųɼ�\n");
				manage.inputGrade(students, len);
				break;
			case 5:
				System.out.println("����ĳ�ųɼ���������\n");
				manage.sortBySimple(students, len);
				break;

			case 6:
				System.out.println("�����ܷ�����\n");
				manage.sortBySum(students, len);
				break;
			case 7:
				System.out.println("��ӡ��Ϣ\n");
				manage.show(students, len);
				break;
			case 8:
				System.out.println("���˳�ϵͳ\n");
				break;
			default:
				System.out.println("��������");
				break;
			}
			if (key == 8) {
				break;
			}
		}
	}
}
