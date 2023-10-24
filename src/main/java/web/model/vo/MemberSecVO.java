package web.model.vo;

import web.util.MyException;

import java.io.Serializable;

public class MemberSecVO implements Serializable {

    private String id;
    private String salt;
    private String secKey;

    public MemberSecVO() {
    }

    public MemberSecVO(String id, String salt, String secKey) throws MyException {
        this.id = id;
        this.salt = salt;
        this.secKey = secKey;
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public String getId() {
        return id;
    }

    public void setId(String id) throws MyException {
        if (id == null || id.length() == 0) {
            throw new MyException("유효하지 않은 멤버 ID입니다.");
        }
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }
}
