namespace py api
namespace java com.cszjkj.aisc.tms_message

service MessageServiceApi {
    bool send_sms(1:string mobile_phone, 2:string msg);
    bool send_email(1:string email_addr, 2:string msg);
}