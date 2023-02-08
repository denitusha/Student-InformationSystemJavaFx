package model;

public class Constants {
    public static final String DB_NAME = "eis";
    public static final String username = "root";
    public static final String password = "12345678";
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" +DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_TYPE = "type";

    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_STUDENT_ID = "id_student";
    public static final String COLUMN_STUDENT_NAME = "name";
    public static final String COLUMN_STUDENT_SURNAME = "surname";
    public static final String COLUMN_STUDENT_BIRTH_DATE= "birth_date";
    public static final String COLUMN_STUDENT_BIRTH_PLACE = "birth_place";
    public static final String COLUMN_STUDENT_FATHER_NAME = "father_name";
    public static final String COLUMN_STUDENT_FATHER_SURNAME = "father_surname";
    public static final String COLUMN_STUDENT_MOTHER_NAME = "mother_name";
    public static final String COLUMN_STUDENT_MOTHER_SURNAME ="mother_surname";
    public static final String COLUMN_STUDENT_PROGRAM = "program";
    public static final String COLUMN_STUDENT_SEMESTER = "semester";
    public static final String COLUMN_STUDENT_STATUS = "status";
    public static final String COLUMN_STUDENT_REGISTRATION_DATE = "registration_date";
    public static final String COLUMN_STUDENT_CGPA = "CGPA";
    public static final String COLUMN_STUDENT_EMAIL = "email";

    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE_CODE = "code";
    public static final String COLUMN_COURSE_NAME = "name";
    public static final String COLUMN_COURSE_PROGRAM = "program";
    public static final String COLUMN_COURSE_CATEGORY = "category";
    public static final String COLUMN_COURSE_CREDITS = "credits";
    public static final String COLUMN_COURSE_PROFESSOR = "professor";
    public static final String COLUMN_COURSE_SYLLABUS = "syllabus";

    public static final String TABLE_CURRENT_COURSES = "current_courses";
    public static final String COLUMN_CURRENT_COURSES_ID_STUDENT = "id_student";
    public static final String COLUMN_CURRENT_COURSES_CODE = "code";
    public static final String COLUMN_CURRENT_COURSES_SEMESTER = "semester";
    public static final String COLUMN_CURRENT_COURSES_MIDTERM = "midterm";
    public static final String COLUMN_CURRENT_COURSES_FINAL = "final";
    public static final String COLUMN_CURRENT_COURSES_MIDTERM_PERCENTAGE = "midterm_percentage";
    public static final String COLUMN_CURRENT_COURSES_FINAl_PERCENTAGE = "final_percentage";

    public static final String TABLE_COMPLETED_COURSES = "completed_courses";
    public static final String COLUMN_COMPLETED_COURSES_ID_STUDENT = "id_student";
    public static final String COLUMN_COMPLETED_COURSES_CODE = "code";
    public static final String COLUMN_COMPLETED_COURSES_SEMESTER ="semester";
    public static final String COLUMN_COMPLETED_COURSES_GRADE = "grade";

    public static final String query_Student = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
            COLUMN_STUDENT_EMAIL + " = ?";
    public static final String getQuery_Students = "SELECT * FROM " + TABLE_STUDENTS;
    public static final String query_Courses = "SELECT * FROM " + TABLE_COURSES + " WHERE " +
            COLUMN_COURSE_PROGRAM + " = ?";
    public static final String query_Current_Courses = "SELECT * FROM " + TABLE_CURRENT_COURSES +
            " WHERE " + COLUMN_CURRENT_COURSES_ID_STUDENT + " = ?";
    public static final String query_User = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL +
            " = ?";
    public static final String query_Completed_Courses = "SELECT * FROM " + TABLE_COMPLETED_COURSES + " WHERE "
            + COLUMN_COMPLETED_COURSES_ID_STUDENT + " = ? ORDER BY " + COLUMN_COMPLETED_COURSES_SEMESTER;
    public static final String query_For_Completed_Course = "SELECT * FROM " + TABLE_COMPLETED_COURSES + " WHERE " +
            COLUMN_COMPLETED_COURSES_ID_STUDENT + " = ? AND " + COLUMN_COMPLETED_COURSES_CODE + " = ? AND " +
            COLUMN_COMPLETED_COURSES_SEMESTER + " = ?";

    public static final String insert_Completed_course = "INSERT INTO " + TABLE_COMPLETED_COURSES + '(' +
            COLUMN_COMPLETED_COURSES_ID_STUDENT + ", " + COLUMN_COMPLETED_COURSES_CODE + ", " +
            COLUMN_COMPLETED_COURSES_SEMESTER + ", " + COLUMN_COMPLETED_COURSES_GRADE + ')' +
            " VALUES " + "(?, ?, ?,?)";
    public static final String delete_current_course ="DELETE FROM " + TABLE_CURRENT_COURSES +
            " WHERE " + COLUMN_CURRENT_COURSES_CODE + " =?";

    public static final String query_For_Current_Course = "SELECT * FROM " + TABLE_CURRENT_COURSES + " WHERE " +
            COLUMN_CURRENT_COURSES_CODE + " = ?";
    public static final String insert_current_course = "INSERT INTO " + TABLE_CURRENT_COURSES +
            '(' +COLUMN_CURRENT_COURSES_ID_STUDENT + ", " + COLUMN_CURRENT_COURSES_CODE +
            ", " + COLUMN_CURRENT_COURSES_SEMESTER + ')' + " VALUES (?, ?, ?)";

    public static final String insert_student = "INSERT INTO students (id_student, program, name, surname, semester, status, registration_date, CGPA, birth_date, birth_place, father_name, father_surname, mother_name, mother_surname, email)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String update_student = "UPDATE students SET id_student = ?, program = ?, name = ?, surname = ?, semester = ?, status = ?, registration_date = ?, CGPA = ?, birth_date = ?, birth_place = ?, father_name = ?, father_surname = ?, mother_name = ?, mother_surname = ? WHERE (email = ?)";
//            "INSET INTO " + TABLE_STUDENTS + '(' +COLUMN_STUDENT_ID + ", " +
//            COLUMN_STUDENT_PROGRAM + ", " + COLUMN_STUDENT_NAME + ", " + COLUMN_STUDENT_SURNAME + ", " +
//            COLUMN_STUDENT_SEMESTER + ", " + COLUMN_STUDENT_STATUS + ", " + COLUMN_STUDENT_REGISTRATION_DATE + ", " +
//            COLUMN_STUDENT_CGPA + ", " + COLUMN_STUDENT_BIRTH_DATE + ", " + COLUMN_STUDENT_BIRTH_PLACE + ", " +
//            COLUMN_STUDENT_FATHER_NAME + ", " + COLUMN_STUDENT_FATHER_SURNAME + ", " + COLUMN_STUDENT_MOTHER_NAME +
//            ", " + COLUMN_STUDENT_MOTHER_SURNAME + ", " + COLUMN_STUDENT_EMAIL + ')' +
//            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String delete_student = " DELETE FROM students WHERE (id_student = ?)";
    public static final String update_completed_course = "UPDATE current_courses SET midterm = ?, final = ?, midterm_percentage = ?, final_percentage = ? WHERE  (code = ?)";
    public static final String select_credits = "SELECT credits FROM courses WHERE (code = ?)";
}
