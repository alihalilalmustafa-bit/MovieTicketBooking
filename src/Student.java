public class Student {

        private static int studentCount = 0;
        private String name;

        public Student(String name) {
            this.name = name;
            studentCount += 1; // increases every time a new Student is created
        }

        public static int getStudentCount() {
            return studentCount;
        }
    }

