import java.util.ArrayList;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();

        addCourse(courses, new Course("CS101", "Java Programming", 2));
        addCourse(courses, new Course("CS102", "Data Structures", 30));

        System.out.println("\n=== 進行選課操作 ===");
        enrollStudent(courses, "CS101");
        enrollStudent(courses, "CS101");
        enrollStudent(courses, "CS101");

        System.out.println("\n=== 進行退選操作 ===");
        withdrawStudent(courses, "CS101");

        printReport(courses);
    }

    public static boolean addCourse(ArrayList<Course> list, Course course) {
        if (findCourse(list, course.getCode()) != null) {
            System.out.println("❌ 課程代碼重複，新增失敗！");
            return false;
        }
        list.add(course);
        System.out.println("✅ 成功開課：" + course.getName());
        return true;
    }

    public static Course findCourse(ArrayList<Course> list, String code) {
        for (Course c : list) {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    public static void enrollStudent(ArrayList<Course> list, String code) {
        Course c = findCourse(list, code);
        if (c == null) {
            System.out.println("❌ 找不到課程：" + code);
        } else if (c.register()) {
            System.out.println("✅ 報名成功：" + c.getName());
        } else {
            System.out.println("❌ 報名失敗！課程 [" + c.getName() + "] 已經額滿！");
        }
    }

    public static void withdrawStudent(ArrayList<Course> list, String code) {
        Course c = findCourse(list, code);
        if (c != null && c.withdraw()) {
            System.out.println("✅ 退選成功：" + c.getName());
        } else {
            System.out.println("❌ 退選失敗，找不到課程或目前選課人數為 0。");
        }
    }

    public static void printReport(ArrayList<Course> list) {
        System.out.println("\n================ 系統統計報告 ================");
        int totalStudents = 0;
        int fullCoursesCount = 0;

        for (Course c : list) {
            System.out.println(c);
            totalStudents += c.getEnrolled();
            if (c.isFull()) fullCoursesCount++;
        }

        System.out.println("----------------------------------------------");
        System.out.println("總開設課程數：" + list.size());
        System.out.println("總選課總人次：" + totalStudents);
        System.out.println("已額滿課程數：" + fullCoursesCount);
    }
}