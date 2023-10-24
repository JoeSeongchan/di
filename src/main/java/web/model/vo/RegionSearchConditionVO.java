package web.model.vo;

import java.io.Serializable;

import web.util.MyException;

public class RegionSearchConditionVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int sidoCode, gugunCode, contentTypeId;

    public RegionSearchConditionVO(int sidoCode, int gugunCode, int contentTypeId) throws MyException {
        setSidoCode(sidoCode);
        setGugunCode(gugunCode);
        setContentTypeId(contentTypeId);
    }
    // TODO: setter에 유효성 검증 코드 넣기 
    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) {
        this.sidoCode = sidoCode;
    }

    public int getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(int gugunCode) {
        this.gugunCode = gugunCode;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }
}
