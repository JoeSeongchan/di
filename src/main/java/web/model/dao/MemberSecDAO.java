package web.model.dao;

import web.model.vo.MemberSecVO;
import web.util.MyException;

public interface MemberSecDAO {
    String readSalt(String id) throws MyException;

    void write(MemberSecVO memberSecVO) throws MyException;
}
