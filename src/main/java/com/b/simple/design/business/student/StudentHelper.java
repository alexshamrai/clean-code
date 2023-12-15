package com.b.simple.design.business.student;
public class StudentHelper {

	public static final int GRADE_B_LOWER_BOUNDARY = 51;
	public static final int GRADE_B_UPPER_BOUNDARY = 80;
	public static final int EXTRA_INCREASE_FOR_MATH = 10;
	public static final int A_GRADE_LIMIT = 91;
	public static final int B_GRADE_LIMIT = 51;
	public static final int MATHS_EXTRA_ADDITION = 5;
	public static final int GOOD_MARK_BOUNDARY = 80;
	public static final int BAD_MARK_BOUNDARY = 20;

	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {
		int extraIncrease = isMaths ? EXTRA_INCREASE_FOR_MATH : 0;
		int upperBoundary = GRADE_B_UPPER_BOUNDARY + extraIncrease;
		return marks >= GRADE_B_LOWER_BOUNDARY && marks <= upperBoundary;
	}

	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

	public String getGrade(int mark, boolean isMaths) {
		int extraLimitForMaths = isMaths ? MATHS_EXTRA_ADDITION : 0;
		if (mark >= A_GRADE_LIMIT + extraLimitForMaths)
			return "A";
		if (mark >= B_GRADE_LIMIT + extraLimitForMaths)
			return "B";
		return "C";
	}

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     * 
     * Return value can be YES, NO or MAYBE.
     * 
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     * 
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     * 
     * marks1 - your marks
     * marks2 - your friends marks
    */
        
    public String willQualifyForQuiz(int yourMarks, int yourFriendsMarks, boolean isMaths) {
        if (isBadInSubject(yourMarks, isMaths) || isBadInSubject(yourFriendsMarks, isMaths))
			return "NO";
        if (isGoodInSubject(yourMarks, isMaths) || isGoodInSubject(yourFriendsMarks, isMaths))
			return "YES";
        return "MAYBE";
    }

	private static boolean isGoodInSubject(int marks, boolean isMaths) {
		int extraLimitForMaths = isMaths ? MATHS_EXTRA_ADDITION : 0;
		return marks >= GOOD_MARK_BOUNDARY + extraLimitForMaths;
	}

	private static boolean isBadInSubject(int marks, boolean isMaths) {
		int extraLimitForMaths = isMaths ? MATHS_EXTRA_ADDITION : 0;
		return marks <= BAD_MARK_BOUNDARY + extraLimitForMaths;
	}

}