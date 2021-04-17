
namespace java com.cszjkj.aisc.tms_user

struct UserInfo {
    1:i64 userId,
    2:string loginName,
    3:string loginPwd,
    4:string realName,
    5:string mobilePhone,
    6:string emailAddr
}

service UserService {
    UserInfo getUserById(1:i64 userId);
    UserInfo getUserByLoginName(1:string loginName);
    void registerUser(1:UserInfo userInfo);
}