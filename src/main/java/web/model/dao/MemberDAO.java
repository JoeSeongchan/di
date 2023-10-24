package web.model.dao;

import web.model.vo.MemberVO;
import web.util.MyException;

public interface MemberDAO {

	void createMember(MemberVO m) throws MyException;


	String readPw(String id) throws MyException;
}
