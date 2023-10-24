package web.model.vo;

import web.util.MyException;

import java.io.Serializable;

public class GugunVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String name;
    private int sidoCode;

    public GugunVO() {
        super();
    }

    public GugunVO(int code, String name, int sidoCode) throws MyException {
        setCode(code);
        setName(name);
        setSidoCode(sidoCode);
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) throws MyException {
        if (sidoCode < 0) {
            throw new MyException("유효하지 않은 시도 코드입니다.");
        }
        this.sidoCode = sidoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MyException {
        if (name == null || name.length() == 0) {
            throw new MyException("유효하지 않은 시도 이름입니다.");
        }
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) throws MyException {
        if (code < 0) {
            throw new MyException("유효하지 않은 코드입니다. (시도)");
        }
        this.code = code;
    }

}
