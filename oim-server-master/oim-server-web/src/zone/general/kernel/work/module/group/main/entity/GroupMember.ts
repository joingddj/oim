/**
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * @author XiaHui
 * @since 1.0.0
 */
export default class GroupMember {

    /**
     * 主键id
     */
    public id: string = '';
    /**
     * 创建时间（格式：2019-01-01 00:00:00）
     */
    public createdDateTime: string = '';
    /**
     * 创建时间戳（毫秒）
     */
    public createdTimestamp: string = '';
    /**
     * 是否逻辑删除：0：否、1：是
     */
    public isDeleted: number = 0;
    /**
     * 修改时间（格式：2019-01-01 00:00:00）
     */
    public updatedDateTime: string = '';
    /**
     * 修改时间戳（毫秒）
     */
    public updatedTimestamp: string = '';
    /**
     * 群id
     */
    public groupId: string = '';
    /**
     * 备注名(群中显示昵称)
     */
    public nickname: string = '';
    /**
     * 权限  1:群主 2:管理员 3:普通成员
     */
    public position: string = '';
    /**
     * 群成员用户id
     */
    public userId: string = '';
}
