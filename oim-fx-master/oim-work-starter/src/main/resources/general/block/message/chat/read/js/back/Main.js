function WebBridge() {

    var own = this;
    // var readView = readView;
    var messageBuilder = new MessageBuilder();

    own.setMessages = function (value) {
        if (BaseUtil.isJsonArray(value)) {
            own.clear();
            var messages = BaseUtil.jsonToObject(value);
            if (messages && messages.length > 0) {
                var length = messages.length;
                for (var i = 0; i < length; i++) {
                    var message = messages[i];
                    var html = messageBuilder.getHtml(message);
                    readView.insertLastHtml(html);
                }
            }
            readView.scrollToBottom();
        }
        return (value) ? value.toString() : '';
    }

    own.insertBefore = function (value) {
        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            var html = messageBuilder.getHtml(message);

            var key = message.key;
            if (readView.hasElement(key)) {
                readView.replaceElementHtml(key, html);
            } else {
                readView.insertBeforeHtml(html);
            }
        }
        return (value) ? value.toString() : '';
    }

    own.insertLast = function (value) {

        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            var html = messageBuilder.getHtml(message);

            var key = message.key;
            if (readView.hasElement(key)) {
                readView.replaceElementHtml(key, html);
            } else {
                var position = readView.getScrollPosition();
                readView.insertLastHtml(html);
                readView.checkElementSize();
                if ("bottom" === position) {
                    readView.scrollToBottom();
                    // setTimeout(() => {
                    //
                    // }, 10);
                }
            }
        }
        return (value) ? value.toString() : '';
    }

    own.clear = function () {
        readView.setListHtml('');
    }

    own.scrollToBottom = function () {
        readView.scrollToBottom();
    }

    own.setScrollTop = function (top) {
        readView.setScrollTop(top);
    }

    own.saveScrollTop = function () {
        appBridge.saveScrollTop();
    }

    own.removeNode = function (id) {
        readView.removeNode(id);
    }

    own.insertAt = function (value) {
        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            var messageKey = message.messageKey;
            var chatUserName = message.chatUserName;
            var chatText = message.chatText;
            promptView.insertAt(messageKey, chatUserName, chatText);
        }
        return (value) ? value.toString() : '';
    }

    own.insertChatPrompt = function (value) {
        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            var messageKey = message.messageKey;
            var chatUserName = message.chatUserName;
            var chatText = message.chatText;
            promptView.insertChatPrompt(messageKey, chatUserName, chatText);
        }
        return (value) ? value.toString() : '';
    }
}
