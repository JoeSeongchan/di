package web.model.vo;

import web.util.MyException;

public class MemberVO {

    private String id, pw, name, pinNo;

    public MemberVO(String id, String pw) throws MyException {
        setId(id);
        setPw(pw);
    }

    public MemberVO(String id, String pw, String name, String pinNo) throws MyException {
        setId(id);
        setPw(pw);
        setPinNo(pinNo);
        setName(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws MyException {
        if (id == null || id.length() == 0) {
            throw new MyException("유효하지 않은 멤버 ID입니다.");
        }
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) throws MyException {
        if (pw == null || pw.length() == 0) {
            throw new MyException("유효하지 않은 멤버 비밀번호입니다.");
        }
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MyException {
        if (name == null || name.length() == 0) {
            throw new MyException("유효하지 않은 멤버 이름입니다.");
        }
        this.name = name;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) throws MyException {
        if (pinNo == null || pinNo.length() == 0) {
            throw new MyException("유효하지 않은 핀 넘버입니다.");
        }
        this.pinNo = pinNo;
    }
}
