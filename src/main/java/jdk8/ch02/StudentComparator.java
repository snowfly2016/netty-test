package jdk8.ch02;

public class StudentComparator {

    public int compareStudentByScore(Student student1,Student student2){
        return student1.getScore() - student2.getScore();
    }

    public int compareStudentByName(Student student1,Student student2){
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
