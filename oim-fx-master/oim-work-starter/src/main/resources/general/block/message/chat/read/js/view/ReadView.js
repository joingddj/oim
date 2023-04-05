var readView = new ReadView();
var promptView = new PromptView();
var readViewManager = new ReadViewManager();

function ReadView() {
    var own = this;
    var maxSize = 500;
    var viewNodeId = 'message-list';
	var viewScrollId = 'message-scroll';
    var position = 'bottom';

    own.insertBeforeHtml = function (html) {
        var list = document.getElementById(viewNodeId);
        if (list) {
            var element = document.createElement('div');
            element.innerHTML = html;
            list.insertBefore(element, list.firstElementChild);
        }
    }

    own.insertLastHtml = function (html) {
        var list = document.getElementById(viewNodeId);
        if (list) {
            var element = document.createElement('div');
            element.innerHTML = html;
            list.appendChild(element);
        }
    }

    own.replaceElementHtml = function (id, html) {
        var oldElement = document.getElementById(id);
        if (oldElement) {
            oldElement.innerHTML = html;
        }
    }

    own.setListHtml = function (html) {
        var list = document.getElementById(viewNodeId);
        if (list) {
            list.innerHTML = html;
        }
    }

    own.checkElementSize = function (max) {
        if (!max) {
            max = maxSize;
        }
        var list = document.getElementById(viewNodeId);
        if (list) {
            var nodes = list.children;
            var length = nodes.length;
            if (length > max) {
                var size = length - max;
                for (var i = 0; i < size; i++) {
                    list.removeChild(list.firstElementChild);
                }
            }
        }
    }

    own.getElementSize = function () {
        var length = 0;
        var list = document.getElementById(viewNodeId);
        if (list) {
            var nodes = list.children;
            length = nodes.length;
        }
        return length;
    }

    own.isMaxElementSize = function () {
        var is = false;
        if (own.getElementSize() >= maxSize) {
            is = true;
        }
        return is;
    }

    own.getMaxSize = function () {
        return maxSize;
    }

    own.setMaxSize = function (max) {
        maxSize = max;
    }

    own.hasElement = function (id) {
        var has = false;
        var e = document.getElementById(id);
        if (e) {
            has = true;
        }
        return has;
    }

    own.replaceImageSrc = function (id, src) {
        var e = document.getElementById(id);
        if (e) {
            e.src = src;
        }
    }

    own.getScrollPosition = function () {
        return position;
    }

    own.getScrollTop = function () {
        var top = 0;
        var list = document.getElementById(viewScrollId);
        if (list) {
            top = list.scrollTop;
        }
        return top;
    }

    own.setScrollTop = function (top) {
        var list = document.getElementById(viewScrollId);
        if (list) {
            list.scrollTop = top;
        }
    }


    own.getSyncScrollPosition = function () {
		var p = "bottom";
        var list = document.getElementById(viewScrollId);
        if (list) {
            var height = list.scrollHeight;
            var top = list.scrollTop;

            var clientHeight = list.clientHeight;


            var a = (height - top);
            var b = (clientHeight + 25);
            if (top === 0 && clientHeight === height) {
                p = "top";
            } else if (a < b) {
                p = "bottom";
            } else if (top === 0) {
                p = "top";
            } else {
                p = "middle";
            }
        }
        return p;
    }

    own.scrollToBottom = function () {
        var list = document.getElementById(viewScrollId);
        if (list) {
            var height = list.scrollHeight;
            list.scrollTop = height + 150;
        }
        position = 'bottom';
    }

    own.updateScrollIntoView = function (viewId) {
        var list = document.getElementById(viewScrollId);
        if (list) {
            var v = document.getElementById(viewId);
            if (v) {
                var offsetTop = v.offsetTop;
                list.scrollTop = offsetTop;
            }
        }
    }

    own.removeNode = function (id) {
        var node = document.getElementById(id);
        if (node) {
            var list = document.getElementById(viewNodeId);
            if (list) {
                list.removeChild(node);
            }
        }
    }

    own.handleScroll = function (e) {
        var own = this;
        var target = e.target;
        if (target instanceof Element) {
            var node = target;
            var height = node.scrollHeight;
            var top = node.scrollTop;

            var clientHeight = node.clientHeight;

            var a = (height - top);
            var b = (clientHeight + 25);

            if (a < b) {
                position = 'bottom';
            } else if (top === 0) {
                position = 'top';
            } else {
                position = 'middle';
            }
        }
    }
}


