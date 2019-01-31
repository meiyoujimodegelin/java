import java.util.Scanner;
import java.util.Arrays;
import java.lang.Comparable;

//学生类 要实现从大到小排序
class Student implements Comparable<Student> {
	private int id;
	private String name;

	private int chinese, math, english;
	private int sum, average;
	// 排名
	private int rank;
	// flag=1 按照总分排序 flag=2按照语文成绩排序 flag=3按照数学成绩排序
	// flag=4按照英语成绩排序
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
		String s = "学号：" + getId() + "\t" + "姓名：" + getName() + "\t" + "语文： " + getChinese() + "\t" + "数学： " + getMath()
				+ "\t" + "英语：" + getEnglish() + "\t" + "总分：" + getSum() + "\t" + "平均分：" + getAverage() + "\t" + "排名："
				+ getRank() + "\t";
		return s;
	}

	public int compareTo(Student o) {

		switch (flag) {
		// 按照总分从大到小排序
		case 1:
			return (o.getSum() - this.getSum());
		// 按照语文从大到小排序
		case 2:
			return (o.getChinese() - this.getChinese());
		// 按照数学 从大到小排序
		case 3:
			return (o.getMath() - this.getMath());
		// 按照英语从大到小排序
		case 4:
			return (o.getEnglish() - this.getEnglish());
		}
		return (o.getSum() - this.getSum());

	}

}

// 管理类
class Manage {
	// 添加一个学生
	Scanner reader = new Scanner(System.in);

	public Manage() {
		// TODO Auto-generated constructor stub
	}

	void add(Student[] students, int[] len) {
		if (isFull(students, len)) {
			System.out.println("添加失败");
			// 结束
			return;
		}
		Student s = new Student();
		System.out.println("输入学号：");
		int id = reader.nextInt();
		System.out.println("输入学生姓名：");
		String name = reader.next();
		s.setId(id);
		s.setName(name);
		students[len[0]] = s;
		len[0]++;
		System.out.println("添加成功");
	}

	// 查找一个学生
	Student search(Student[] students, int[] len) {
		System.out.println("输入学生编号");
		int id = reader.nextInt();
		if (isEmpty(students, len)) {
			System.out.println("当前已经无学生");
			// 结束
			return null;
		}

		int index = getIndex(students, len, id);
		// 学生编号不存在，则查找失败
		if (index == -1) {
			System.out.println("查找失败");
			return null;
		}
		return students[index];
	}

	// 根据学生编号更新学生基本信息
	void update(Student[] students, int[] len) {
		System.out.println("请输入学号");
		int id = reader.nextInt();
		// 数组为空 更新失败
		if (isEmpty(students, len)) {
			System.out.println("当前已经无学生");
			return;
		}
		int index = getIndex(students, len, id);
		// 学生编号不存在，则查找失败
		if (index == -1) {
			System.out.println("更新失败");
			return;
		}
		// 更新学生姓名 后续如有需要再更改其他值
		System.out.println("输入学生姓名：");
		String name = reader.next();
		students[index].setName(name);
		return;

	}

	// 根据学生编号删除学生 长度要改变
	void delete(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("当前已经无学生");
			return;
		}
		System.out.println("输入学号：");
		int id = reader.nextInt();
		// 通过学号确定学生的位置
		int index = getIndex(students, len, id);
		// 可以删除0 到len-1位置的学生
		if (index == -1) {
			System.out.println("删除失败");
			return;
		}
		for (int i = index + 1; i < len[0]; i++) {
			students[i - 1] = students[i];
		}
		len[0]--;
		System.out.println("删除成功");

