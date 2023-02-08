package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.Constants.*;

public class DataSource {
    private Connection conn;

    private PreparedStatement queryStudent;
    private PreparedStatement queryStudents;
    private PreparedStatement queryCourses;
    private PreparedStatement queryCurrentCourses;
    private PreparedStatement queryCompletedCourses;
    private PreparedStatement queryForCompletedCourse;
    private PreparedStatement queryUsers;
    private PreparedStatement insertIntoCompletedCourses;
    private PreparedStatement deleteCurrentCourse;
    private PreparedStatement queryForCurrentCourse;
    private PreparedStatement insertIntoCurrentCourses;
    private PreparedStatement insertStudent;
    private PreparedStatement updateStudent;
    private PreparedStatement deleteStudent;
    private PreparedStatement updateCurrentCourse;
    private PreparedStatement querycredits;


    private static DataSource instance = new DataSource();

    public static DataSource getInstance() {
        return instance;
    }

    private DataSource(){

    }
    public boolean open(){
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION_STRING,username,password);
            queryStudent = conn.prepareStatement(query_Student);
            queryStudents = conn.prepareStatement(getQuery_Students);
            queryCourses = conn.prepareStatement(query_Courses);
            queryCurrentCourses = conn.prepareStatement(query_Current_Courses);
            queryCompletedCourses = conn.prepareStatement(query_Completed_Courses);
            queryForCompletedCourse = conn.prepareStatement(query_For_Completed_Course);
            queryUsers = conn.prepareStatement(query_User);
            insertIntoCompletedCourses = conn.prepareStatement(insert_Completed_course);
            deleteCurrentCourse = conn.prepareStatement(delete_current_course);
            queryForCurrentCourse = conn.prepareStatement(query_For_Current_Course);
            insertIntoCurrentCourses = conn.prepareStatement(insert_current_course);
            insertStudent = conn.prepareStatement(insert_student);
            updateStudent = conn.prepareStatement(update_student);
            deleteStudent = conn.prepareStatement(delete_student);
            updateCurrentCourse = conn.prepareStatement(update_completed_course);
            querycredits = conn.prepareStatement(select_credits);

