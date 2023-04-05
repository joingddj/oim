class MessageContentWrap extends MessageWrap {
    type = 1;
    id = '';
    key = '';
    isOwn = false;
    name = '';
    avatar = '';
    user = {};
    /**
     * 0:发送中 1:发送成功 2:发送失败
     */
    status = 0;
    nameVisible = false;
    timeVisible = true;
    timeText = '';
    content = {timestamp: 0};

    getTimestamp = function () {
        const content = this.content;
        return content ? content.timestamp : 0;
    };
}
