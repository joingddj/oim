/**
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * @author XiaHui
 * @since 1.0.0
 */
export default class GroupMemberQuery {


    public ids: [] | null = null;

    public startUpdatedTimestamp: number | null = null;

    public stopUpdatedTimestamp: number | null = null;

    public startCreatedTimestamp: number | null = null;

    public stopCreatedTimestamp: number | null = null;

    /**
     * 主键id
     */
    public id: string | null = null;
    /**
     * 创建时间（格式：2019-01-01 00:00:00）
     */
    public createdDateTime: string | null = null;
    /**
     * 创建时间戳（毫秒）
     */
    public createdTimestamp: string | null = null;
    /**
     * 是否逻辑删除：0：否、1：是
     */
    public isDeleted: number | null = null;
    /**
     * 修改时间（格式：2019-01-01 00:00:00）
     */
    public updatedDateTime: string | null = null;
    /**
     * 修改时间戳（毫秒）
     */
    public updatedTimestamp: string | null = null;
    /**
     * 群id
     */
    public groupId: string | null = null;
    /**
     * 备注名(群中显示昵称)
     */
    public nickname: string | null = null;
    /**
     * 权限  1:群主 2:管理员 3:普通成员
     */
    public position: string | null = null;
    /**
     * 群成员用户id
     */
    public userId: string | null = null;
}
