package com.cszjkj.aisc.tms_user.mapper;

import com.cszjkj.aisc.cm_user.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select real_name as realName, mobile_phone as mobilePone, email_addr as emailAddr " +
            "from e_user where user_id=#{userId}")
    public UserInfo getUserById(@Param("userId") long userId);
    public UserInfo getUserByLoginName(@Param("loginName") String loginName);
    /*
    @Insert("insert into e_user(real_name, login_name, login_pwd, mobile_phone, email_addr) " +
            "values(#{u.realName}, #{u.loginName}, #{u.loginPwd}, #{u.mobilePhone}, #{u.emailAddr})")
     */
    public void registerUser(@Param("u") UserInfo userInfo);
}
