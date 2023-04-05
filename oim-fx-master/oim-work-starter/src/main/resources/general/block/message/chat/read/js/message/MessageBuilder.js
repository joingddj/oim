function MessageBuilder() {
    var own = this;

    own.getHtml = function (message) {
        var html = '';
        if (message) {
            var type = message.type;
            if (0 === type) {
                html = own.getMessageHtml(message);
            }
            if (1 === type) {
                html = own.getPromptHtml(message);
            }
        }
        return html;
    }

    own.getPromptHtml = function (message) {
        var html = '';
        if (message) {
            var type = message.type;
            var id = message.id;
            var key = message.key;

            var timeVisible = message.timeVisible;
            var timeText = message.timeText;
            var timestamp = message.timestamp;

            var text = message.text;


            html = '';
            html = html + '       <div id="' + key + '" name="message-prompt"  class="message-item-prompt">';
            if (timeVisible) {
                html = html + '            <div class="prompt-time">';
                html = html + '                <div class="content">' + timeText + '</div>';
                html = html + '            </div>';
            }
            html = html + '            <p class="line">' + text + '</p>';
            html = html + '        </div>';
        }
        return html;
    }

    own.getMessageHtml = function (message) {
        var html = '';
        if (message) {
            var type = message.type;
            var id = message.id;
            var key = message.key;
            var isOwn = message.own;
            var name = message.name;
            var avatar = message.avatar;
            var user = message.user;
            /**
             * 0:发送中 1:发送成功 2:发送失败
             */
            var status = message.status;
            var nameVisible = message.nameVisible;

            var receive = message.receive;
            var timeVisible = message.timeVisible;
            var timeText = message.timeText;
            var timestamp = message.timestamp;

            var content = message.content;

            var messageClassStyle = isOwn ? 'message right' : 'message left';
            var bubbleClassStyle = isOwn ? 'bubble bubble_primary right' : 'bubble bubble_default left';
            var nameClassStyle = isOwn ? 'nickname nickname-right' : 'nickname nickname-left';

            var timeVisibleStyle = timeVisible ? '' : 'display: none';
            var nameVisibleStyle = nameVisible ? '' : 'display: none';

            var contentHtml = own.getContentHtml(content);
            var p = '<div style="padding: 0">' + contentHtml + '</div>';
            var messageStatusHtml = own.getMessageStatusHtml(key, status, receive);

            html = html + '       <div name="message-content" id="' + key + '" class="' + messageClassStyle + '">';
            html = html + '            <div style="' + timeVisibleStyle + '" class="message-time">';
            html = html + '                <div class="content">' + timeText + '</div>';
            html = html + '            </div>';
            html = html + '            <img class="avatar" src="' + avatar + '" title="' + name + '" onselectstart="return false;">';
            html = html + '            <div class="content message-font">';
            html = html + '                <h4 style="' + nameVisibleStyle + '" class="' + nameClassStyle + '">' + name + '</h4>';
            html = html + '                <div class="' + bubbleClassStyle + '">';
            html = html + '                    <div class="bubble_cont ">';
            html = html + '                        <i class="arrow"></i>';
            html = html + '                        <div class="plain">';
            html = html + '                             <div style="padding: 0">' + contentHtml + '</div>';
            // html = html + '                            <div style="padding: 0">';
            // html = html + '                                <template v-if="hasSections" v-for="(section,index) of data.content.sections">';
            // html = html + '                                    <template v-if="!hasItems(section)&&index>0">';
            // html = html + '                                        <br>';
            // html = html + '                                    </template>';
            // html = html + '                                    <div>';
            // html = html + '                                        <template v-if="hasItems(section)" v-for="item of section.items">';
            // html = html + '                                            <message-content-item :data="item">';
            // html = html + '                                            </message-content-item>';
            // html = html + '                                        </template>';
            // html = html + '                                    </div>';
            // html = html + '                                </template>';
            //html = html + '                            </div>';
            // html = html + '                            <!--<img class="ico_loading ng-hide" src="" alt="">-->';
            // html = html + '                            <div v-if="data.own && data.status===2" class="action-icon">';
            // html = html + '                                <i @click="resend" class="icon-fail fas fa-exclamation-circle"';
            // html = html + '                                   title="重新发送">';
            // html = html + '                                </i>';
            // html = html + '                            </div>';
            // html = html + '                            <div v-if="data.own && data.status===0" class="action-icon">';
            // html = html + '                                <i class="icon-loading fas fa-redo-alt fa-spin"';
            // html = html + '                                   title="发送中">';
            // html = html + '                                </i>';
            // html = html + '                            </div>';
            html = html + messageStatusHtml;
            html = html + '                        </div>';
            html = html + '                    </div>';
            html = html + '                </div>';
            html = html + '            </div>';
            html = html + '        </div>';
        }
        return html;
    }
    own.getMessageStatusHtml = function (key, status, receive) {
        var html = '';
        if (status === 2) {
            html = html + '                            <div class="action-icon">';
            html = html + '                                <i onclick="appBridge.resend(\'' + key + '\')" class="icon-fail fas fa-exclamation-circle"';
            html = html + '                                   title="重新发送">';
            html = html + '                                </i>';
            html = html + '                            </div>';
        }
        if (status === 0) {
            html = html + '                            <div class="action-icon">';
            html = html + '                                <i class="icon-loading fas fa-redo-alt fa-spin"';
            html = html + '                                   title="发送中">';
            html = html + '                                </i>';
            html = html + '                            </div>';
        }
        return html;
    }
    own.getContentHtml = function (content) {
        var html = '';
        if (content && content.sections) {
            var messageKey = content.key;
            var sections = content.sections;
            var length = sections.length;
            for (var i = 0; i < length; i++) {
                var section = sections[i];
                var has = (section && section.items && section.items.length > 0);
                if (!has && i > 0) {
                    html = html + '<br>';
                } else {
                    var sectionHtml = own.getSectionHtml(section, messageKey);
                    html = html + '<div>';
                    html = html + sectionHtml;
                    html = html + '</div>';
                }
            }
        }
        return html;
    }

    own.getSectionHtml = function (section, messageKey) {
        var html = '';
        if (section && section.items) {
            var items = section.items;
            var length = items.length;
            for (var i = 0; i < length; i++) {
                var item = items[i];
                var itemHtml = own.getItemHtml(item, messageKey);
                html = html + itemHtml;
            }
        }
        return html;
    }

    own.getItemHtml = function (item, messageKey) {
        var html = '';
        if (item) {
            html = '';


            var type = item.type;
            var value = item.value;
            var itemKey = item.key;
            if (BaseUtil.isEmpty(itemKey)) {
                itemKey = BaseUtil.getKey();
            }
            if ('text' === type) {
                html = html + '<label>' + value + '</label>';
            } else if ('image' === type) {
                var type = value.type; // 1:local upload 2:net
                var id = value.id;
                var name = value.name;
                var url = value.url;
                var size = value.size;
                var extension = value.extension;

                var valueJson = BaseUtil.objectToJson(value);

                html = html + ' <a  href="javascript:appBridge.showImage(\'' + messageKey + '\',\'' + itemKey + '\',\'' + url + '\');">';
                html = html + '    <img id="' + itemKey + '" name="chat-image" chat="chat_image" data-source="' + url + '" src="' + url + '">';
                html = html + '</a>';
            } else if ('face' === type) {
                var categoryId = value.categoryId;
                var key = value.key;
                var text = value.text;
                var path = value.path;
                var height = value.height;
                var width = value.width;

				var faceValue = categoryId + ',' + key;

                var sizeStyle = '';
                if (height && height > 0 && width && width > 0) {
                    sizeStyle = sizeStyle + ' height: ' + height + 'px' + ';';
                    sizeStyle = sizeStyle + 'width: ' + width + 'px' + ';';
                }

                html = html + '<img value="' + faceValue + '" style="' + sizeStyle + '" src="' + path + '" name="face" title="' + text + '" path="' + path + '">';
            } else if ('file' === type) {
                var type = value.type; // 1:local upload 2:net
                var id = value.id;
                var name = value.name;
                var url = value.url;
                var size = value.size;
                var extension = value.extension;

                var sizeText = value.sizeText;

                var iconUrl = own.getFileIconUrl(value);

                var download = messageKey + '\',\'' + itemKey + '\',\'' + url + '\',\'' + name + '\',\'' + size;

                html = html + '    <div class="attach">';
                html = html + '        <div class="attach_bd">';
                html = html + '            <div class="cover">';
                html = html + '                <img src="' + iconUrl + '" alt="icon">';
                html = html + '            </div>';
                html = html + '            <div class="cont">';
                html = html + '                <p class="title ">' + name + '</p>';
                html = html + '                <div class="opr">';
                html = html + '                    <span class="">' + sizeText + '</span>';
                html = html + '                    <span class="sep">|</span>';
                html = html + '                    <label class="download" onclick="appBridge.download(\'' + download + '\');"';
                html = html + '                    >下载</label>';
                html = html + '                </div>';
                html = html + '            </div>';
                html = html + '        </div>';
                html = html + '    </div>';
            } else if ('audio' === type) {
                var type = value.type; // 1:local upload 2:net
                var id = value.id;
                var name = value.name;
                var url = value.url;
                var size = value.size;
                var extension = value.extension;

                var sizeText = value.sizeText;

                var iconUrl = own.getFileIconUrl(value);

                var valueJson = BaseUtil.objectToJson(value);

                var download = messageKey + '\',\'' + itemKey + '\',\'' + url + '\',\'' + name + '\',\'' + size;

                html = html + '     <div class="voice media">';
                html = html + '        <div style="width:320px;">';
                html = html + '            <audio width="320" controls>';
                html = html + '                <source src="' + url + '">';
                html = html + '                不支持播放的格式，请下载文件播放\';';
                html = html + '            </audio>';
                html = html + '        </div>';
                html = html + '        <br>';
                html = html + '        <label>文件：' + name + ' </label>';
                html = html + '        <br>';
                html = html + '        <label>';
                html = html + '            <span file-url="' + url + '" onclick="appBridge.download(\'' + download + '\');" class="download"';
                html = html + '               >点击下载</span>|<span>' + sizeText + '</span>';
                html = html + '        </label>';
                html = html + '    </div>';
            } else if ('video' === type) {
                var type = value.type; // 1:local upload 2:net
                var id = value.id;
                var name = value.name;
                var url = value.url;
                var size = value.size;
                var extension = value.extension;

                var sizeText = value.sizeText;

                var iconUrl = own.getFileIconUrl(value);

                var valueJson = BaseUtil.objectToJson(value);

                var download = messageKey + '\',\'' + itemKey + '\',\'' + url + '\',\'' + name + '\',\'' + size;

                html = html + '     <div class="video media">';
                html = html + '        <div style="width:320px;">';
                html = html + '            <video width="320" controls>';
                html = html + '                <source src="' + url + '">';
                html = html + '                不支持播放的格式，请下载文件播放\';';
                html = html + '            </video>';
                html = html + '        </div>';
                html = html + '        <br>';
                html = html + '        <label>文件：' + name + '</label>';
                html = html + '        <br>';
                html = html + '        <label>';
                html = html + '            <span file-url="' + url + '" onclick="appBridge.download(\'' + download + '\');" class="download"';
                html = html + '               >点击下载</span>|<span>' + sizeText + '</span>';
                html = html + '        </label>';
                html = html + '    </div>';
            } else if ('at' === type) {
                var at = '@';
                var userId = value.userId;
                var text = value.text;

                var vt = '';
                if (text) {
                    if (text.indexOf(at) > -1) {
                        vt = text;
                    } else {
                        vt = at + text;
                    }
                }

                html = html + '<a name="at" value="' + userId + '" text="' + text + '" class="message-content-item-at" contenteditable="false" href="javascript:void(0)">' + vt + '</a>';
            } else {
                html = html + '     <div>';
                html = html + '        <p>不支持的消息</p>';
                // html = html + '        <p>不支持的消息<a href="javascript:void(0)">点击查看原始内容</a></p>';
                // html = html + '        <p v-if="show">{{value}}</p>';
                html = html + '    </div>';
            }
        }
        return html;
    }

    own.getFileIconUrl = function (value) {
        var url = '';
        var e = document.getElementById('file-icon-default');
        if (e) {
            url = e.src;
        }
        return url;
    }
}
