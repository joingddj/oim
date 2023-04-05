/**
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * @author XiaHui
 * @since 1.0.0
 */
export default class Group {

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
     * 自定义头像，（当用户选择系统头像时为空）
     */
    public avatar: string = '';
    /**
     * 分类
     */
    public classification: string = '';
    /**
     * 系统头像
     */
    public head: string = '';
    /**
     * 介绍
     */
    public introduce: string = '';
    /**
     * 群位置
     */
    public location: string = '';
    /**
     * 群名称
     */
    public name: string = '';
    /**
     * 群号码
     */
    public number: string = '';
}
