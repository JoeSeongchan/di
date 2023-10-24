package web.model.service;

import java.util.UUID;
import web.model.dao.MemberDAO;
import web.model.dao.MemberSecDAO;
import web.model.vo.MemberSecVO;
import web.model.vo.MemberVO;
import web.util.MyException;
import web.util.OpenCrypt;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	private MemberSecDAO memberSecDAO;

	public MemberServiceImpl(Object memberDAO, Object memberSecDAO) {
		this.memberDAO=(MemberDAO)memberDAO;
		this.memberSecDAO=(MemberSecDAO) memberSecDAO;
	}

	@Override
	public void registMember(MemberVO m) throws MyException {
		// pw와 salt를 다른 db에 저장하여야한다!
		// pinNo와 secKey를 다른 db에 저장하여야한다!
		// 보안, 트랜잭션, 영속성 서비스
		try {
			// pw 암호
			String salt = UUID.randomUUID().toString();
			String hashPw = OpenCrypt.getSHA256(m.getPw(), salt);
			m.setPw(hashPw);
			// pinNo 암호화
			byte[] secKey = OpenCrypt.generateKey("AES", 128);
			String encryptPinNo = OpenCrypt.aesEncrypt(m.getPinNo(), secKey);
			m.setPinNo(encryptPinNo);

			// 트랜잭션 시작
			memberDAO.createMember(m);
			memberSecDAO.write(new MemberSecVO(m.getId(), salt, String.valueOf(secKey)));

			// 트랜잭션 commit
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

	@Override
	public boolean login(MemberVO m) throws MyException {
		try {
			// id,pw 확인
			String id = m.getId();
			String pw = m.getPw();
			// pw확인을 하기 위해서 salt값을 가져와야됨
			String DBpw = memberDAO.readPw(m.getId()); // db에 저장되어 있는 pw
			String salt = memberSecDAO.readSalt(m.getId()); // db에 저장되어 있는 salt
			if (DBpw == null || salt == null) {
				return false;
			}

			String hashPw = OpenCrypt.getSHA256(pw, salt);
			if (DBpw.equals(hashPw)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

}
