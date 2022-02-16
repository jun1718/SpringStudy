package com.spring.database.mybatis.score.model;

public class ScoreVO {
	private int stuId;
	private String stuName;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double average;
	
	public void setData() {
		this.total = kor + eng + math;
		this.average = Math.round(total / 3.0 * 100) / 100.0;
	}
	
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	
	
	@Override
	public String toString() {
		return "ScoreVO [stuId=" + stuId + ", stuName=" + stuName + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", average=" + average + "]";
	}
	
	
	

}
