/**
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * @author XiaHui
 * @since 1.0.0
 */
export default class GroupQuery {


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
     * 自定义头像，（当用户选择系统头像时为空）
     */
    public avatar: string | null = null;
    /**
     * 分类
     */
    public classification: string | null = null;
    /**
     * 系统头像
     */
    public head: string | null = null;
    /**
     * 介绍
     */
    public introduce: string | null = null;
    /**
     * 群位置
     */
    public location: string | null = null;
    /**
     * 群名称
     */
    public name: string | null = null;
    /**
     * 群号码
     */
    public number: string | null = null;
}
