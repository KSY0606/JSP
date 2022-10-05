package exam.ex01;


import java.sql.Date;

public class MemberBean {
	private int num;
	private String name;
	private String subject;
	private String text;
	private String pass;
	private int count;
	private Date regdate;
	
	public MemberBean() {
		
	}
	
	public MemberBean(int num, String name, String subject, String text, String pass, int count) {
		this.num = num;
		this.name = name;
		this.subject = subject;
		this.text = text;
		this.pass = pass;
		this.count = count;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
