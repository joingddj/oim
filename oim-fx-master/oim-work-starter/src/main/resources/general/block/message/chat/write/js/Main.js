function WebBridge() {
    var own = this;

    own.insertBeforeHtml = function (html) {
        writeView.insertBeforeHtml(html);
    }

    own.insertLastHtml = function (html) {
        writeView.insertLastHtml(html);
    }

    own.replaceElementHtml = function (id, html) {
        writeView.replaceElementHtml(html);
    }

    own.clear = function () {
        writeView.setBodyHtml('');
    }

    own.setBodyHtml = function (html) {
        writeView.setBodyHtml(html);
    }

    own.insertAtCursorHtml = function (html) {
        writeView.insertAtCursorHtml(html);
    }

    own.focus = function () {
        writeView.focus();
    }

    own.isFocus = function () {
        return writeView.isFocus();
    }

    own.hasElement = function (id) {
        return writeView.insertBeforeHtml(id);
    }

    own.replaceImageSrc = function (id, src) {
        writeView.replaceImageSrc(id, src);
    }

    own.deleteSelection = function (id, src) {
        writeView.deleteSelection();
    }

    own.getMessageContent = function () {
        var m;
        var childNodes = document.body.childNodes;
        var content = contentGetUtil.getContent(childNodes);
        if (content) {
            m = BaseUtil.objectToJson(content);
        } else {
            m = '';
        }
        return m;
    }

    own.updateStyle = function (style) {
        document.body.setAttribute('style', style);
    }

    own.insertAtUser = function (userId, name) {
        if (!userId) {
            userId = '';
        }
        if (!name) {
            name = '';
        }
        var vt = '@' + name;
        var html = '<label name="at" value="' + userId + '" text="' + vt + '" disabled="disabled"  contenteditable="false" href="javascript:void(0)">' + vt + '</label>';


        var data = writeUtil.getCursorLocation();
        var node = data.node;
        if (node) {
            var nodeValue = node.nodeValue;
            if (nodeValue) {
                var at = '@';
                var length = nodeValue.length;
                var lastIndex = nodeValue.lastIndexOf(at);
                if (lastIndex > -1 && lastIndex < length) {
                    node.nodeValue = nodeValue.substring(0, lastIndex);
                }
            }
            writeView.toCursor(node);
        }
        own.insertAtCursorHtml(html);
    }

    own.insertAtUserImage = function (userId, name, img) {
        if (!userId) {
            userId = '';
        }
        if (!name) {
            name = '';
        }

        var vt = '@' + name;
        var html = '<img name="at" class="at" value="' + userId + '" text="' + name + '"  title="' + vt + '" src="' + img + '"  />';

        var data = writeUtil.getCursorLocation();
        var node = data.node;
        var endValue = '';
        if (node) {
            var nodeValue = node.nodeValue;
            // appEditBridge.showLog('nodeValue:' + nodeValue);
            if (nodeValue) {
                var at = '@';
                var length = nodeValue.length;

                var range = data.range;
                if (range) {
                    var rangeStartOffset = range.startOffset;
                    // range.endOffset;

                    if (rangeStartOffset < length) {
                        nodeValue = nodeValue.substring(0, rangeStartOffset);
                        length = nodeValue.length;
                    }

                    var lastIndex = nodeValue.lastIndexOf(at);
                    if (lastIndex > -1 && length > 0) {
                        //range.startOffset = lastIndex;
                        range.setStart(node, lastIndex);
                        range.setEnd(node, length);
                        // range.select();
                        var selection = window.getSelection();
                        selection.removeAllRanges();
                        selection.addRange(range);
                        selection.deleteFromDocument();
                        // appEditBridge.showLog('lastIndex:' + lastIndex);
                    }
                }


                // if (lastIndex > -1 && length > 0) {
                //     var nameLength = name.length;
                //     var endLength = nameLength + lastIndex;
                //     var end = length - 1;
                //     // appEditBridge.showLog('lastIndex:' + lastIndex + '/nameLength:' + nameLength + '/endLength:' + endLength);
                //
                //     var startValue = nodeValue.substring(0, lastIndex);
                //     if (endLength > end) {
                //         endLength = end;
                //     }
                //     if (endLength < end) {
                //         endValue = nodeValue.substring(endLength);
                //     }
                //     if (startValue) {
                //         // node.nodeValue = startValue;
                //     }
                // }


            }
            // writeView.toCursor(node);
        }


        own.insertAtCursorHtml(html);
        if (endValue) {
            //  own.insertAtCursorHtml(endValue);
        }
    }
}

var webBridge = new WebBridge();

function AppEditBridge() {
    var own = this;
    own.paste = function () {
        var bridge = window.editBridge;
        if (bridge) {
            bridge.paste();
        }
    }

    own.canBlankLine = function () {
        var can = true;
        var bridge = window.editBridge;
        if (bridge) {
            bridge.canBlankLine();
        }
        return can;
    }

    own.onKeypress = function (e) {
        if (!e.shiftKey && e.keyCode === 13) {
            e.returnValue = false;
            return false;
        }
    }

    own.showLog = function (log) {
        var bridge = window.editBridge;
        if (bridge) {
            bridge.showLog(log);
        }
    }
}


function AppBridge() {
    var own = this;
    var node;
    own.onInput = function (event) {
        var data = writeUtil.getCursorLocation();
        node = data.node;
        var range = data.range;
        var rangeStartOffset = 0;
        var rangeEndOffset = 0;
        if (range) {
            rangeStartOffset = range.startOffset;
            rangeEndOffset = range.endOffset;
        }
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.onInput(data.text, data.x, data.y, rangeStartOffset, rangeEndOffset);
        }
    }
}

var appEditBridge = new AppEditBridge();
var appBridge = new AppBridge();
//判断是不是火狐
if (window.navigator.userAgent.toLowerCase().indexOf('firefox') != -1) {

} else {
    document.addEventListener("paste", function (e) {
        // e.preventDefault();
        // e.stopPropagation();
        appEditBridge.paste();
        // writeUtil.pasteHandle(e,
        //     function (html) {
        //         webBridge.insertAtCursorHtml('哈哈啥');
        //     },
        //     function (file) {
        //
        //     },
        //     function (files) {
        //
        //     }
        // );
    });
}

window.addEventListener('DOMContentLoaded', function () {
    webBridge.clear();
    document.body.focus();
});

