package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

/**
 * 
 * <p>Title: UserMapper</p>
 * <p>Description: mapper�ӿڣ��൱ ��dao�ӿڣ��û�����</p>
 * <p>Company: www.itcast.com</p> 
 * @author	����.����
 * @date	2015-4-22����2:45:12
 * @version 1.0
 */
public interface UserMapper {
	
	//�û���Ϣ�ۺϲ�ѯ
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	//�û���Ϣ�ۺϲ�ѯ����
	public int findUserCount(UserQueryVo userQueryVo) throws Exception;
	
	//����id��ѯ�û���Ϣ
	public User findUserById(int id) throws Exception;
	
	//����id��ѯ�û���Ϣ��ʹ��resultMap���
	public User findUserByIdResultMap(int id) throws Exception;
	
	
	//�����û����в�ѯ�û��б�
	public List<User> findUserByName(String name)throws Exception;
	
	//�����û�
	public void insertUser(User user)throws Exception;
	
	//ɾ���û�
	public void deleteUser(int id)throws Exception;
	
	
	

}