		return;
	}

	// 根据学生编号输入学生各门成绩
	void inputGrade(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("当前已经没有学生了");
			return;
		}
		System.out.println("输入学号：");
		int id = reader.nextInt();
		int index = getIndex(students, len, id);
		if (index == -1) {
			System.out.println("输入的学号不存在");
			return;
		}
		// 开始设置成绩
		System.out.println("输入语文的成绩：");
		int chinese = reader.nextInt();
		if (chinese < 0 || chinese > 100) {
			System.out.println("成绩无效");
			return;
		}
		System.out.println("输入数学的成绩：");

		int math = reader.nextInt();
		if (math < 0 || math > 100) {
			System.out.println("成绩无效");
			return;
		}
		System.out.println("输入英语的成绩：");
		int english = reader.nextInt();
		if (english < 0 || english > 100) {
			System.out.println("成绩无效");
			return;
		}
		students[index].setChinese(chinese);
		students[index].setMath(math);
		students[index].setEnglish(english);
		students[index].setSum();
		students[index].setAverage();
		return;
	}

	// 根据某门成绩进行排序
	void sortBySimple(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("当前已经没有学生了");
			return;
		}
		// 学生不空的情况下计算排名
		System.out.println("请选择：");
		System.out.println("1：按照语文成绩排名");
		System.out.println("2：按照数学成绩排名");
		System.out.println("3：按照英语成绩排名");
		int flag = reader.nextInt();
		if (flag != 1 && flag != 2 && flag != 3) {
			System.out.println("输入有误");
			return;
		}
		for (int i = 0; i < len[0]; i++) {
			students[i].setFlag(flag + 1);
		}
		Arrays.sort(students);
		// 查找排名
		for (int i = 0; i < len[0]; i++) {
			int rank = getIndexBySum(students, len, students[i].getSum()) + 1;
			students[i].setRank(rank);

		}
		System.out.println("排序完成");
		return;
	}

	// 根据总分排序
	void sortBySum(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("当前已经没有学生了");
			return;
		}

		// 学生不空的情况下计算排名
		for (int i = 0; i < len[0]; i++) {
			students[i].setFlag(1);
		}

		Arrays.sort(students);

		// 查找排名
		for (int i = 0; i < len[0]; i++) {
			int rank = getIndexBySum(students, len, students[i].getSum()) + 1;
			students[i].setRank(rank);

		}
		System.out.println("排序完成");
		return;
	}

	// 打印当前信息
	void show(Student[] students, int[] len) {
		if (isEmpty(students, len)) {
			System.out.println("当前已经无学生");
			return;
		}
		for (int i = 0; i < len[0]; i++) {
			System.out.println(students[i].toString());
		}
	}

	// 通过学号确定学生在数组中的位置
	int getIndex(Student[] students, int[] len, int id) {
		// 顺序查找
		for (int i = 0; i < len[0]; i++) {
			if (students[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// 通过总分确定学生在数组中的位置
	int getIndexBySum(Student[] students, int[] len, int sum) {
		for (int i = 0; i < len[0]; i++) {
			if (students[i].getSum() == sum) {
				return i;
			}
		}
		return -1;
	}

	// 判空
	boolean isEmpty(Student[] students, int[] len) {
		if (len[0] == 0) {
			return true;
		} else {
			return false;
		}

	}

	// 判满
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
		// 显示学生信息
		Scanner reader = new Scanner(System.in);
		// System.out.println("输入学生人数");
		// int num=reader.nextInt();
		// 最多有10个学生
		Student[] students = new Student[10];
		for (int i = 0; i < 10; i++) {
			students[i] = new Student();
		}
		// 当前长度为0
		int[] len = new int[] { 0 };
		Manage manage = new Manage();
		while (true) {
			System.out.println("请选择你要执行的功能:");
			System.out.println("0：添加一个学生");
			System.out.println("1：查找一个学生");
			System.out.println("2：根据学生编号更新学生基本信息");
			System.out.println("3：根据学生编号删除学生");
			System.out.println("4：根据学生编号输入学生各门成绩");
			System.out.println("5：根据某门成绩进行排序");
			System.out.println("6：根据总分排序");
			System.out.println("7：打印信息");
			System.out.println("8：退出系统");
			// 创建管理类的对象
			int key = reader.nextInt();
			switch (key) {
			case 0:
				System.out.println("添加一个学生\n");
				manage.add(students, len);
				break;
			case 1:
				System.out.println("查找一个学生\n");
				Student s = manage.search(students, len);
				if (s == null) {
					System.out.println();
				} else {
					System.out.println(s);
				}

				break;
			case 2:
				System.out.println("根据学生编号更新学生基本信息\n");
				manage.update(students, len);
				break;
			case 3:
				System.out.println("根据学生编号删除学生\n");
				manage.delete(students, len);
				break;
			case 4:
				System.out.println("根据学生编号输入学生各门成绩\n");
				manage.inputGrade(students, len);
				break;
			case 5:
				System.out.println("根据某门成绩进行排序\n");
				manage.sortBySimple(students, len);
				break;

			case 6:
				System.out.println("根据总分排序\n");
				manage.sortBySum(students, len);
				break;
			case 7:
				System.out.println("打印信息\n");
				manage.show(students, len);
				break;
			case 8:
				System.out.println("已退出系统\n");
				break;
			default:
				System.out.println("输入有误");
				break;
			}
			if (key == 8) {
				break;
			}
		}
	}
}
