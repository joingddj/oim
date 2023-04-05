function WebBridge() {

    var own = this;

    own.setMessages = function (value) {
        if (BaseUtil.isJsonArray(value)) {
            var messages = BaseUtil.jsonToObject(value);
            readViewManager.setMessageValues(messages);
        }
        return (value) ? value.toString() : '';
    }

    own.insertBefore = function (value) {
        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            readViewManager.insertBeforeValue(message);
        }
        return (value) ? value.toString() : '';
    }

    own.insertLast = function (value) {

        if (BaseUtil.isJsonObject(value)) {
            var message = BaseUtil.jsonToObject(value);
            var position = readView.getScrollPosition();
            readViewManager.insertLastValue(message);
            if ("bottom" === position) {
                readView.scrollToBottom();
            }
            // setTimeout(() => {
            // }, 10);
        }
        return (value) ? value.toString() : '';
    }

    own.clear = function () {
        readViewManager.clear();
    }

    own.scrollToBottom = function () {
        readView.scrollToBottom();
    }

    own.reviseScrollBottom = function () {
		var lastPosition = readView.getScrollPosition();
        var syncPosition = readView.getSyncScrollPosition();
        if ("bottom" === lastPosition && "bottom" !== syncPosition) {
            readView.scrollToBottom();
        }
    }

    own.setScrollTop = function (top) {
        readView.setScrollTop(top);
    }

    own.saveScrollTop = function () {
        appBridge.saveScrollTop();
    }

    own.setListMaxSize = function (maxSize) {
        readView.setMaxSize(maxSize);
    }

    own.replaceImageSrc = function (id, src) {
        readView.replaceImageSrc(id, src);
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
		var lastPosition = readView.getScrollPosition();
        if ("bottom" !== lastPosition) {
	        if (BaseUtil.isJsonObject(value)) {
	            var message = BaseUtil.jsonToObject(value);
	            var messageKey = message.messageKey;
	            var chatUserName = message.chatUserName;
	            var chatText = message.chatText;
	            promptView.insertChatPrompt(messageKey, chatUserName, chatText);
	        }
		}
        return (value) ? value.toString() : '';
    }

	own.setTextPrompt = function (value) {
		if (BaseUtil.isNotEmpty(value)) {
            promptView.setTextPrompt(value);
        }
    }
}

window.webBridge = new WebBridge();
var appBridge = new AppBridge();

//判断是不是火狐
if (window.navigator.userAgent.toLowerCase().indexOf('firefox') != -1) {
    document.body.addEventListener('DOMMouseScroll', function (event) {
        handleMousewheel(event);
    }, false);//'DOMMouseScroll'只兼容火狐,必须用时间绑定；
} else {
    document.addEventListener("mousewheel", function (event) {
        handleMousewheel(event);
    });
}
var handleMousewheel = function (e) {

    if (e instanceof WheelEvent) {
        var target = e.target;
        var deltaY = e.deltaY;
        if (deltaY < 0) {
            // 向上
            var position = readView.getSyncScrollPosition();
            if (position === 'top') {
                // alert('top')
				appBridge.onScrollTop(readView.getElementSize());
            }
        }
    }
}
// document.body.addEventListener("mousewheel", function (event) {
//     readView.handleMousewheel(event);
// });

function Task() {
    var own = this;
    own.initialize = function () {
        own.intervalTime = window.setInterval(function () {
            webBridge.reviseScrollBottom();
        }, 1000);
    }
    
    own.initialize();
}

new Task();