function PromptView() {
    var own = this;
    var viewId = 'prompt-view';

    own.insertLastHtml = function (html) {
        var list = document.getElementById(viewId);
        if (list) {
            var element = document.createElement('div');
            element.innerHTML = html;
            list.appendChild(element);
        }
    }

    own.hasElement = function (id) {
        var has = false;
        var e = document.getElementById(id);
        if (e) {
            has = true;
        }
        return has;
    }

    own.replaceElementHtml = function (id, html) {
        var oldElement = document.getElementById(id);
        if (oldElement) {
            oldElement.innerHTML = html;
        }
    }

    own.removeNode = function (node) {
        if (node) {
            node.remove();
        }
    }

	own.onPromptAtClick = function (messageKey) {
        if (messageKey) {
            readView.updateScrollIntoView(messageKey);
        }
        var id = 'prompt-view-at';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, '');
        }
    }

    own.onPromptChatClick = function (messageKey) {
        if (messageKey) {
            readView.updateScrollIntoView(messageKey);
        }
        var id = 'prompt-view-chat';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, '');
        }
    }

	own.onPromptTextClick = function () {
        var id = 'prompt-view-text';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, '');
        }
    }

    own.insertAt = function (messageKey, chatUserName, chatText) {
        var id = 'prompt-view-at';
        var text = chatUserName + '@我：' + chatText;
        var html = '';
        html = html + '<div id="' + id + '" style="color: #00b0ff" class="prompt-message-inner" onclick="promptView.onPromptAtClick(\'' + messageKey + '\')">';
        html = html + text;
        html = html + '</div>';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, html);
        } 
    }

    own.insertChatPrompt = function (messageKey, chatUserName, chatText) {
        var id = 'prompt-view-chat';
        var text = chatUserName + '：' + chatText;
        var html = '';
        html = html + '<div id="' + id + '" class="prompt-message-inner" onclick="promptView.onPromptChatClick(\'' + messageKey + '\')">';
        html = html + text;
        html = html + '</div>';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, html);
        }
    }

	own.setTextPrompt = function (text) {
        var id = 'prompt-view-text';
        var html = '';
        html = html + '<div id="' + id + '" class="prompt-message-inner" onclick="promptView.onPromptTextClick()">';
        html = html + text;
        html = html + '</div>';
        if (own.hasElement(id)) {
            own.replaceElementHtml(id, html);
        }
    }
}

function ReadViewManager() {
    var own = this;
    var index = 1;
    var messageBuilder = new MessageBuilder();

    own.setMessageValues = function (messages) {
        own.clear();
        if (messages && messages.length > 0) {
            var length = messages.length;
            for (var i = 0; i < length; i++) {
                var message = messages[i];
                own.insertLastValue(message);
            }
        }
        readView.scrollToBottom();
    }

    own.insertBeforeValue = function (message) {
        if (message && !readView.isMaxElementSize()) {
            if (BaseUtil.isEmpty(message.key)) {
                message.key = own.getKey() + '';
            }
            var html = messageBuilder.getHtml(message);
            var key = message.key;
            if (readView.hasElement(key)) {
                readView.replaceElementHtml(key, html);
            } else {
                readView.insertBeforeHtml(html);
            }
        }
    }

    own.insertLastValue = function (message) {
        if (message) {
            if (BaseUtil.isEmpty(message.key)) {
                message.key = own.getKey() + '';
            }
            var html = messageBuilder.getHtml(message);
            var key = message.key;
            if (readView.hasElement(key)) {
                readView.replaceElementHtml(key, html);
            } else {
                readView.insertLastHtml(html);
                readView.checkElementSize();
            }
        }
    }

    own.clear = function () {
        readView.setListHtml('');
    }

    own.getKey = function () {
        index++;
        var key = BaseUtil.getTimestamp() + index;
        return key;
    }
}
