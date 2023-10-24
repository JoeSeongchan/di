package web.model.service;

import web.model.vo.MemberVO;
import web.util.MyException;

public interface MemberService {

    void registMember(MemberVO m) throws MyException;

    boolean login(MemberVO m) throws MyException;

}
