export default class UserData {

    /**
     *
     */
    public id: string = '';
    public account: string = ''; // 帐号
    public mobile: string = ''; // 手机

    // /基本信息

    public avatar: string = ''; // 自定义照片，（当用户选择系统头像时为空）
    public nickname: string = ''; // 昵称
    public name: string = ''; // 姓名
    public gender: string = ''; // 性别 1:男 2：女 3：保密 4:男->女过程中 5：女->男过程中

    public age: string = ''; // 年龄
    public qq: string = ''; // qq
    public signature: string = '';

    public type: string = '';
    public isDisable: number = 0;

}