            return true;
        }catch (SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
            //e.printStackTrace();
            return false;
        }
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void close(){
        try {

            if(queryStudent != null) {
                queryStudent.close();
            }
            if(queryStudents != null) {
                queryStudents.close();
            }
            if(queryCourses != null) {
                queryCourses.close();
            }
            if(queryCurrentCourses != null) {
                queryCurrentCourses.close();
            }
            if (queryCompletedCourses != null){
                queryCompletedCourses.close();
            }
            if(queryForCompletedCourse !=null){
                queryForCompletedCourse.close();
            }
            if(queryUsers != null){
                queryUsers.close();
            }
            if(insertIntoCompletedCourses != null){
                insertIntoCompletedCourses.close();
            }
            if (deleteCurrentCourse != null){
                deleteCurrentCourse.close();
            }
            if(queryForCurrentCourse != null){
                queryForCurrentCourse.close();
            }
            if(insertIntoCurrentCourses != null){
                insertIntoCurrentCourses.close();
            }
            if(insertStudent != null){
                insertStudent.close();
            }
            if(updateStudent !=null){
                updateStudent.close();
            }
            if(deleteStudent != null){
                deleteStudent.close();
            }
            if(updateCurrentCourse !=null){
                updateCurrentCourse.close();
            }
            if(queryStudents != null){
                querycredits.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldnt close connection " + e.getMessage());
        }
    }

    public List<Student> queryStudents(){
        try {
            ResultSet results = queryStudents.executeQuery();
            List<Student> students = new ArrayList<>();
            while (results.next()) {
                Student student = new Student();
                student.setId(results.getInt(COLUMN_STUDENT_ID));
                student.setProgram(results.getString(COLUMN_STUDENT_PROGRAM));
                student.setName(results.getString(COLUMN_STUDENT_NAME));
                student.setSurname(results.getString(COLUMN_STUDENT_SURNAME));
                student.setSemester(results.getInt(COLUMN_STUDENT_SEMESTER));
                student.setStatus(results.getString(COLUMN_STUDENT_STATUS));
                student.setRegistrationDate(results.getString(COLUMN_STUDENT_REGISTRATION_DATE));
                student.setCgpa(results.getFloat(COLUMN_STUDENT_CGPA));
                student.setBirthDate(results.getString(COLUMN_STUDENT_BIRTH_DATE));
                student.setBirthPlace(results.getString(COLUMN_STUDENT_BIRTH_PLACE));
                student.setFatherName(results.getString(COLUMN_STUDENT_FATHER_NAME));
                student.setFatherSurname(results.getString(COLUMN_STUDENT_FATHER_SURNAME));
                student.setMotherName(results.getString(COLUMN_STUDENT_MOTHER_NAME));
                student.setMotherSurname(results.getString(COLUMN_STUDENT_MOTHER_SURNAME));
                student.setEmail(results.getString(COLUMN_STUDENT_EMAIL));
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Student queryStudent(String email) {
        try {
            queryStudent.setString(1,email);
            ResultSet result = queryStudent.executeQuery();
            Student student = new Student();
            while (result.next()) {
                student.setId(result.getInt(COLUMN_STUDENT_ID));
                student.setProgram(result.getString(COLUMN_STUDENT_PROGRAM));
                student.setName(result.getString(COLUMN_STUDENT_NAME));
                student.setSurname(result.getString(COLUMN_STUDENT_SURNAME));
                student.setSemester(result.getInt(COLUMN_STUDENT_SEMESTER));
                student.setStatus(result.getString(COLUMN_STUDENT_STATUS));
                student.setRegistrationDate(result.getString(COLUMN_STUDENT_REGISTRATION_DATE));
                student.setCgpa(result.getFloat(COLUMN_STUDENT_CGPA));
                student.setBirthDate(result.getString(COLUMN_STUDENT_BIRTH_DATE));
                student.setBirthPlace(result.getString(COLUMN_STUDENT_BIRTH_PLACE));
                student.setFatherName(result.getString(COLUMN_STUDENT_FATHER_NAME));
                student.setFatherSurname(result.getString(COLUMN_STUDENT_FATHER_SURNAME));
                student.setMotherName(result.getString(COLUMN_STUDENT_MOTHER_NAME));
                student.setMotherSurname(result.getString(COLUMN_STUDENT_MOTHER_SURNAME));
                student.setEmail(result.getString(COLUMN_STUDENT_EMAIL));
            }
            return student;
        }catch (SQLException e){
            System.out.println("Culdnt find student " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Course>  queryCourses(String program) {
        try {
            queryCourses.setString(1, program);
            ResultSet results = queryCourses.executeQuery();
            List<Course> courses= new ArrayList<>();
            while (results.next()){
                Course course = new Course();
                course.setCode(results.getString(COLUMN_COURSE_CODE));
                course.setName(results.getString(COLUMN_COURSE_NAME));
                course.setProgram(results.getString(COLUMN_COURSE_PROGRAM));
                course.setCategory(results.getString(COLUMN_COURSE_CATEGORY));
                course.setCredits(results.getInt(COLUMN_COURSE_CREDITS));
                course.setProfessor(results.getString(COLUMN_COURSE_PROFESSOR));
                course.setSyllabus(results.getString(COLUMN_COURSE_SYLLABUS));
                courses.add(course);
            }
            return courses;
        }catch (SQLException e ){
            System.out.println("Couldnt find courses " + e.getMessage());
            return  null;
        }
    }
    public List<CurrentCourse>  queryCurrentCourses(int student_id) {
        try {
            queryCurrentCourses.setInt(1, student_id);
            ResultSet results = queryCurrentCourses.executeQuery();
            List<CurrentCourse> currentCourses = new ArrayList<>();
            while (results.next()){
                CurrentCourse currentCourse = new CurrentCourse();
                currentCourse.setId_student(results.getInt(COLUMN_CURRENT_COURSES_ID_STUDENT));
                currentCourse.setCode(results.getString(COLUMN_CURRENT_COURSES_CODE));
                currentCourse.setSemester(results.getInt(COLUMN_CURRENT_COURSES_SEMESTER));
                currentCourse.setMidterm(results.getFloat(COLUMN_CURRENT_COURSES_MIDTERM));
                currentCourse.setFinalExam(results.getFloat(COLUMN_CURRENT_COURSES_FINAL));
                currentCourse.setMidtermPercentage(results.getFloat(COLUMN_CURRENT_COURSES_MIDTERM_PERCENTAGE));
                currentCourse.setFinalPercentage(results.getFloat(COLUMN_CURRENT_COURSES_FINAl_PERCENTAGE));
                currentCourses.add(currentCourse);

            }
            for(CurrentCourse course :currentCourses){
                System.out.println(course.toString());
            }
            return currentCourses;
        }catch (SQLException e ){
            System.out.println("COuldnt find current courses " + e.getMessage());
            //e.printStackTrace();
            return  null;
        }
    }
    public List<Completed_course>  queryCompletedCourses(int student_id) {
        try {
            queryCompletedCourses.setInt(1, student_id);
            ResultSet results = queryCompletedCourses.executeQuery();
            List<Completed_course> completedCourses = new ArrayList<>();
            while (results.next()){
                Completed_course completedCourse = new Completed_course();
                completedCourse.setId_student(results.getInt(COLUMN_COMPLETED_COURSES_ID_STUDENT));
                completedCourse.setCode(results.getString(COLUMN_COMPLETED_COURSES_CODE));
                completedCourse.setSemester(results.getInt(COLUMN_COMPLETED_COURSES_SEMESTER));
                completedCourse.setGrade(results.getFloat(COLUMN_COMPLETED_COURSES_GRADE));
                completedCourses.add(completedCourse);
            }
            return completedCourses;
        }catch (SQLException e ){
            System.out.println("Couldnt find complted courses " + e.getMessage());
           //e.printStackTrace();
            return  null;
        }
    }

    public boolean queryForCompletedCourse (int id, String code, int semester){
        try{
            queryForCompletedCourse.setInt(1,id);
            queryForCompletedCourse.setString(2,code);
            queryForCompletedCourse.setInt(3,semester);
            ResultSet result = queryForCompletedCourse.executeQuery();
            return result.next();
        }catch (SQLException e){
            System.out.println("problem " + e.getMessage());
            return false;
        }
    }
    public void deleteCurrentCourse(String code){
        try {
            deleteCurrentCourse.setString(1, code);
            deleteCurrentCourse.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void InsertCompletedCourse(CurrentCourse course){
        try {
            double grade = (course.getMidterm() * course.getMidtermPercentage() * 0.01 ) +
                    (course.getFinalExam() * course.getFinalPercentage() * 0.01);
            conn.setAutoCommit(false);
            insertIntoCompletedCourses.setInt(1,course.getId_student());
            insertIntoCompletedCourses.setString(2, course.getCode());
            insertIntoCompletedCourses.setInt(3, course.getSemester());
            insertIntoCompletedCourses.setDouble(4, grade);
            deleteCurrentCourse.setString(1,course.getCode());
            int affectedRows = insertIntoCompletedCourses.executeUpdate();
            affectedRows += deleteCurrentCourse.executeUpdate();
            if(affectedRows == 2){
                conn.commit();
            }else throw new SQLException("The completed course insert failed");

        }catch (Exception e){
            e.printStackTrace();
            try{
                System.out.println("performin roll back");
                conn.rollback();
            }catch (SQLException e2){
                System.out.println("rollback failed");
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }
    }
    public boolean queryForCurrentCourse (String code){
        try{

            queryForCurrentCourse.setString(1,code);
            ResultSet result = queryForCurrentCourse.executeQuery();
            return result.next();
        }catch (SQLException e){
            System.out.println("problem " + e.getMessage());
            return false;
        }
    }
    public void insertIntoCurrentCourses(String code, Student student){
        try {
            if(queryForCurrentCourse(code)){
                System.out.println("Curse already selected");
                return;
            }
            insertIntoCurrentCourses.setInt(1,student.getId());
            insertIntoCurrentCourses.setString(2,code);
            insertIntoCurrentCourses.setInt(3, student.getSemester());
            insertIntoCurrentCourses.executeUpdate();
        }catch (SQLException e){
            System.out.println("Insert into current courses failed " + e.getMessage());
        }
    }

    public void insertStudent(Student student){
        try {
            insertStudent.setInt(1, student.getId());
            insertStudent.setString(2,student.getProgram());
            insertStudent.setString(3,student.getName());
            insertStudent.setString(4, student.getSurname());
            insertStudent.setInt(5, student.getSemester());
            insertStudent.setString(6, student.getStatus());
            insertStudent.setString(7, student.getRegistrationDate());
            insertStudent.setFloat(8, student.getCgpa());
            insertStudent.setString(9, student.getBirthDate());
            insertStudent.setString(10, student.getBirthPlace());
            insertStudent.setString(11, student.getFatherName());
            insertStudent.setString(12, student.getFatherSurname());
            insertStudent.setString(13, student.getMotherName());
            insertStudent.setString(14, student.getMotherSurname());
            insertStudent.setString(15, student.getEmail());
            insertStudent.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void updateStudent(Student student){
        try {
            updateStudent.setInt(1, student.getId());
            updateStudent.setString(2,student.getProgram());
            updateStudent.setString(3,student.getName());
            updateStudent.setString(4, student.getSurname());
            updateStudent.setInt(5, student.getSemester());
            updateStudent.setString(6, student.getStatus());
            updateStudent.setString(7, student.getRegistrationDate());
            updateStudent.setFloat(8, student.getCgpa());
            updateStudent.setString(9, student.getBirthDate());
            updateStudent.setString(10, student.getBirthPlace());
            updateStudent.setString(11, student.getFatherName());
            updateStudent.setString(12, student.getFatherSurname());
            updateStudent.setString(13, student.getMotherName());
            updateStudent.setString(14, student.getMotherSurname());
            updateStudent.setString(15, student.getEmail());
            updateStudent.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(Student student){
        try {
            deleteStudent.setInt(1, student.getId());
            deleteStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentCourse(CurrentCourse course){
        try {
            updateCurrentCourse.setFloat(1,course.getMidterm());
            updateCurrentCourse.setFloat(2,course.getFinalExam());
            updateCurrentCourse.setFloat(3, course.getMidtermPercentage());
            updateCurrentCourse.setFloat(4,course.getFinalPercentage());
            updateCurrentCourse.setString(5, course.getCode());
            updateCurrentCourse.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int queryCredits(Completed_course course){
        try {
            querycredits.setString(1,course.getCode());
            ResultSet result = querycredits.executeQuery();
            int credits = 0;
            while (result.next()){
                credits = result.getInt(1);
            }
            return credits;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public User queryUsers(String email){
        try {
            queryUsers.setString(1, email);
            ResultSet result = queryUsers.executeQuery();
            User user = new User();
            while (result.next()){
                user.setEmail(result.getString(COLUMN_USER_EMAIL));
                user.setPassword(result.getString(COLUMN_USER_PASSWORD));
                user.setType(result.getString(COLUMN_USER_TYPE));
            }
            return user;
        }catch (SQLException e){
            System.out.println("Wrong email or password " + e.getMessage());
           // e.printStackTrace();
            return  null;
        }
    }
    public String login(User user){
        return user.getType();
    }
}























