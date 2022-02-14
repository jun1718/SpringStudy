package com.spring.database.jdbc.score.model;

public class ScoreVO {
/*
 -- 점수 테이블 생성 : DB에 이렇게 작성하면됨 : 이름규칙도 java에서는 카멜인데 db에서는 _를 씀 그래도 다르게설정
CREATE TABLE scores (
	stu_id INT PRIMARY KEY AUTO_INCREMENT,
    stu_name VARCHAR(60) NOT NULL,
    kor INT,
    eng INT,
    math INT,
    total INT,
    average FLOAT(5, 2) -- 다섯자리수까지, 소수점표현은 두번째자리까지 최대수 -> 100.00 5자리에 소수점 2개 99.99면 4자리에 2 
);
 */
	
	private Integer stuId;
	private String stuName;
	private Integer kor;
	private Integer eng;
	private Integer math;
	private Integer total;
	private Double average;
	
	//총점 평균을 구하는 메서드
	public void calcData() {
		this.total = this.kor + this.eng + this.math;
		this.average = Math.round((this.total / 3.0) * 100) / 100.0;
		
	}
	
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getKor() {
		return kor;
	}
	public void setKor(Integer kor) {
		this.kor = kor;
	}
	public Integer getEng() {
		return eng;
	}
	public void setEng(Integer eng) {
		this.eng = eng;
	}
	public Integer getMath() {
		return math;
	}
	public void setMath(Integer math) {
		this.math = math;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	
	
	@Override
	public String toString() {
		return "ScoreVO [stuId=" + stuId + ", stuName=" + stuName + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", average=" + average + "]";
	}
	
	
	
}
