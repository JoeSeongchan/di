package web.model.vo;

public class Member {
    private String id, pw, name, pinNo;
    public Member(String id, String pw) {
        setId(id);
        setPw(pw);
    }
    public Member(String id, String pw, String name, String pinNo) {
        setId(id);
        setPw(pw);
        setPinNo(pinNo);
        setName(name);
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPinNo() {
        return pinNo;
    }
    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }


}
