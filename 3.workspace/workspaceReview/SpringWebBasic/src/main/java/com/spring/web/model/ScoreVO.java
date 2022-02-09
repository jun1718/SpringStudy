package com.spring.web.model;

public class ScoreVO {
	private String stuName;
	private Integer kor;
	private Integer eng;
	private Integer math;
	private Integer total;
	private Double average;
	
	
	public ScoreVO() {
		// TODO Auto-generated constructor stub
	}


	public void setData() {
		this.total = kor + eng + math;
		this.average = Math.round(total / 3.0 * 100) / 100.0;
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
	
	
	
}
