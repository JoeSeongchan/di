package web.model.vo;

import java.io.Serializable;

public class SidoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int code;

    public SidoVO() {
        super();
    }

    public SidoVO(int code, String name) {
        setName(name);
        setCode(code);
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
